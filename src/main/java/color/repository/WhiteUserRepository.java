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

    public WhiteUser getByEmail(String email) {
        return manager.createQuery("select user from WhiteUser user where user.email = :email", WhiteUser.class).setParameter("email", email).getSingleResult();
    }
}
