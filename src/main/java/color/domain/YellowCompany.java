package color.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class YellowCompany {
    @Id @GeneratedValue
    @Column(name="company_id")
    private Long id;

    private String name;

    private String address;

    private String representativeName;

    private boolean is_active;

    private YellowCompany(String name, String address, String representativeName) {
        this.name = name;
        this.address = address;
        this.representativeName = representativeName;
        this.is_active = true;
    }

    public static YellowCompany createCompany(String name, String address, String representativeName) {
        return new YellowCompany(name, address, representativeName);
    }

    public void update(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void deactivate() {
        this.is_active = false;
    }
}
