package egovframework.com.cmm.web;

import egovframework.com.cmm.LoginVO;

import javax.servlet.http.HttpSession;

public class UserController {
    public LoginVO user(HttpSession session) {
        LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
        return loginVO;
    }
}
