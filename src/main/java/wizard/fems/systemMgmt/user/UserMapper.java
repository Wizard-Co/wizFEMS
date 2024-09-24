package wizard.fems.systemMgmt.user;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * packageName      : wizard.fems.systemMgmt.user
 * fileName         : UserMapper
 * author           : sooJeong
 * date             : 2024-09-19
 * ======================================================
 * DATE             AUTHOR               NOTE
 * ------------------------------------------------------
 * 2024-09-19         sooJeong             최초 생성
 */

@Mapper
public interface UserMapper {
    List<User> getUserList(Map<String, Object> param);
    User getUserDetail(String userID);
    User saveUserDetail(User user);
    User updateUserDetail(User user);
    void deleteUser(String userID);

}
