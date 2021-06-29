package by.ilya.catalog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/admin").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/admin/managers/add").hasAnyAuthority("ADMIN")
                .antMatchers("/admin/managers/edit").hasAnyAuthority("ADMIN")
                .antMatchers("/admin/managers/delete").hasAnyAuthority("ADMIN")
                .antMatchers("/").permitAll()
                .antMatchers("/submission").permitAll()
                .antMatchers("/contest").permitAll()
                .antMatchers("/catalog/**").permitAll()
                .antMatchers("/css/**", "/js/**", "/img/**", "/files/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable().cors();
    }



    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println("");
        System.out.println(BCrypt.hashpw("123456", BCrypt.gensalt("$2a", 10)));
    }

    /*@Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }*/
}
