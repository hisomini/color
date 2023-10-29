package color.dto.whiteuser;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class WhiteUserLoginDTO {
    @NotBlank(message = "아이디(이메일)을 입력해주세요.")
    private String email;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
