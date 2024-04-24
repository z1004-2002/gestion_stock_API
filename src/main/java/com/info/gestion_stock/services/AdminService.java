package com.info.gestion_stock.services;

import com.info.gestion_stock.models.Admin;
import com.info.gestion_stock.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository repository;

    public Admin createAdmin(Admin admin){
        List<Admin> admins = repository.findAll();
        if (admins.isEmpty()){
            return repository.save(admin);
        }else {
            return null;
        }

    }
    public Boolean authentification(Admin admin){
        Admin ad = repository.findByLogin(admin.getLogin());
        if (ad==null){
            return false;
        }else {
            return admin.getPassword().equals(ad.getPassword());
        }
    }
    public Admin getAdmin(){
        List<Admin> admins = repository.findAll();
        if (admins.isEmpty()){
            return null;
        }else {
            return admins.get(0);
        }
    }
    public Admin update(Admin admin){
        List<Admin> admins = repository.findAll();
        if (admins.isEmpty()){
            return null;
        }else {
            Admin ad = admins.get(0);
            ad.setLogin(admin.getLogin());
            ad.setPassword(admin.getPassword());
            return repository.save(ad);
        }
    }
}
