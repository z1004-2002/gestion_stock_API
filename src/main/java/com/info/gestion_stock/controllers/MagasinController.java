package com.info.gestion_stock.controllers;

import com.info.gestion_stock.models.Magasin;
import com.info.gestion_stock.services.MagasinService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/magasin")
@CrossOrigin("*")
@Tag(name = "Magasin")
public class MagasinController {
    @Autowired
    private MagasinService service;

    @PostMapping
    public Magasin createMaagsin(@RequestBody Magasin magasin){
        return service.createMagasin(magasin);
    }
    @GetMapping(path = "/{id}")
    public Magasin getMagasin(@PathVariable UUID id){
        return service.getMagasin(id);
    }
    @PostMapping(path = "/authenticate")
    public Magasin authenticate(@RequestBody Magasin magasin){
        return service.authenticate(magasin);
    }
    @GetMapping
    public List<Magasin> getAllMagasin(){
        return service.getAllMagasin();
    }
    @PutMapping(path = "/visibility/{id}")
    public Boolean changeActive(@PathVariable UUID id){
        return service.changeActive(id);
    }
    @PutMapping(path = "/{id}")
    public Magasin updateMagasin(@PathVariable UUID id,@RequestBody Magasin magasin){
        return service.updateMagasin(id,magasin);
    }
}
