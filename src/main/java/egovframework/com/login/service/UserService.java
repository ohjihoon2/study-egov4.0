package egovframework.com.login.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    public UserDetails loadUserByUsername(String userId);

    public List<GrantedAuthority> loadAuthoritiesByUsername(String userId);
}
