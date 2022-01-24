package egovframework.com.config.idgn;

import org.apache.commons.dbcp2.BasicDataSource;
import org.egovframe.rte.fdl.idgnr.impl.EgovTableIdGnrServiceImpl;
import org.egovframe.rte.fdl.idgnr.impl.strategy.EgovIdGnrStrategyImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StsfdgNoConfig {

    @Bean
    public EgovTableIdGnrServiceImpl egovStsfdgNoGnrService(){
        EgovTableIdGnrServiceImpl EgovTableIdGnrServiceImpl = new EgovTableIdGnrServiceImpl();

        EgovTableIdGnrServiceImpl.setDataSource(new BasicDataSource());
        EgovTableIdGnrServiceImpl.setStrategy(stsfdgNoStrategy());
        EgovTableIdGnrServiceImpl.setBlockSize(10);
        EgovTableIdGnrServiceImpl.setTable("COMTECOPSEQ");
        EgovTableIdGnrServiceImpl.setTableName("STSFDG_NO");
        return EgovTableIdGnrServiceImpl;
    }


    public EgovIdGnrStrategyImpl stsfdgNoStrategy(){
        EgovIdGnrStrategyImpl egovIdGnrStrategy = new EgovIdGnrStrategyImpl();
        egovIdGnrStrategy.setCipers(20);

        return egovIdGnrStrategy;
    }
}
