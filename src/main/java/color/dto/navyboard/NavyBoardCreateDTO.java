package color.dto.navyboard;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NavyBoardCreateDTO {
    @NotBlank(message = "게시판 제목을 입력해주세요.")
    private String title;
    @NotBlank(message = "게시판 내용을 입력해주세요.")
    private String content;
}
