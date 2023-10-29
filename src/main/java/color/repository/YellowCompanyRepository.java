package color.repository;

import color.domain.YellowCompany;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class YellowCompanyRepository {

    private final EntityManager manager;

    public void save(YellowCompany company) {
        manager.persist(company);
    }

    public Optional<YellowCompany> get(Long id) {
        List<YellowCompany> companies = manager.createQuery("select company from YellowCompany company where company.id = :id").setParameter("id", id).getResultList();
        return companies.stream().findAny();
    }

    public List<YellowCompany> list(int offset, int limit) {
        return manager.createQuery("select company from YellowCompany company where company.is_active=true", YellowCompany.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
