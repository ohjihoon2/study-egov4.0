package egovframework.com.login.service.impl;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;

@Repository("memberDAO")
public class UserDAO extends EgovComAbstractDAO {

    public UserVO loadUserByUsername(String userId) {
        return (UserVO)selectOne("LoginUsrSecurity.loadUserByUsername", userId);
    }

    public String loadAuthoritiesByUsername(String userId){
        return selectOne("LoginUsrSecurity.loadAuthoritiesByUsername", userId);
    }
}
