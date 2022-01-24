package egovframework.com.config.idgn;

import org.apache.commons.dbcp2.BasicDataSource;
import org.egovframe.rte.fdl.idgnr.impl.EgovTableIdGnrServiceImpl;
import org.egovframe.rte.fdl.idgnr.impl.strategy.EgovIdGnrStrategyImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnswerNoConfig {

    @Bean
    public EgovTableIdGnrServiceImpl egovAnswerNoGnrService(){
        EgovTableIdGnrServiceImpl EgovTableIdGnrServiceImpl = new EgovTableIdGnrServiceImpl();

        EgovTableIdGnrServiceImpl.setDataSource(new BasicDataSource());
        EgovTableIdGnrServiceImpl.setStrategy(answerNoStrategy());
        EgovTableIdGnrServiceImpl.setBlockSize(10);
        EgovTableIdGnrServiceImpl.setTable("COMTECOPSEQ");
        EgovTableIdGnrServiceImpl.setTableName("ANSWER_NO");
        return EgovTableIdGnrServiceImpl;
    }


    public EgovIdGnrStrategyImpl answerNoStrategy(){
        EgovIdGnrStrategyImpl egovIdGnrStrategy = new EgovIdGnrStrategyImpl();
        egovIdGnrStrategy.setCipers(20);

        return egovIdGnrStrategy;
    }
}
