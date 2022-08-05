package fx.fixgia.userms.security;



import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LoginFilter extends OncePerRequestFilter {


    private final AuthenticationManager authenticationManager;

    private final JwtHelper jwtHelper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {

        var username = request.getHeader("username");
        var password = request.getHeader("password");

        var authenticated = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));


        response.setHeader(HttpHeaders.AUTHORIZATION, createJwtToken(authenticated));
    }

    private String createJwtToken(Authentication authenticated) {
        User user = (User) authenticated.getPrincipal();
        String roleString = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining());
        return jwtHelper.createToken(user.getUsername(), Map.of("roles", roleString));
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        String method = request.getMethod();
        String uri = request.getRequestURI();
        boolean isLogin = HttpMethod.POST.matches(method) && uri.startsWith("/login");

        return !isLogin;
    }
}
