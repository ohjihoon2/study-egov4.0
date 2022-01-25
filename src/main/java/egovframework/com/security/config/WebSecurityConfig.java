package egovframework.com.security.config;

import egovframework.com.login.service.impl.UserServiceImpl;
import egovframework.com.security.filter.CustomAuthenticationFilter;
import egovframework.com.security.filter.CustomAuthenticationProvider;
import egovframework.com.security.filter.CustomLoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // 비밀번호 암호화를 위한 서비스 호출
    private UserServiceImpl memberService;

    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        // 암호화
        return new BCryptPasswordEncoder();
    }

    // 정적 자원에 대해서는 Security 설정을 적용하지 않음.
    // WebSecurity는 FilterChainProxy 생성 필터입니다.
    @Override
    public void configure(WebSecurity web) {
        // 해당 경로의 파일들은 spring security가 무시하도록합니다.
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.csrf().disable(); // csrf를 사용할지 여부
        http.authorizeRequests() // HttpServletRequest에 따라접근을 제한
//                .antMatchers("/admin/**").hasRole("ADMIN") // url에 따른 권한만 허용
//                .antMatchers("/info").hasRole("MEMBER") // url에 따른 권한만 허용
                .antMatchers("/**").permitAll(); // url에 따른 모두 허용

        http.formLogin() // form 기반 로그인 인증 관련하며 HttpSession 이용
                .loginPage("/login") // 지정하고 싶은 로그인 페이지 url
                .usernameParameter("id") // 지정하고 싶은 username name 명칭이며, 기본은 username
                .passwordParameter("password") // 지정하고 싶은 password name 명칭이며, 기본은 password
                .defaultSuccessUrl("/index") // 로그인 성공 시 이동페이지
                .permitAll() // 모두 접근 허용
                .and()
                .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//                .csrf() // csrf 사용
//                .ignoringAntMatchers("/h2-console/**") // csrf 제외 url
//                .ignoringAntMatchers("/post/**") // csrf 제외 url
//                .ignoringAntMatchers("/admin/**") // csrf 제외 url
//                .and()

         http.logout() // 로그아웃
                .logoutRequestMatcher(new AntPathRequestMatcher(("/logout"))) // 지정하고 싶은 로그아웃 url
                .logoutSuccessUrl("/index") // 성공 시 이동 페이지
                .invalidateHttpSession(true) // 세션 초기화
//              .deleteCookies("cookies") // 특정 쿠키를 제거
                .and()
                .exceptionHandling() // 에러 처리
                .accessDeniedPage("/error"); // 에러 시 이동할 페이지
    }

    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        System.out.println("WebSecurityConfig.customAuthenticationFilter");
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
        customAuthenticationFilter.setFilterProcessesUrl("/loginproc");
        customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());
        customAuthenticationFilter.afterPropertiesSet();
        return customAuthenticationFilter;
    }

    @Bean
    public CustomLoginSuccessHandler customLoginSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }

    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider(){
        return new CustomAuthenticationProvider();
    }

    /**
     * AuthenticationManagerBuilder
     * AuthenticationManager를 생성합니다. AuthenticationManager는 사용자 인증을 담당
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("WebSecurityConfig.configure");
        auth.authenticationProvider(authProvider);
    }

}
