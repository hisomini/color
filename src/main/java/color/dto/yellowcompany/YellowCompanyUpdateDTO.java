package color.dto.yellowcompany;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class YellowCompanyUpdateDTO {
    @NotBlank(message = "회사명을 입력해주세요.")
    private String name;
    @NotBlank(message = "주소를 입력해주세요.")
    private String address;
}
