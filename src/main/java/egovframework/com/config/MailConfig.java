package egovframework.com.config;

import egovframework.com.cop.ems.service.EgovMultiPartEmail;
import org.springframework.context.annotation.Bean;

public class MailConfig {
    @Bean
    public EgovMultiPartEmail egovMultiPartEmail(){
        EgovMultiPartEmail egovMultiPartEmail = new EgovMultiPartEmail();

        egovMultiPartEmail.setPort(587);
        egovMultiPartEmail.setHost("smtp.gmail.com");
        egovMultiPartEmail.setId("");
        egovMultiPartEmail.setPassword("");
        egovMultiPartEmail.setSenderName("System-test");
        egovMultiPartEmail.setEmailAddress("");

        return egovMultiPartEmail;
    }
}

