package com.Facturacion;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class ProjectConfig implements WebMvcConfigurer{
    //Los siguientes metodos son para hacer uso de internacionalizacion
    @Bean
    public LocaleResolver localeResolver(){
        var slr=new SessionLocaleResolver();
        
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locale");
        slr.setTimeZoneAttributeName("session.current.timezone");
        
        return slr;
    }
    
    @Bean
    public LocaleChangeInterceptor LocaleChangeInterceptor(){
        var lci=new LocaleChangeInterceptor();
        
        lci.setParamName("lang");
        
        return lci;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registro){
        registro.addInterceptor(LocaleChangeInterceptor());
    }
    
    //Bean para utilizar los textos de mensaje en una clase java
    
    @Bean("messagesSource")
    public MessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("message");
        messageSource.setDefaultEncoding("UTF-8");
        
        return messageSource;
    }
    
    /* Los siguiente métodos son para implementar el tema de seguridad dentro del proyecto */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        //registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registro/nuevo").setViewName("/registro/nuevo");
        registry.addViewController("/report_bug/listado").setViewName("listado");
        registry.addViewController("/report_bug/fragmentos").setViewName("fragmentos");
        registry.addViewController("/report_bug/report_bug").setViewName("report_bug");
        registry.addViewController("/reportes/principal").setViewName("principal");
        
    }
    
    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                .requestMatchers("/","/index","/errores/**",
                        "/carrito/**",
                        "/registro/**","/js/**","/webjars/**","/categoria/listado","/categoria/colas","/categoria/diademas",
                        "/report_bug/**", "/report_bug/listado",
                        "/report_bug/fragmentos","/report_bug/listado",
                        "/reportes/principal", "/reportes/*")
                        .permitAll() // se le permite ingresar a todos a lo de arriba 
                .requestMatchers(
                        "/categoria/listado","/categoria/colas","/categoria/diademas",
                        "/producto/nuevo","/producto/guardar",
                        "/producto/modificar/**","/producto/eliminar/**",
                        "/categoria/nuevo","/categoria/guardar","/categoria/listado",
                        "/categoria/modificar/**","/categoria/eliminar/**",
                        "/usuario/nuevo","/usuario/guardar",
                        "/usuario/modificar/**","/usuario/eliminar/** ","/registro/nuevo"
                ).hasRole("ADMIN") //solo los admins pueden ver lo de arriba
                
                .requestMatchers(
                        "/categoria/listado","/categoria/colas","/categoria/diademas","/facturar/carrito")
                .hasRole("USER") //solo usuarios pueden ver lo de arriba
                )
                .formLogin((form) -> form
                .loginPage("/index").permitAll())
                .logout((logout) -> logout.permitAll());
        http
            .authorizeHttpRequests((authorize) -> authorize
            .anyRequest().permitAll()
              );
        return http.build();
    }*/

    // El siguiente método se utiliza para completar la clase no es 
    //realmente funcional, la próxima semana se reemplaza con usuarios de BD   
    @Bean
    public UserDetailsService users() {
        UserDetails admin = User.builder()
                .username("juan")
                .password("{noop}123")
                .roles("USER",  "ADMIN")
                .build();
        UserDetails sales = User.builder()
                .username("rebeca")
                .password("{noop}456")
                .roles("USER")
                .build();
        UserDetails user = User.builder()
                .username("pedro")
                .password("{noop}789")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user, sales, admin);
    }
    
    @Autowired UserDetailsService userDetailsService;
    
    @Autowired 
    public void configurerGlobal(AuthenticationManagerBuilder amb) throws Exception {
        amb.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
