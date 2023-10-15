package color.controller;

import color.dto.yellowcompany.YellowCompanyCreateDTO;
import color.dto.yellowcompany.YellowCompanySummaryDTO;
import color.dto.yellowcompany.YellowCompanyUpdateDTO;
import color.service.YellowCompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class YellowCompanyController {

    private final YellowCompanyService companyService;

    @GetMapping()
    public List<YellowCompanySummaryDTO> list(@RequestParam int offset, @RequestParam int limit) {
        System.out.println(offset);
        System.out.println(limit);

        return companyService.list(offset, limit);
    }

    @PostMapping("/add")
    public Long create(@RequestBody YellowCompanyCreateDTO companyCreateDTO) {
        return companyService.create(companyCreateDTO.getName(), companyCreateDTO.getAddress(), companyCreateDTO.getRepresentativeName());
    }
    @PutMapping("/edit/{id}")
    public Long update(@PathVariable Long id,  YellowCompanyUpdateDTO companyUpdateDTO) {
        return companyService.update(id, companyUpdateDTO.getName(),companyUpdateDTO.getAddress());
    }
    @PutMapping("/deactivate/{id}")
    public Long deactivate(@PathVariable Long id) {
        return companyService.deactivate(id);
    }
}
