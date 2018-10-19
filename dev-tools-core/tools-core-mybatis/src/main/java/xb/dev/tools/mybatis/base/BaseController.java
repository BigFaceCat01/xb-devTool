package xb.dev.tools.mybatis.base;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Created by huangxb on 2018-08-03 15:59:06
 * @Description: 基础控制类
 */
public class BaseController {
    protected ThreadLocal<Long> localUser = new ThreadLocal<>();

    @ModelAttribute
    public void loginUserInfo(HttpServletRequest request){
        String userId = request.getHeader("X-AUTH-USER");
        if(userId!=null) {
            boolean b = userId.matches("[0-9]+");
            if (b) {
                localUser.set(userId == null ? null : Long.valueOf(userId));
            }
        }
    }
}
