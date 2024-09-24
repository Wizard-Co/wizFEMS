package wizard.fems.systemMgmt.user;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wizard.fems.common.security.domain.FemsUserDetails;
import wizard.fems.common.security.service.FemsUserDetailsService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * packageName      : wizard.fems.systemMgmt.user
 * fileName         : UserService
 * author           : sooJeong
 * date             : 2024-09-19
 * ======================================================
 * DATE             AUTHOR               NOTE
 * ------------------------------------------------------
 * 2024-09-19         sooJeong             최초 생성
 */
@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final FemsUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    public List<User> getUserList(Map<String, Object> param) {
        return mapper.getUserList(param);
    }

    public User getUserDetail(String userID) {
        return mapper.getUserDetail(userID);
    }

    public User saveUserDetail(User loginUser, User user) {

        checkPassword(loginUser, user);
        return mapper.saveUserDetail(user);
    }

    public User updateUserDetail(User loginUser, User user){

        checkPassword(loginUser, user);
        return mapper.updateUserDetail(user);
    }

    public void deleteUser(String userID) {
        mapper.deleteUser(userID);
    }

    private void checkPassword(User loginUser, User user) {
        String userID = loginUser.getUserID();
        String inputPW = user.getPassword();

        FemsUserDetails userDetail = userDetailsService.loadUserByUsername(userID);

        if (!passwordEncoder.matches(inputPW, userDetail.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다");
        }
    }
}
