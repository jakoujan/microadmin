package com.mcss.microadmin;

import com.mcss.mcom.Communicator;
import com.mcss.mcom.serial.PortConfig;
import com.mcss.mcom.serial.PortData;
import com.mcss.mcom.serial.SerialCommunicator;
import com.mcss.microadmin.data.dto.Store;
import com.mcss.microadmin.data.dto.TicketData;
import java.sql.SQLException;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableWebSecurity
@PropertySource(encoding = "UTF-8", value = {"file:configuration.properties"})
public class Microadmin extends WebSecurityConfigurerAdapter {

    public static String REALM = "APPLICATION_REALM";

    public static void main(String[] args) throws SQLException {
        Server.createTcpServer("-tcpAllowOthers").start();
        SpringApplication.run(Microadmin.class, args);
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    @Qualifier("customUSerDetailsService")
    private UserDetailsService customUserDetailsService;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
            }
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
                .and().authorizeRequests()
                .antMatchers("/", "/index.html", "/security/login").permitAll()
                .antMatchers("/**").permitAll()
                .antMatchers("/ws/**").permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
        return new CustomBasicAuthenticationEntryPoint();
    }

    @Bean
    public TicketData ticketData(@Value("${ticket.printer}") String printerName, @Value("${ticket.header}") String header, @Value("${ticket.footer}") String footer,
            @Value("${ticket.message.1}") String messageOne, @Value("${ticket.message.2}") String messageTwo) {
        return new TicketData(printerName, header, footer, messageOne, messageTwo);
    }

    @Bean
    public Store store(@Value("${store.name}") String name,
            @Value("${store.bussinesName}") String bussinesName,
            @Value("${store.taxId}") String taxId,
            @Value("${store.street}") String street,
            @Value("${store.external}") String external,
            @Value("${store.internal}") String internal,
            @Value("${store.colony}") String colony,
            @Value("${store.city}") String city,
            @Value("${store.county}") String county,
            @Value("${store.state}") int state,
            @Value("${store.country}") int country,
            @Value("${store.postalCode}") String postalCode,
            @Value("${store.phoneNumber}") String phoneNumber,
            @Value("${store.email}") String email,
            @Value("${store.webpage}") String webpage,
            @Value("${store.taxRegime}") String taxRegime) {
        return new Store(name, bussinesName, taxId, street, external, internal, colony, city, county, state, country, postalCode, phoneNumber, email, webpage, taxRegime);
    }

    @Bean
    PortConfig portConfig(@Value("${port.name}") String name, @Value("${port.baudrate}") Integer baudRate,
            @Value("${port.parity}") Integer parity, @Value("${port.databits}") Integer dataBits,
            @Value("${port.stopbits}") Integer stopBits) {
        return new PortConfig(name, baudRate, PortData.findParity(parity), dataBits, PortData.findStopBits(stopBits));
    }

    @Bean
    Communicator communicator(@Autowired PortConfig portConfig) {
        return new SerialCommunicator(portConfig, null);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

}
