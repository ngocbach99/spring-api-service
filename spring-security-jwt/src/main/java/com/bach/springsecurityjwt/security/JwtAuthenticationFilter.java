package com.bach.springsecurityjwt.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bach.springsecurityjwt.dao.UserService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	 @Autowired
	 private UserService customUserDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			
			//Lấy JWT từ request
			String jwt = getJwtFromRequest(request);
			
			if(StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
				//Lấy user người dùng từ chuỗi jwt 
				String username = jwtTokenProvider.getUserFormJWT(jwt);
				//Lấy thông tin người dùng hợp lệ, set thông tin cho Security Context				
				UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
				
				if(userDetails != null) {
					//Nếu người dùng không hợp lệ, set thông tin cho security context.
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities()); //Chứng chỉ - credentials để null
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
				
			}
			
		} catch (Exception e) {
			log.error("failed on set user authentication" , e);
		}
		
		filterChain.doFilter(request, response);
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		//Kiểm tra xem header Authorization có chứa thông tin jwt không
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
			return bearerToken.substring(7);
		}
		return null;
	}

}
