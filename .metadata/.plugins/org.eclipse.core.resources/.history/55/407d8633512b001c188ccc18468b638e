package com.bach.springsecurityjwt.dao;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bach.springsecurityjwt.models.LoginRequest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	LoginRequest user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Tao ban cho mày một chức vụ, làm cho tốt
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		// Mật khẩu là gì , nôn ra
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// Tài khoản nữa, cmn
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// Dùng cháy máy đi, còn lâu mới hết hạn
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// Khóa nó nếu người yêu mày đòi 
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// Mấy cái chức thực, mật khẩu của mày chưa hết hạn đâu yên tâm
		return true;
	}

	@Override
	public boolean isEnabled() {
		// Kích hoạt nè
		return true;
	}

}
