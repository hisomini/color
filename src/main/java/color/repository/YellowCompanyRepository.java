package color.repository;

import color.domain.YellowCompany;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class YellowCompanyRepository {

    private final EntityManager manager;

    public void save(YellowCompany company) {
        manager.persist(company);
    }

    public YellowCompany get(Long id) {
        return manager.find(YellowCompany.class, id);
    }

    public List<YellowCompany> list() {
        return manager.createQuery("select company from YellowCompany company", YellowCompany.class).getResultList();
    }
}
