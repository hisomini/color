package color.service;

import color.domain.NavyBoard;
import color.domain.WhiteUser;
import color.dto.navyboard.NavyBoardSummaryDTO;
import color.repository.NavyBoardRepository;
import color.repository.WhiteUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NavyBoardService {

    private final NavyBoardRepository boardRepository;
    private final WhiteUserRepository userRepository;

    public List<NavyBoardSummaryDTO> list(int offset, int limit) {
        List<NavyBoard> boards = boardRepository.list(offset, limit);
        return boards.stream().map(board -> {
            return new NavyBoardSummaryDTO(
                    board.getId(),
                    board.getTitle(),
                    board.getUser().getName(),
                    board.getCreateDate(),
                    board.getUpdateDate(),
                    board.is_active()
            );
        }).collect(Collectors.toList());
    }
    @Transactional
    public Long create(Long userId, String title, String content) {
        WhiteUser user = userRepository.get(userId);
        NavyBoard new_board = NavyBoard.createBoard(user, title, content);
        boardRepository.save(new_board);
        return new_board.getId();
    }
    @Transactional
    public Long update(Long boardId, String title, String content) {
        NavyBoard board = boardRepository.get(boardId);
        board.update(title, content);
        return board.getId();
    }
    @Transactional
    public Long deactivate(Long boardId) {
        NavyBoard board = boardRepository.get(boardId);
        board.deactivate();
        return board.getId();
    }

}
