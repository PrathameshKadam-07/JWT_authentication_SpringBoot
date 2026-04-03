package com.filter;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.entity.UserBean;
import com.service.UserService;
import com.util.JWTHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTfilter extends OncePerRequestFilter {
	@Autowired
	JWTHelper jwthelper;
	
	@Autowired
	UserService userservice;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
		String header = request.getHeader("Authorization");
		
		String token = null;
		String username = null;
		
		if(!header.equals(null) && header.startsWith("Bearer ")) 
		{
			try {
				token = header.substring(7);
				username = jwthelper.extractUsername(token);
				
				if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) 
				{
					 UserDetails ub = userservice.loadUserByUsername(username);
					 
					 if(jwthelper.validatetoken(username, ub, token)) 
					 {
//						 Authentication object is set:- principle:ub,credential:null,authority:ub.getAuthorities
//						 extra details from request store (ip,session id) is saved and set to SecurityContextHolder
						 
						 UsernamePasswordAuthenticationToken up = new UsernamePasswordAuthenticationToken(ub,null,ub.getAuthorities());
						 up.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						 SecurityContextHolder.getContext().setAuthentication(up);; 
					 }
				}
				
				filterChain.doFilter(request, response);
				
			} catch (Exception e) {
				
								}
		}
		
	}

}
