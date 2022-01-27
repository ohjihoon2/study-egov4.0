package egovframework.com.security.filter;

import egovframework.com.login.service.UserService;
import egovframework.com.utl.sim.service.EgovFileScrty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("CustomAuthenticationProvider.authenticate");
        // TODO Auto-generated method stub

//        String loginUserName = String.valueOf(authentication.getPrincipal());
//        String loginPassword = String.valueOf(authentication.getCredentials());

//        System.out.println("loginUserName : " + loginUserName);
//        System.out.println("loginPassword : " + loginPassword);


//        UserDetails userDetails = userService.loadUserByUsername(loginUserName);
//        System.out.println("userDetails.toString() = " + userDetails.toString());
//        if(!matchPassword(loginPassword, userDetails.getPassword())) {
//            throw new BadCredentialsException(loginUserName);
//        }

        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
        System.out.println("authToken = " + authToken);
        String enpassword = "";
        try {
            enpassword = EgovFileScrty.encryptPassword(String.valueOf(authToken.getCredentials()), authToken.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserDetails userDetails = userService.loadUserByUsername(authToken.getName());
        System.out.println("userDetails = " + userDetails);
        System.out.println("provider - userDetail");
        if(userDetails == null){
            throw new UsernameNotFoundException(authToken.getName());
        }

        System.out.println("userDetails.getPassword() = " + userDetails.getPassword());
        System.out.println("enpassword = " + enpassword);

        if(!matchPassword(userDetails.getPassword(), enpassword)) {
            throw new BadCredentialsException("not matching username or password");
        }
        List<GrantedAuthority> authorities = userService.loadAuthoritiesByUsername(authToken.getName());
        Authentication auth = new UsernamePasswordAuthenticationToken(authToken.getPrincipal(), authToken.getCredentials(),authorities);
        System.out.println("auth = " + auth);
        return auth;
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
