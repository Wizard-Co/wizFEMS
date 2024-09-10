package wizard.fems.common.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wizard.fems.common.commonMapper;
import wizard.fems.common.dto.User;
import wizard.fems.common.security.domain.FemsUserDetails;

/**
 * packageName      : wizard.fems.common.security.service
 * fileName         : FemsUserDetailsService
 * author           : sooJeong
 * date             : 2024-09-09
 * ======================================================
 * DATE             AUTHOR               NOTE
 * ------------------------------------------------------
 * 2024-09-09         sooJeong             최초 생성
 */

@Service
@RequiredArgsConstructor
public class FemsUserDetailsService implements UserDetailsService {

    @Autowired
    private final commonMapper mapper;

    @Override
    public FemsUserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = mapper.getUser(id);
        if(user != null){
            return new FemsUserDetails(user);
        }
        throw new UsernameNotFoundException("아이디를 찾지 못했습니다: " + id);
    }
}
