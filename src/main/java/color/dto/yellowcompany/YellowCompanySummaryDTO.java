package color.dto.yellowcompany;

import lombok.Getter;

@Getter
public class YellowCompanySummaryDTO {

    private Long id;
    private String name;
    private String address;
    private String representativeName;

    public YellowCompanySummaryDTO(Long id, String name, String address, String representativeName) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.representativeName = representativeName;
    }
}
