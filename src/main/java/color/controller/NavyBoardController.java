package color.controller;

import color.component.CustomUserDetails;
import color.dto.navyboard.NavyBoardCreateDTO;
import color.dto.navyboard.NavyBoardDetailDTO;
import color.dto.navyboard.NavyBoardSummaryDTO;
import color.dto.navyboard.NavyBoardUpdateDTO;
import color.service.NavyBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class NavyBoardController {

    private final NavyBoardService boardService;

    @GetMapping
    public List<NavyBoardSummaryDTO> list(@RequestParam int offset, int limit) {
        return boardService.list(offset, limit);
    }

    @GetMapping("/{id}")
    public NavyBoardDetailDTO get(@PathVariable Long id) {
        return boardService.get(id);
    }

    @PostMapping()
    public Long create(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody NavyBoardCreateDTO boardCreateDTO) {
        return boardService.create(userDetails.getId(), boardCreateDTO.getTitle(), boardCreateDTO.getContent());
    }

    @PutMapping("/edit/{id}")
    public Long update(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id, @RequestBody NavyBoardUpdateDTO boardUpdateDTO) throws AccessDeniedException {
        if (!boardService.checkAuthority(userDetails.getId(), id)) {
            throw new AccessDeniedException("해당 리소스에 접근권한이 없습니다");
        }
        return boardService.update(id, boardUpdateDTO.getTitle(), boardUpdateDTO.getContent());
    }

    @DeleteMapping("/deactivate/{id}")
    public Long deactivate(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id) throws AccessDeniedException {
        if (!boardService.checkAuthority(userDetails.getId(), id)) {
            throw new AccessDeniedException("해당 리소스에 접근권한이 없습니다");
        }
        return boardService.deactivate(id);
    }
}
