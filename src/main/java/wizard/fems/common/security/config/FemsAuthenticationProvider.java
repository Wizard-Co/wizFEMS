package wizard.fems.common.security.config;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import wizard.fems.common.security.domain.FemsUserDetails;
import wizard.fems.common.security.service.FemsUserDetailsService;

/**
 * packageName      : wizard.fems.common.security.config
 * fileName         : FemsAuthenticationProvider
 * author           : sooJeong
 * date             : 2024-09-10
 * ======================================================
 * DATE             AUTHOR               NOTE
 * ------------------------------------------------------
 * 2024-09-10         sooJeong             최초 생성
 */
@Component
@AllArgsConstructor
public class FemsAuthenticationProvider implements AuthenticationProvider {

    private final FemsUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userID = authentication.getPrincipal().toString();
        String userPW = authentication.getCredentials().toString();

        FemsUserDetails userDetail = userDetailsService.loadUserByUsername(userID);
        if(!passwordEncoder.matches(userPW, userDetail.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다");
        }

        return new UsernamePasswordAuthenticationToken(userID, userPW, userDetail.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
