package config.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ott.ott_project.controller.LoginController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestURI = request.getRequestURI();
        System.out.println("[interceptor] : " + requestURI);
        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute(LoginController.SessionConst.LOGIN_MEMBER) == null) {
            // 로그인 되지 않음
            System.out.println("[미인증 사용자 요청]");

            //로그인으로 redirect
            response.sendRedirect("/login?redirectURL=" + requestURI);
            return false;
        }
        // 로그인 되어있을 떄
        return true;
    }
}

