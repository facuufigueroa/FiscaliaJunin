package Fiscalia.junin.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;



@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    @Qualifier("userServiceImp")
    @Autowired
    public UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/form/**","/css/**","/users/**").permitAll()
                .antMatchers("/causas/**").hasAnyRole("USER")
                .antMatchers("/formCausa/**").hasAnyRole("USER")
                .antMatchers("/detalle/**").hasAnyRole("USER")
                .antMatchers("/editar/**").hasAnyRole("USER")
                .antMatchers("/eliminar/**").hasAnyRole("USER")
                .antMatchers("/formInformacion/**").hasAnyRole("USER")
                .antMatchers("/formHistorialCausa/**").hasAnyRole("USER")
                .antMatchers("/users/**").hasAnyRole("USER")
                .and()
                .formLogin().loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/causas", true)
                .and()
                .logout().permitAll();


    }






}
