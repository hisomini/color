package color.service;

import color.domain.YellowCompany;
import color.repository.YellowCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class YellowCompanyService {

    private final YellowCompanyRepository companyRepository;

    @Transactional
    public Long create(String name, String address, String representativeName) {
        YellowCompany company = YellowCompany.createCompany(name, address, representativeName);
        companyRepository.save(company);
        return company.getId();
    }
    @Transactional
    public void update(Long companyId, String name, String address) {
        YellowCompany company = companyRepository.get(companyId);
        company.update(name, address);
    }

    public void deactivate(Long companyId) {
        YellowCompany company = companyRepository.get(companyId);
        company.deactivate();
    }
}
