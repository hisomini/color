package color.repository;

import color.domain.NavyBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NavyBoardRepository {

    private final EntityManager manager;

    public void save(NavyBoard board) {
        manager.persist(board);
    }

    public Optional<NavyBoard> get(Long id) {
        List<NavyBoard> boards = manager.createQuery("select board from NavyBoard board where board.is_active=true and board.id = :id", NavyBoard.class).setParameter("id", id).getResultList();
        return boards.stream().findAny();
    }

    public List<NavyBoard> list(int offset, int limit) {
        return manager.createQuery("select board from NavyBoard board where board.is_active=true", NavyBoard.class).setFirstResult(offset)
                .setMaxResults(limit).getResultList();

    }
}
