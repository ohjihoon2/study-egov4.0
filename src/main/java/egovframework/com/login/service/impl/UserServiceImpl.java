package egovframework.com.login.service.impl;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.login.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService , UserService {

    private UserDAO userDAO;

    /*@Transactional
    public Long joinUser(MemberDto memberDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        return memberRepository.save(memberDto.toEntity()).getId();
    }*/

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println("UserServiceImpl.loadUserByUsername");
        // TODO Auto-generated method stub
        UserVO user = userDAO.loadUserByUsername(userName);
        System.out.println("user = " + user);

        String auth = userDAO.loadAuthoritiesByUsername(userName);
        System.out.println("auth = " + auth);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(auth));

        if(user == null) {
            throw new UsernameNotFoundException(userName);
        }

//        user.setUserAuthority(authorities.toString());

        return new User(userName, user.getPassword(),authorities);
//        return user;
    }

    @Override
    public List<GrantedAuthority> loadAuthoritiesByUsername(String userId) {
        System.out.println("UserServiceImpl.loadAuthoritiesByUsername");
        String auth = userDAO.loadAuthoritiesByUsername(userId);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(auth));

        return authorities;
    }
}
