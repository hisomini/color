package color.service;


import color.domain.WhiteUser;
import color.dto.whiteuser.WhiteUserLoginDTO;
import color.dto.whiteuser.WhiteUserSignupDTO;
import color.repository.WhiteUserRepository;
import color.repository.YellowCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WhiteUserService {

    private final WhiteUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long signUp(WhiteUserSignupDTO signupDTO) {
        WhiteUser new_user = WhiteUser.createUser(
                signupDTO.getName(),
                signupDTO.getEmail(),
                signupDTO.getPhone(),
                passwordEncoder.encode(signupDTO.getPassword()),
                signupDTO.getPosition());
        userRepository.save(new_user);
        return new_user.getId();
    }

    @Transactional
    public WhiteUser login(WhiteUserLoginDTO loginDTO) {
        Optional<WhiteUser> user = userRepository.getByEmail(loginDTO.getEmail());
        if (user.isPresent() && passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword())) {
            user.get().login();
            return user.get();
        }
        return null;
    }

    public boolean existByEmail(String email) {
        Optional<WhiteUser> user = userRepository.getByEmail(email);
        if (user.isPresent()) {
            return true;
        }
        return false;
    }
}
