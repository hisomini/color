package color.dto.navyboard;

import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

public class NavyBoardDetailDTO {
    private Long id;
    private String title;
    private String content;
    private String userName;
    private LocalDateTime createTime;
    @Nullable
    private LocalDateTime updateTime;

    public NavyBoardDetailDTO(Long id, String title, String content, String userName, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.createTime = createTime;
        this.updateTime = updateTime;

    }
}
