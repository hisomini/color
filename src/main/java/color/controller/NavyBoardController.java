package color.controller;

import color.dto.navyboard.NavyBoardCreateDTO;
import color.dto.navyboard.NavyBoardSummaryDTO;
import color.dto.navyboard.NavyBoardUpdateDTO;
import color.service.NavyBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class NavyBoardController {

    private final NavyBoardService boardService;
    @GetMapping
    public List<NavyBoardSummaryDTO> list(@PathVariable int offset, int limit) {
        return boardService.list(offset, limit);
    }
    @PostMapping("/add")
    public Long create(@RequestBody NavyBoardCreateDTO boardCreateDTO) {
        return boardService.create(boardCreateDTO.getUserId(), boardCreateDTO.getTitle(), boardCreateDTO.getContent());
    }
    @PutMapping("/edit/{id}")
    public Long update(@PathVariable Long id, NavyBoardUpdateDTO  boardUpdateDTO) {
        return boardService.update(id, boardUpdateDTO.getTitle(), boardUpdateDTO.getContent());
    }
    @PutMapping("/deactivate/{id}")
    public Long deactivate(@PathVariable Long id) {
        return boardService.deactivate(id);
    }
}
