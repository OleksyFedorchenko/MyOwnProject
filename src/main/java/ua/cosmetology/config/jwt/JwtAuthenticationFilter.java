package ua.cosmetology.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import ua.cosmetology.config.SecurityConstant;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
	private UserDetailsService userDetailsService;
	
    @Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String email = null;
		String authToken = null;
		
		String header = request.getHeader(SecurityConstant.HEADER_NAME);
		
		if(header != null && header.startsWith(SecurityConstant.TOKEN_PREFIX)) {
			authToken = header.replace(SecurityConstant.TOKEN_PREFIX, "");
			
			try {
				
				email = jwtTokenProvider.getEmailFromToken(authToken);
				
			} catch(Exception e) {
				System.out.println("Get Email from token exception");
				e.printStackTrace();
			}
		}else {
			System.out.println("Could not find Bearer token");
		}
		
		 if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			 UserDetails userDetails = userDetailsService.loadUserByUsername(email);
			 
                 if(jwtTokenProvider.validateToken(authToken, userDetails))	{
                	 UsernamePasswordAuthenticationToken authentication = 
                			 jwtTokenProvider.getAuthentication(authToken, SecurityContextHolder.getContext().getAuthentication(), userDetails);
                	 authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                	 System.out.println("User " + email + " added to security contex");
                	 SecurityContextHolder.getContext().setAuthentication(authentication);
                	 
                 }
			 
		 }
		
		filterChain.doFilter(request, response); 
		
	}

}
