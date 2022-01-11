package egovframework.let.login.web;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes(types = ComDefaultVO.class)
public class LoginController {
    @GetMapping("/login")
    public String login(Model model, HttpSession session) throws Exception {
        LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
        model.addAttribute("loginVO", loginVO);
        return "login/login";
    }
}


