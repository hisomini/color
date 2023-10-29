package color.controller;

import color.component.JwtTokenProvider;
import color.domain.WhiteUser;
import color.dto.whiteuser.WhiteUserLoginDTO;
import color.dto.whiteuser.WhiteUserSignupDTO;
import color.service.WhiteUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class WhiteUserController {

    private final WhiteUserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody WhiteUserLoginDTO userLoginDTO, HttpServletResponse response) {
        WhiteUser user = userService.login(userLoginDTO);
        String token = jwtTokenProvider.createToken(user.getEmail());
        response.addHeader("Authorization", token);
        System.out.println(token);
        return "로그인에 성공했습니다";
    }

    @ResponseBody
    @PostMapping("/signup")
    public String signUp(@Valid @RequestBody WhiteUserSignupDTO signupDTO) {
        userService.signUp(signupDTO);
        return "회원가입되었습니다.";
    }
}
