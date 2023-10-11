package color.service;

import color.domain.YellowCompany;
import color.dto.yellowcompany.YellowCompanySummaryDTO;
import color.repository.YellowCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class YellowCompanyService {

    private final YellowCompanyRepository companyRepository;

    public List<YellowCompanySummaryDTO> list(int offset, int limit) {
        List<YellowCompany> companies = companyRepository.list(offset, limit);
        return companies.stream().map(company -> {
            return new YellowCompanySummaryDTO(
                    company.getId(),
                    company.getName(),
                    company.getAddress(),
                    company.getRepresentativeName()
            );
        }).collect(Collectors.toList());
    }
    @Transactional
    public Long create(String name, String address, String representativeName) {
        YellowCompany company = YellowCompany.createCompany(name, address, representativeName);
        companyRepository.save(company);
        return company.getId();
    }
    @Transactional
    public Long update(Long companyId, String name, String address) {
        YellowCompany company = companyRepository.get(companyId);
        company.update(name, address);
        return company.getId();
    }

    @Transactional
    public Long deactivate(Long companyId) {
        YellowCompany company = companyRepository.get(companyId);
        company.deactivate();
        return company.getId();
    }
}
