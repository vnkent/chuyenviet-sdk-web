/**
 *
 */
package vn.chuyenviet.sdk.web.demo.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * @author haint
 */
@ComponentScan("vn.chuyenviet.sdk.web.demo")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/webjars/**", "/swagger-ui.html", "/swagger-resources/**", "/images/**", "/v2/api-docs", "/oauth/uncache_approvals", "/oauth/cache_approvals", "/health",
                "/api/nonauth/**", "/register", "/login", "/api/public/**", "/", "/csrf", "/favicon.ico", "/api/internal/**")
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf().disable().authorizeRequests().antMatchers("/api-docs/**", "/swagger-ui.html", "/health", "/api/nonauth/**", "/api/public/**").permitAll()
    }
}