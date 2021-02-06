package academy.config;

import academy.service.UserAcademyService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;

@EnableWebSecurity
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityApplication  extends WebSecurityConfigurerAdapter {

    private final UserAcademyService userAcademyService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()// tirar o disable .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) dar and
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        log.info("Password Encoded {}", passwordEncoder.encode("teste"));
        log.info("Password Encoded {}", passwordEncoder.encode("teste123"));
//        auth.inMemoryAuthentication()
//                .withUser("almeidaluan")
//                .password(passwordEncoder.encode("teste123"))
//                .roles("USER","ADMIN")
//                .and()
//                .withUser("dev")
//                .password(passwordEncoder.encode("senhadev"))
//                .roles("USER");
        auth.userDetailsService(userAcademyService).passwordEncoder(passwordEncoder);

    }


}
