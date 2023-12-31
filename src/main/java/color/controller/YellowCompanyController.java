package color.controller;

import color.dto.yellowcompany.YellowCompanyCreateDTO;
import color.dto.yellowcompany.YellowCompanySummaryDTO;
import color.dto.yellowcompany.YellowCompanyUpdateDTO;
import color.service.YellowCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class YellowCompanyController {

    private final YellowCompanyService companyService;

    @GetMapping()
    public List<YellowCompanySummaryDTO> list(@RequestParam int offset, @RequestParam int limit) {
        return companyService.list(offset, limit);
    }

    @PostMapping()
    public Long create(@Valid @RequestBody YellowCompanyCreateDTO companyCreateDTO) {
        return companyService.create(companyCreateDTO.getName(), companyCreateDTO.getAddress(), companyCreateDTO.getRepresentativeName());
    }

    @PutMapping("/edit/{id}")
    public Long update(@Valid @PathVariable Long id, YellowCompanyUpdateDTO companyUpdateDTO) {
        return companyService.update(id, companyUpdateDTO.getName(), companyUpdateDTO.getAddress());
    }

    @DeleteMapping("/{id}")
    public Long deactivate(@PathVariable Long id) {
        return companyService.deactivate(id);
    }
}
