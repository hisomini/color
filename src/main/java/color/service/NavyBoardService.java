package color.service;

import color.domain.NavyBoard;
import color.domain.WhiteUser;
import color.dto.navyboard.NavyBoardDetailDTO;
import color.dto.navyboard.NavyBoardSummaryDTO;
import color.repository.NavyBoardRepository;
import color.repository.WhiteUserRepository;
import color.service.exception.error.BusinessException;
import color.service.exception.error.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NavyBoardService {

    private final NavyBoardRepository boardRepository;
    private final WhiteUserRepository userRepository;

    public List<NavyBoardSummaryDTO> list(int offset, int limit) {
        List<NavyBoard> boards = boardRepository.list(offset, limit);
        return boards.stream().map(NavyBoardSummaryDTO::new).collect(Collectors.toList());
    }

    public NavyBoardDetailDTO get(Long id) {
        Optional<NavyBoard> board = boardRepository.get(id);
        if (board.isEmpty()) {
            throw new BusinessException(ErrorMessage.BOARD_NOT_FOUND_ERROR);
        }
        return new NavyBoardDetailDTO(board.get());
    }

    public boolean checkAuthority(Long userId, Long domainId) {
        Optional<NavyBoard> board = boardRepository.get(domainId);
        if (board.isEmpty()) {
            throw new BusinessException(ErrorMessage.BOARD_NOT_FOUND_ERROR);
        }
        if (board.get().getUser().getId().equals(userId)) {
            return true;
        }
        return false;
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
        Optional<NavyBoard> board = boardRepository.get(boardId);
        if (board.isEmpty()) {
            throw new BusinessException(ErrorMessage.BOARD_NOT_FOUND_ERROR);
        }
        board.get().update(title, content);
        return board.get().getId();
    }

    @Transactional
    public Long deactivate(Long boardId) {
        Optional<NavyBoard> board = boardRepository.get(boardId);
        if (board.isEmpty()) {
            throw new BusinessException(ErrorMessage.BOARD_NOT_FOUND_ERROR);
        }
        board.get().deactivate();
        return board.get().getId();
    }

}
