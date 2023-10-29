package color.component;

import color.domain.WhiteUser;
import color.repository.WhiteUserRepository;
import color.service.exception.error.BusinessException;
import color.service.exception.error.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final WhiteUserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<WhiteUser> user = userRepository.getByEmail(email);
        if (user.isEmpty()) {
            new BusinessException(ErrorMessage.USER_NOT_FOUND_ERROR);
        }
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setId(user.get().getId());
        customUserDetails.setEmail(user.get().getEmail());
        customUserDetails.setPassword(user.get().getPassword());
        return customUserDetails;
    }
}
