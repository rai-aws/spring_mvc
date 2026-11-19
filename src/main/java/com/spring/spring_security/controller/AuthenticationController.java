// package com.spring.spring_security.controller;

// import java.util.Collections;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.spring.spring_security.dto.AuthLoginDto;
// import com.spring.spring_security.dto.AuthRoleRegisterDto;
// import com.spring.spring_security.dto.AuthUserRegisterDto;
// import com.spring.spring_security.entity.Auth_Role;
// import com.spring.spring_security.entity.Auth_User;
// import com.spring.spring_security.repository.AuthRoleRepository;
// import com.spring.spring_security.repository.AuthUserRepository;

// import jakarta.validation.Valid;

// /**
//  * REST controller responsible for user registration, role registration,
//  * and user authentication.
//  */
// @RestController 
// @RequestMapping ("/api/auth")
// public class AuthenticationController {
//     private AuthenticationManager authenticationManager;
//     private AuthUserRepository authUserRepo;
//     private AuthRoleRepository authRoleRepo;
//     private BCryptPasswordEncoder passwordEncoder;

//     /**
//      * Creates the authentication controller with the required dependencies.
//      *
//      * @param authenticationManager handles user authentication
//      * @param authUserRepository repository for accessing user records
//      * @param authRoleRepo repository for accessing role records
//      * @param passwordEncoder encodes user passwords
//      */
//     public AuthenticationController
//                         (AuthenticationManager authenticationManager, 
//                         AuthUserRepository authUserRepository,
//                         AuthRoleRepository authRoleRepo, 
//                         BCryptPasswordEncoder passwordEncoder) {
//         this.authenticationManager = authenticationManager;
//         this.authUserRepo = authUserRepository;
//         this.authRoleRepo = authRoleRepo;
//         this.passwordEncoder = passwordEncoder;
//     }

//     /**
//      * Registers a new authorized role if it does not already exist.
//      *
//      * @param authRoleRegisterDto contains the name of the role to register
//      * @return response indicating whether the role was successfully created
//      */
//     @PostMapping("/register_authorized_role")
//     public ResponseEntity<String> register_authRole(
//                         @RequestBody AuthRoleRegisterDto authRoleRegisterDto){
//         if(authRoleRepo.existsByName(authRoleRegisterDto.getName())) {
//             return new ResponseEntity<>(authRoleRegisterDto.getName() + 
//                 " already created.", HttpStatus.BAD_REQUEST);
//         }

//         Auth_Role auth_role = new Auth_Role();
//         auth_role.setName(authRoleRegisterDto.getName());

//         authRoleRepo.save(auth_role);

//         return new ResponseEntity<>(auth_role.getName() + 
//                 " is successfully created.", HttpStatus.OK);  
//     }

//     /**
//      * Registers a new user with an encoded password and the default USER role.
//      *
//      * @param authUserRegisterDto contains the username and password
//      * @return response indicating whether the user was successfully registered
//      */
//     @PostMapping("/register_authorized_user")
//     public ResponseEntity<String> register_authUser(
//                         @Valid @RequestBody 
//                         AuthUserRegisterDto authUserRegisterDto){
//         if (authUserRepo.existsByUsername(authUserRegisterDto.getUsername())){
//             return new ResponseEntity<>(authUserRegisterDto.getUsername() + 
//                 " is already taken.",  HttpStatus.BAD_REQUEST);
//         }

//         Auth_User auth_user = new Auth_User();
//         auth_user.setUsername(authUserRegisterDto.getUsername());
//         auth_user.setPassword(passwordEncoder
//                             .encode(authUserRegisterDto.getPassword()));

//         Auth_Role auth_role = authRoleRepo.findByName("USER")
//                                 .orElseThrow(() -> 
//                                 new RuntimeException("Roles USER not found"));
//         auth_user.setRoles(Collections.singletonList(auth_role));

//         authUserRepo.save(auth_user);

//         return new ResponseEntity<>(auth_user.getUsername() + 
//                 " is successfully registered", HttpStatus.OK);
//     }   
    
//     /**
//      * Authenticates a user using the provided username and password.
//      *
//      * @param authLoginDto contains the login credentials
//      * @return response indicating successful authentication
//      */
//     @PostMapping ("/auth_login")
//     public ResponseEntity<String> login(
//                         @Valid @RequestBody AuthLoginDto authLoginDto){
//         Authentication authenticate = authenticationManager.authenticate(
//             new UsernamePasswordAuthenticationToken(
//                         authLoginDto.getUsername(), 
//                         authLoginDto.getPassword()));

//         SecurityContextHolder.getContext().setAuthentication(authenticate);

//         return new ResponseEntity<>("Sign in successfully!", HttpStatus.OK);
//     }
// }
