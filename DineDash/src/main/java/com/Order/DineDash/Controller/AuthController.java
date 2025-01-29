package com.Order.DineDash.Controller;

import com.Order.DineDash.Request.LoginRequest;
import com.Order.DineDash.Response.AuthResponse;
import com.Order.DineDash.Service.CustomerUserDetailService;
import com.Order.DineDash.config.JwtProvider;
import com.Order.DineDash.model.Cart;
import com.Order.DineDash.model.USER_ROLE;
import com.Order.DineDash.model.User;
import com.Order.DineDash.repository.CartRepository;
import com.Order.DineDash.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CustomerUserDetailService customerUserDetailService;
    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/Signup")

    public ResponseEntity<AuthResponse> createUserhandler(@RequestBody User user) throws Exception {

        User isEmptyExist=userRepository.findByEmail(user.getEmail());
        if(isEmptyExist!=null){
            throw new Exception("Email is Already Used with another account");
        }

        User createUser=new User();
        createUser.setId(user.getId());
        createUser.setEmail(user.getEmail());
        createUser.setFullname(user.getFullname());
        createUser.setRole(user.getRole());
        createUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser=userRepository.save(createUser);

        Cart cart=new Cart();
        cart.setCustomer(savedUser);
        cartRepository.save(cart);

        Authentication authentication=new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt=jwtProvider.generateToken(authentication);
        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("register Success");
        authResponse.setRole(savedUser.getRole());

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/Signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest req) throws Exception {

        String username=req.getEmail();
        String password=req.getPassword();

        Authentication authentication= authenticate(username,password);

        Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
        String role =authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

        String jwt=jwtProvider.generateToken(authentication);

        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login Success");
        authResponse.setRole(USER_ROLE.valueOf(role));

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customerUserDetailService.loadUserByUsername(username);

        if (userDetails==null){
            throw new BadCredentialsException("Invalid username..");
        }

        if (!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password..");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
