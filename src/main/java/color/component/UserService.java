package color.component;

import color.domain.WhiteUser;
import color.repository.WhiteUserRepository;
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
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(email);
        }
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setEmail(user.get().getEmail());
        customUserDetails.setPassword(user.get().getPassword());
        return customUserDetails;
    }
}
