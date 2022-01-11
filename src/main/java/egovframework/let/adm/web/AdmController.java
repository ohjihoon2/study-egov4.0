package egovframework.let.adm.web;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.let.cop.bbs.service.BoardVO;
import egovframework.let.cop.bbs.service.EgovBBSManageService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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

	/**
	 * EgovBBSManageService
	 */
	@Resource(name = "EgovBBSManageService")
	private EgovBBSManageService bbsMngService;

	@RequestMapping("")
	public String index(Model model) throws Exception {
//		model.addAttribute("greeting", "Hello Thymeleaf!");
		return "adm/main";

	}
}