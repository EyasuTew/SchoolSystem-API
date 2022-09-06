package com.product.school.controller;

import com.product.school.data.User;
import com.product.school.dto.request.LoginDto;
import com.product.school.dto.request.UserCreateDto;
import com.product.school.repositories.UserRepository;
import com.product.school.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

   // @PostMapping
//    public ResponseEntity login(@RequestBody final User jwtUser){
//        // check if user is in database
//        System.out.println(jwtUser.toString());
//        if(jwtUser.getUsername().equals("admin") && jwtUser.getPassword().equals("1234")){
//            jwtUser.setRole("ROLE_ADMIN");
//            jwtUser.setId(1l);
//            return ResponseEntity.ok().header("MyToken",jwtGenerator.generate(jwtUser)).body(null);
//        } else if (jwtUser.getUsername().equals("user") && jwtUser.getPassword().equals("1234")){
//            jwtUser.setRole("ROLE_USER");
//            jwtUser.setId(2l);
//            return ResponseEntity.ok().header("MyToken",jwtGenerator.generate(jwtUser)).body(null);
//        } else {
//            return ResponseEntity.badRequest().body(null);
//        }
//
//    }
    @PostMapping
    public ResponseEntity login(@RequestBody final LoginDto loginDto){

        User user=userRepository.findUserByUsername(loginDto.getUsername());
        if(user!=null && passwordEncoder.matches(loginDto.getPassword(),user.getPassword())){
            return ResponseEntity.ok().header("MyToken",jwtGenerator.generate(user)).body(null);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody final UserCreateDto userCreateDto){
        // check if user is in database
        System.out.println(userCreateDto.toString());
        User user=new User();
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        user.setRole(userCreateDto.getRole());
        userRepository.save(user);
        return ResponseEntity.ok().header(
                "Message","Success").body(user);

//        if(jwtUser.getUsername().equals("admin") && jwtUser.getPassword().equals("1234")){
//            jwtUser.setRole("ROLE_ADMIN");
//            jwtUser.setId(1l);
//            return ResponseEntity.ok().header("MyToken",jwtGenerator.generate(jwtUser)).body(null);
//        } else if (jwtUser.getUsername().equals("user") && jwtUser.getPassword().equals("1234")){
//            jwtUser.setRole("ROLE_USER");
//            jwtUser.setId(2l);
//            return ResponseEntity.ok().header("MyToken",jwtGenerator.generate(jwtUser)).body(null);
//        } else {
//            return ResponseEntity.badRequest().body(null);
//        }

    }
}
