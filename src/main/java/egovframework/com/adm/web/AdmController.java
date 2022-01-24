package egovframework.com.adm.web;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import org.egovframe.rte.fdl.cmmn.exception.EgovBizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 템플릿 메인 페이지 컨트롤러 클래스(Sample 소스)
 * @author 실행환경 개발팀 JJY
 * @since 2011.08.31
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2022.01.11  김보검            최초 생성
 *
 * </pre>
 */
@Controller@SessionAttributes(types = ComDefaultVO.class)
@RequestMapping("/adm")
public class AdmController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;


	/**
	 * 운영자 권한을 확인한다.(로그인 여부를 확인한다.)
	 *
	 * @param boardMaster
	 * @throws EgovBizException
	 */
	protected boolean checkAuthority(ModelMap model) throws Exception {
		// 사용자권한 처리
		if(!EgovUserDetailsHelper.isAuthenticated()) {
//			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return false;
		}
		else{
			return true;
		}
	}

	@RequestMapping(value = {"", "/admIndex"})
	public String admIndex(ModelMap model, HttpSession session) throws Exception {
		if (!checkAuthority(model)) {
			// server-side 권한 확인
			return "redirect:/";
		}

		LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
		model.addAttribute("loginVO", loginVO);
		return "adm/admIndex";

	}
}