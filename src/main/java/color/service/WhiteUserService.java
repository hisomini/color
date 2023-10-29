package color.service;


import color.domain.WhiteUser;
import color.domain.YellowCompany;
import color.dto.whiteuser.WhiteUserLoginDTO;
import color.dto.whiteuser.WhiteUserSignupDTO;
import color.repository.WhiteUserRepository;
import color.repository.YellowCompanyRepository;
import color.service.exception.error.BusinessException;
import color.service.exception.error.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WhiteUserService {

    private final WhiteUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final YellowCompanyRepository companyRepository;

    @Transactional
    public Long signUp(WhiteUserSignupDTO signupDTO) {
        Optional<WhiteUser> user = userRepository.getByEmail(signupDTO.getEmail());
        if (!user.isEmpty()) {
            throw new BusinessException(ErrorMessage.USER_ALREADY_EXISTS_ERROR);
        }
        Optional<YellowCompany> company = companyRepository.get(Long.parseLong(signupDTO.getCompanyId()));
        if (company.isEmpty()) {
            throw new BusinessException(ErrorMessage.COMPANY_NOT_FOUND_ERROR);
        }
        WhiteUser new_user = WhiteUser.createUser(
                signupDTO.getName(),
                signupDTO.getEmail(),
                signupDTO.getPhone(),
                passwordEncoder.encode(signupDTO.getPassword()),
                signupDTO.getPosition(),
                company.get()
        );
        userRepository.save(new_user);
        return new_user.getId();
    }

    @Transactional
    public WhiteUser login(WhiteUserLoginDTO loginDTO) {
        Optional<WhiteUser> user = userRepository.getByEmail(loginDTO.getEmail());
        if (!user.isEmpty() && passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword())) {
            user.get().login();
            return user.get();
        }
        return null;
    }

    public boolean existByEmail(String email) {


        return false;
    }
}
