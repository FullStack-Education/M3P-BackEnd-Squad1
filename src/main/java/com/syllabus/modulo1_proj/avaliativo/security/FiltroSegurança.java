package com.syllabus.modulo1_proj.avaliativo.security;
import com.syllabus.modulo1_proj.avaliativo.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class FiltroSegurança extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    private final TokenService tokenService;
    private final UsuarioRepository userRepository;

    public FiltroSegurança(TokenService tokenService, UsuarioRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        logger.debug("Aplicando filtro da camada de segurança - getAuthorities.");
        var token = this.recoverToken(request);
        if(token != null){
            var login = tokenService.validateToken(token);
            UserDetails user = userRepository.findByLogin(login);

            if (user != null) {
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Token inválido.");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        logger.debug("Recuperando token - HttpServletRequest.");
        var header = request.getHeader("Authorization");
        if(header == null) return null;
        return header.replace("Bearer ", "");
    }
}
