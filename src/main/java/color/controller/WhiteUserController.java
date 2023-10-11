package color.controller;

import color.component.JwtTokenProvider;
import color.domain.WhiteUser;
import color.dto.whiteuser.WhiteUserLoginDTO;
import color.dto.whiteuser.WhiteUserSignupDTO;
import color.service.WhiteUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class WhiteUserController {

    private final WhiteUserService userService;

    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody WhiteUserLoginDTO userLoginDTO, HttpServletResponse response) throws IOException {
        WhiteUser user = userService.login(userLoginDTO);
        if (user == null) {
            response.sendError(401, "아이디 또는 비밀번호가 틀렸습니다");
        }
        String token = JwtTokenProvider.createToken(user.getId());
        Cookie cookie = new Cookie("jwtToken", token);
        cookie.setMaxAge(60 * 60);
        response.addCookie(cookie);
        return "로그인에 성공했습니다";
    }

    @ResponseBody
    @PostMapping("/signUp")
    public String signUp(@RequestBody WhiteUserSignupDTO signupDTO) {
        userService.signUp(signupDTO);
        return "회원가입되었습니다.";
    }
}
