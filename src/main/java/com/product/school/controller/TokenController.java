package com.product.school.controller;

import com.product.school.data.User;
import com.product.school.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class TokenController {

    private JwtGenerator jwtGenerator;

    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping()
    public String generate(@RequestBody final User jwtUser){
        return jwtGenerator.generate(jwtUser);
    }
}
