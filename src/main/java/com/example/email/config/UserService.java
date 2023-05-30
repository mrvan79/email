//package com.example.email.config;
//import java.util.HashSet;
//import java.util.Set;
//
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
//@Service 
//public class UserService implements UserDetailsService {
// 
////    @Autowired
////    private LoginService userRepository;
// 
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        LoginModel user = userRepository.selectLoginByUsername(username);
////        if (user == null) {
////            throw new UsernameNotFoundException("User not found with username: " + username);
////        }
////        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
////    }
// 
//    private Set<SimpleGrantedAuthority> getAuthority(LoginModel user) {
//        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRoles()));
//        return authorities;
//    }
//}