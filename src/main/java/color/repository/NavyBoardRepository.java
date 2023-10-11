package color.repository;

import color.domain.NavyBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class NavyBoardRepository {

    private final EntityManager manager;

    public void save(NavyBoard board) {
        manager.persist(board);
    }

    public NavyBoard get(Long id) {
        return manager.find(NavyBoard.class, id);
    }

    public List<NavyBoard> list(int offset, int limit) {
        return manager.createQuery("select board from NavyBoard board", NavyBoard.class).setFirstResult(offset)
                .setMaxResults(limit).getResultList();

    }
}
