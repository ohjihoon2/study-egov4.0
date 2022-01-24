package egovframework.com.config;

import egovframework.com.sym.ccm.zip.service.impl.EgovCcmExcelRdnmadZipMapping;
import egovframework.com.sym.ccm.zip.service.impl.EgovCcmExcelZipMapping;
import org.egovframe.rte.fdl.excel.impl.EgovExcelServiceImpl;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class ExcelConfig {

    @Resource(name = "egovSqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    @Bean
    public EgovExcelServiceImpl excelZipService() throws Exception {
        EgovExcelServiceImpl egovExcelServiceImpl = new EgovExcelServiceImpl();
        egovExcelServiceImpl.setMapClass(new EgovCcmExcelZipMapping().toString());
        egovExcelServiceImpl.setSqlSessionTemplate(sqlSessionTemplate);
        return egovExcelServiceImpl;
    }

    @Bean
    public EgovExcelServiceImpl excelRdnmadZipService() throws Exception {
        EgovExcelServiceImpl egovExcelServiceImpl = new EgovExcelServiceImpl();
        egovExcelServiceImpl.setMapClass(new EgovCcmExcelRdnmadZipMapping().toString());
        egovExcelServiceImpl.setSqlSessionTemplate(sqlSessionTemplate);
        return egovExcelServiceImpl;
    }
}

