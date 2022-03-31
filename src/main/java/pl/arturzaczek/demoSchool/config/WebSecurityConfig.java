package pl.arturzaczek.demoSchool.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final DataSource dataSource;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
/**
 *       Roles:
 *       -USER,
 *       -TEACHER,
 *       -PRINCIPAL,
 *       -ADMIN,
 *       -STUDENT,
 *       -PARENT,
 */
                .antMatchers("/studentProfile/*").hasAnyRole("PRINCIPAL", "ADMIN", "TEACHER")
                .antMatchers("/rest/student/*").hasAnyRole("PRINCIPAL", "ADMIN", "TEACHER")
                .anyRequest().permitAll()
                .and()
                .csrf().disable().headers().frameOptions().disable()
                .and()
                .formLogin()
                .loginPage("/user/login-form")
                .usernameParameter("email")
                .passwordParameter("password")
                .failureUrl("/user/loginError")
                .loginProcessingUrl("/login-post-by-spring")
                .defaultSuccessUrl("/home")
                .and()
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/index")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT u.email, u.password_hash, 1 FROM user u WHERE u.email = ? ")
                .authoritiesByUsernameQuery("SELECT u.email, r.role_name FROM USER u JOIN user_role ur ON u.id = ur.user_id JOIN ROLE r ON ur.ROLE_ID = r.id WHERE u.email = ?")
                .passwordEncoder(passwordEncoder);
    }
}
