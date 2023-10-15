package color.dto.navyboard;

import java.time.LocalDateTime;

public class NavyBoardSummaryDTO {

    private Long id;
    private String title;
    private String userName;
    private LocalDateTime createTime;



    public NavyBoardSummaryDTO(Long id, String title, String userName, LocalDateTime createTime) {
        this.id = id;
        this.title = title;
        this.userName = userName;
        this.createTime = createTime;
    }
}
