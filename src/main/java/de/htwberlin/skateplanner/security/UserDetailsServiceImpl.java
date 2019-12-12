package de.htwberlin.skateplanner.security;

import de.htwberlin.skateplanner.registration.UserRegistrationDto;
import de.htwberlin.skateplanner.user.UserEntity;
import de.htwberlin.skateplanner.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findUserEntityByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
        return user.map(UserDetailsImpl::new).get();
    }

    public boolean checkIfEmailIsRegistered(String email) {
        return userRepository.findUserEntityByEmail(email).isPresent();
    }

    public UserEntity save(UserRegistrationDto registration) {
        UserEntity user = new UserEntity(
                registration.getEmail(),
                registration.getUsername(),
                passwordEncoder.encode(registration.getPassword()),
                SecurityConfig.ROLE_MEMBER
        );
        return userRepository.save(user);
    }

}
