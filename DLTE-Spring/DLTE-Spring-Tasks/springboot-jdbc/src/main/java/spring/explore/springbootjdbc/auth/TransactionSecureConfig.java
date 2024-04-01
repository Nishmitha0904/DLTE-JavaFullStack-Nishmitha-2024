package spring.explore.springbootjdbc.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import spring.explore.springbootjdbc.security.UsersMyBankService;

@Configuration
public class TransactionSecureConfig {
        @Autowired
        UsersMyBankService service;
        AuthenticationManager authenticationManager;

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
                httpSecurity.httpBasic();
                httpSecurity.formLogin();
                httpSecurity.csrf().disable();

                httpSecurity.authorizeRequests().antMatchers("/profilr/register").permitAll();
                httpSecurity.authorizeRequests().antMatchers("/transaction/new").hasAuthority("admin");
                httpSecurity.authorizeRequests().antMatchers("/transaction/sender/*").hasAuthority("customer");
                httpSecurity.authorizeRequests().antMatchers("/transaction/receiver/*").hasAuthority("customer");
                httpSecurity.authorizeRequests().antMatchers("/transaction/amount/*").hasAuthority("customer");
                httpSecurity.authorizeRequests().antMatchers(HttpMethod.PUT).hasAnyAuthority("manager", "admin");
                httpSecurity.authorizeRequests().antMatchers(HttpMethod.DELETE).hasAuthority("admin");

                httpSecurity.authorizeRequests().anyRequest().authenticated();

                AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
                builder.userDetailsService(service);
                authenticationManager = builder.build();
                httpSecurity.authenticationManager(authenticationManager);

                return httpSecurity.build();
        }

}
