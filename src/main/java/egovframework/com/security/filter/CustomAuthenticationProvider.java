package egovframework.com.security.filter;

import egovframework.com.cmm.LoginVO;
import egovframework.com.login.service.UserService;
import egovframework.com.login.service.impl.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("CustomAuthenticationProvider.authenticate");
        // TODO Auto-generated method stub

        System.out.println("authentication : " + authentication);

        String loginUserName = String.valueOf(authentication.getPrincipal());
        String loginPassword = String.valueOf(authentication.getCredentials());
        System.out.println("loginUserName : " + loginUserName);
        System.out.println("loginPassword : " + loginPassword);

        UserDetails userDetails = userService.loadUserByUsername(loginUserName);
        System.out.println("userDetails.toString() = " + userDetails.toString());
        if(!matchPassword(loginPassword, userDetails.getPassword())) {

            throw new BadCredentialsException(loginUserName);
        }

        /*
        if(!loginVO.isEnabled()) {
            throw new BadCredentialsException(loginUserName);
        }
        */

//        return new UsernamePasswordAuthenticationToken(loginUserName, loginPassword, userDetails.getAuthorities());
        return new UsernamePasswordAuthenticationToken(loginUserName, loginPassword);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // TODO Auto-generated method stub
        return true;
    }

    private boolean matchPassword(String loginPassword, String password) {

        return loginPassword.equals(password);

    }


}
