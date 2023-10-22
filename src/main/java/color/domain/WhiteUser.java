package color.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WhiteUser {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String password;

    private String position;

    private LocalDateTime joinDate;

    @Column(nullable = true)
    private LocalDateTime lastLoginDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private YellowCompany company;

    private WhiteUser(String name, String email, String phone, String password, String position) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.position = position;
        this.joinDate = LocalDateTime.now();
        this.lastLoginDate = null;
    }

    public static WhiteUser createUser(String name, String email, String phone, String password, String position) {
        return new WhiteUser(name, email, phone, password, position);
    }

    public void login() {
        this.lastLoginDate = LocalDateTime.now();
    }

    public void updateUser(String name, String phone, String position) {
        this.name = name;
        this.phone = phone;
        this.position = position;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

}
