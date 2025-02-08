package dev.janssensoftware.memento.infrastructure.auth.persistence.service;

import dev.janssensoftware.memento.domain.model.User;
import dev.janssensoftware.memento.infrastructure.auth.mapper.User_UserDetails_Mapper;
import dev.janssensoftware.memento.infrastructure.auth.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserJpaRepository userRepository;
    private final User_UserDetails_Mapper user_userDetails_mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return user_userDetails_mapper.toUserDetails(user);
    }
}
