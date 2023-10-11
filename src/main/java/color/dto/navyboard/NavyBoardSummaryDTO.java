package color.dto.navyboard;

import org.springframework.lang.Nullable;
import java.time.LocalDateTime;

public class NavyBoardSummaryDTO {

    private Long id;
    private String title;
    private String userName;
    private LocalDateTime createTime;
    @Nullable
    private LocalDateTime updateTime;
    private String status;


    public NavyBoardSummaryDTO(Long id, String title, String userName, LocalDateTime createTime, LocalDateTime updateTime, boolean status) {
        this.id = id;
        this.title = title;
        this.userName = userName;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = (status == true) ? "활성" : "비활성";
    }
}
