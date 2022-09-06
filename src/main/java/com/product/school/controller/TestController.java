package com.product.school.controller;

import com.product.school.data.security.UserDetail;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping
    @PostAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public String test(){
        return "Test Service OK!";
    }


    @RequestMapping(value = "/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String roleTest(HttpServletRequest request, Authentication authentication){
        Principal principal = request.getUserPrincipal();
        principal.getName();
        UserDetail userDetail=(UserDetail) authentication.getPrincipal();
        return "Admin Test Service OK!="+principal.getName()+" -- "+userDetail.getToken();
    }


}
