package color.dto.whiteuser;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class WhiteUserSignupDTO {

    private String name;
    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식을 맞춰주세요.")
    private String email;
    private String phone;
    @Size(min = 8, message = "비밀번호는 8자 이상 입력해야합니다.")
    private String password;
    private String position;

}
