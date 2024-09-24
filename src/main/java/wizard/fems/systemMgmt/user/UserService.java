package wizard.fems.systemMgmt.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wizard.fems.basicMgmt.machine.DTO.MCDTO;

import java.util.HashMap;
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
public class UserService {

    @Autowired
    UserMapper mapper;

    public List<User> getUserList(Map<String, Object> param){
        return mapper.getUserList(param);
    }

    public User getUserDetail(String userID){
        return mapper.getUserDetail(userID);
    }

    public User saveUserDetail(User user){
        return mapper.saveUserDetail(user);
    }

    public User updateUserDetail(User user) {
        return mapper.updateUserDetail(user);
    }

    public void deleteUser(String userID){
        mapper.deleteUser(userID);
    }

}
