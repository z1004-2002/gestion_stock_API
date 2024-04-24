package com.info.gestion_stock.controllers;


import com.info.gestion_stock.models.Admin;
import com.info.gestion_stock.services.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin")
@CrossOrigin("*")
@Tag(name = "Admin")
public class AdminController {
    @Autowired
    private AdminService service;

    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin){
        return service.createAdmin(service.createAdmin(admin));
    }
    @PostMapping("/authenticate")
    public Boolean authenticate(@RequestBody Admin admin){
        return service.authentification(admin);
    }
    @GetMapping(path = "/get")
    public Admin getAdmin(){
        return service.getAdmin();
    }
    @PutMapping
    public Admin updateAdmin(@RequestBody Admin admin){
        return service.update(admin);
    }
}
