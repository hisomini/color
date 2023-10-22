package color.dto.navyboard;

import color.domain.NavyBoard;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NavyBoardSummaryDTO {

    private Long id;
    private String title;
    private String userName;
    private LocalDateTime createTime;

    public NavyBoardSummaryDTO(NavyBoard board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.userName = board.getUser().getName();
        this.createTime = board.getCreateTime();
    }
}
