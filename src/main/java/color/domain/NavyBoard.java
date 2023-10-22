package color.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NavyBoard {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private WhiteUser user;

    private String content;

    private String title;

    private LocalDateTime createTime;

    @Column(nullable = true)
    private LocalDateTime updateTime;

    private boolean is_active;

    private NavyBoard(WhiteUser user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.createTime = LocalDateTime.now();
        this.updateTime = null;
        this.is_active = true;
    }

    public static NavyBoard createBoard(WhiteUser user, String title, String content) {
        return new NavyBoard(user, title, content);
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        this.updateTime = LocalDateTime.now();
    }

    public void deactivate() {
        this.is_active = false;
        this.updateTime = LocalDateTime.now();
    }
}
