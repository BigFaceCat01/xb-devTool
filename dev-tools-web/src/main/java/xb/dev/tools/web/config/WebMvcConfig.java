//package xb.dev.tools.web.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.access.expression.SecurityExpressionHandler;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
//import org.springframework.web.servlet.LocaleResolver;
//import org.springframework.web.servlet.i18n.SessionLocaleResolver;
//
//import javax.annotation.Resource;
//import java.util.Locale;
//
///**
// * @Author: Created by huangxb on 2018-08-16 15:56:42
// * @Description:
// */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebMvcConfig extends WebSecurityConfigurerAdapter {
//
//    @Resource
//    private UserDetailsService userDetailsService;
//
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setHideUserNotFoundExceptions(false);
//        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(passwordEncoder());
//        provider.setMessageSource(new MyReloadableResourceBundleMessageSource());
//        auth.authenticationProvider(provider);
//
//    }
//
//    @Bean
//    public AuthenticationEntryPoint myLoginUrlAuthenticationEntryPoint() {
//        return new MyLoginUrlAuthenticationEntryPoint("/login");
//    }
//
//    // 不需要登录的链接
//    private static final String[] PERMIT_URLS = new String[]{"/js/**", "/html/**", "/css/**", "/images/**", "/plugs/**", "/favicon.ico"};
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers(PERMIT_URLS).permitAll().anyRequest().authenticated()
//                // 设置登录界面
//                .and().formLogin().loginPage("/login").loginProcessingUrl("/loginUser")
//                .successHandler(new LoginSuccessHandler())// 登录成功
//                .failureHandler(new LoginFailureHandler())//
//                .permitAll().and().logout().permitAll().and().exceptionHandling()
//                .authenticationEntryPoint(myLoginUrlAuthenticationEntryPoint())
////				.and().rememberMe().rememberMeServices(rememberMeServices()).key("INTERNAL_SECRET_KEY")//remember me
//                .and().csrf().disable()//
//        ;
//        // 用户一处登录
////        http.sessionManagement().maximumSessions(1).sessionRegistry(new SessionRegistryImpl()).expiredUrl("/login");
//    }
//
//    @Resource
//    private MyPermissionEvaluator myPermissionEvaluator;
//
//    /**
//     * 表达式控制器
//     */
//    @Bean
//    public SecurityExpressionHandler<?> webSecurityExpressionHandler() {
//        DefaultWebSecurityExpressionHandler webSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
//        webSecurityExpressionHandler.setPermissionEvaluator(myPermissionEvaluator);
//        return webSecurityExpressionHandler;
//    }
//
//    @Bean
//    public LocaleResolver localeResolver() {
//        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
//        sessionLocaleResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
//        return sessionLocaleResolver;
//    }
//}
