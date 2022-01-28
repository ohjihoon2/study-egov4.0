package egovframework.com.security.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        System.out.println("CustomLoginSuccessHandler.onAuthenticationSuccess");
        //session 에 'SPRING_SECURITY_CONTEXT' 로 저장됨
        SecurityContextHolder.getContext().setAuthentication(authentication);

        response.sendRedirect("/index");
    }

}
