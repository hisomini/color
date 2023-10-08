package color.repository;

import color.domain.WhiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class WhiteUserRepository {

    private final EntityManager manager;

    public void save(WhiteUser user) {
        manager.persist(user);
    }

    public WhiteUser get(Long id) {
        return manager.find(WhiteUser.class, id);
    }

}
