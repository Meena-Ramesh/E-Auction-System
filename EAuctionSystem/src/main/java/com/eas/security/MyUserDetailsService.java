/*
 * package com.eas.security;
 * 
 * import java.util.Optional;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.stereotype.Service;
 * 
 * import com.eas.entity.User; import
 * com.eas.exception.InvalidInputDataException; import
 * com.eas.repository.UserRepository;
 * 
 * @Service public class MyUserDetailsService implements UserDetailsService {
 * 
 * @Autowired UserRepository userRepository;
 * 
 * @Override public UserDetails loadUserByUsername(String userId) throws
 * UsernameNotFoundException { User user =
 * userRepository.findById(Integer.parseInt(userId)) .orElseThrow(() -> new
 * InvalidInputDataException("Invalid user ID")); return new
 * MyUserDetails(user); }
 * 
 * }
 */