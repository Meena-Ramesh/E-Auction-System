/*
 * package com.eas.security;
 * 
 * import java.util.Arrays; import java.util.Collection;
 * 
 * import org.springframework.security.core.GrantedAuthority; import
 * org.springframework.security.core.authority.SimpleGrantedAuthority; import
 * org.springframework.security.core.userdetails.UserDetails;
 * 
 * import com.eas.entity.User;
 * 
 * public class MyUserDetails implements UserDetails {
 * 
 * private int userId; private String userName; private String password; private
 * GrantedAuthority authority;
 * 
 * public MyUserDetails() { // TODO Auto-generated constructor stub }
 * 
 * public MyUserDetails(User user) { userName =
 * String.valueOf(user.getUserId()); userId = user.getUserId(); password =
 * user.getPassword(); System.out.println(user.getUserType().toString());
 * authority = new SimpleGrantedAuthority(user.getUserType().toString()); }
 * 
 * @Override public Collection<? extends GrantedAuthority> getAuthorities() {
 * return Arrays.asList(authority); }
 * 
 * @Override public String getPassword() { return password; }
 * 
 * @Override public String getUsername() { return userName; }
 * 
 * @Override public boolean isAccountNonExpired() { return true; }
 * 
 * @Override public boolean isAccountNonLocked() { return true; }
 * 
 * @Override public boolean isCredentialsNonExpired() { return true; }
 * 
 * @Override public boolean isEnabled() { return true; }
 * 
 * public void setUserId(int userId) { this.userId = userId; }
 * 
 * public void setUserName(String userName) { this.userName = userName; }
 * 
 * public void setPassword(String password) { this.password = password; }
 * 
 * public void setAuthority(GrantedAuthority authority) { this.authority =
 * authority; }
 * 
 * }
 */