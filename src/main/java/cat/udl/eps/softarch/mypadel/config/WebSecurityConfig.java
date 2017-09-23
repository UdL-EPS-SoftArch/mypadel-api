package cat.udl.eps.softarch.mypadel.config;

import org.springframework.http.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.*;
import org.springframework.security.web.access.channel.*;

/**
 * Created by http://rhizomik.net/~roberto/
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.PUT, "/**/*").authenticated()
                .antMatchers(HttpMethod.POST, "/**/*").authenticated()
                .antMatchers(HttpMethod.DELETE, "/**/*").authenticated()
                .antMatchers(HttpMethod.PATCH, "/**/*").authenticated()

                .antMatchers(HttpMethod.POST, "/admins*/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/courts*/**").hasRole("ADMIN")

                .anyRequest().permitAll()
                .and()
                .httpBasic().realmName("MyPaddle")
                .and()
                .addFilterBefore(new CORSFilter(), ChannelProcessingFilter.class)
                .csrf().disable();
    }
}
