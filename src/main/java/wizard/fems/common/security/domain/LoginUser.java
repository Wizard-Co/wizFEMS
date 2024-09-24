package wizard.fems.common.security.domain;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * packageName      : wizard.fems.common.security.domain
 * fileName         : LoginUser
 * author           : sooJeong
 * date             : 2024-09-11
 * ======================================================
 * DATE             AUTHOR               NOTE
 * ------------------------------------------------------
 * 2024-09-11         sooJeong             최초 생성
 */

@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : user")
public @interface LoginUser {

}