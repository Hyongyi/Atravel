package com.example.demo.config;

import com.example.demo.service.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig  {
    private UserInfoService userInfoService;
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    DataSource dataSource;

    @Bean
    // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/css/**", "/script/**", "/images/**", "/resource/**", "/fragments/**", "/layouts/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.rememberMe() // rememberMe 기능 작동함
                .key("rememberMe")
                .rememberMeParameter("remember") // default: remember-me, checkbox 등의 이름과 맞춰야함
                .tokenValiditySeconds(3600) // 쿠키의 만료시간 설정(초), default: 14일
                .alwaysRemember(false) // 사용자가 체크박스를 활성화하지 않아도 항상 실행, default: false
                .userDetailsService(userDetailsService)
                .tokenRepository(tokenRepository())
                .and()
                .csrf()
                .ignoringAntMatchers("/purchaseTicket", "/purchaseRTTicket", "/result", "/GetUserInfo")
                .and()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
//               .antMatchers("/**").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user/myinfo").hasRole("MEMBER")
//               .anyRequest().authenticated()

                .and() // 로그인 설정
                .formLogin()
                .loginPage("/user/signin")
                .defaultSuccessUrl("/")
                .usernameParameter("id")
                .passwordParameter("password")
                .failureUrl("/user/denied")
                .permitAll()

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and()
                // 403 예외처리 핸들링
                .exceptionHandling().accessDeniedPage("/user/denied");

        http.headers().frameOptions().sameOrigin();

        return http.build();
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        // JDBC 기반의 tokenRepository 구현체
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource); // dataSource 주입
        return jdbcTokenRepository;
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userInfoService).passwordEncoder(passwordEncoder());
    }


}





//
//        @Bean
//        protected void configure (HttpSecurity http) throws Exception {
//            http.authorizeRequests()
//                    // 페이지 권한 설정
//                    .antMatchers("/admin/**").hasRole("ADMIN")
//                    .antMatchers("/user/myinfo").hasRole("MEMBER")
//                    .antMatchers("/**").permitAll()
//                    .and() // 로그인 설정
//                    .formLogin()
//                    .loginPage("/user/signin")
//                    .defaultSuccessUrl("/user/login/result")
//                    .usernameParameter("id")
//                    .permitAll()
//                    .and() // 로그아웃 설정
//                    .logout()
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
//                    .logoutSuccessUrl("/user/logout/result")
//                    .invalidateHttpSession(true)
//                    .and()
//                    // 403 예외처리 핸들링
//                    .exceptionHandling().accessDeniedPage("/user/denied");
//        }
//