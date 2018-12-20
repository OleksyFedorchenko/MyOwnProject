package ua.cosmetology.config.jwt;

import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Arrays;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import ua.cosmetology.config.SecurityConstant;

@Component
public class JwtTokenProvider {
	
	public String generateToken(Authentication authentication) {
		
		String authorites = authentication.getAuthorities()
				.stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		
		Date now = new Date();
		Date validity = new Date(now.getTime() + SecurityConstant.ACCESS_TOKEN_VALIDITY_SECONDS);
		
		return Jwts.builder()
				.setSubject(authentication.getName())
				.claim(SecurityConstant.AUTHORITIES_KEY, authorites)
				.signWith(SignatureAlgorithm.HS256, SecurityConstant.SIGNIN_KEY)
				.setIssuedAt(now)
				.setExpiration(validity)
				.compact();
		
	}
	
	public UsernamePasswordAuthenticationToken getAuthentication(String token, Authentication existAuth, UserDetails userDetails) {
		JwtParser jwtParser = Jwts.parser().setSigningKey(SecurityConstant.SIGNIN_KEY);
		Jws<Claims> claimsJwt = jwtParser.parseClaimsJws(token);
		Claims claims = claimsJwt.getBody();
		
		Collection<? extends GrantedAuthority> authorities = 
				Arrays.stream(claims.get(SecurityConstant.AUTHORITIES_KEY).toString().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
		
	}
	
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		String email = getEmailFromToken(token);
		
		return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
		
	}
	
	public String getEmailFromToken(String token) {
		
		return getClaimsFromToken(token, Claims::getSubject);
	}
	
	public Date getExpirationDateFromToken(String token) {
		return getClaimsFromToken(token, Claims::getExpiration);
	}
	
	private Claims getAllClaimsFromToken(String token) {
		
		return Jwts.parser()
				.setSigningKey(SecurityConstant.SIGNIN_KEY)
				.parseClaimsJws(token)
				.getBody();
	}
	
	private Boolean isTokenExpired(String token) {
		Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	public <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
		Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

}
