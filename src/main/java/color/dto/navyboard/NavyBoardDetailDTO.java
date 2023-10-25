package color.dto.navyboard;

import color.domain.NavyBoard;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;


@Getter
public class NavyBoardDetailDTO {
    private Long id;
    private String title;
    private String content;
    private String userName;
    private String company;
    private LocalDateTime createTime;
    @Nullable
    private LocalDateTime updateTime;

    public NavyBoardDetailDTO(NavyBoard board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.userName = board.getUser().getName();
        this.createTime = board.getCreateTime();
        this.updateTime = board.getUpdateTime();
        this.company = board.getUser().getCompany().getName();
    }
}
