package com.info.gestion_stock.controllers;

import com.info.gestion_stock.models.Historique;
import com.info.gestion_stock.repository.HistoriqueRepository;
import com.info.gestion_stock.services.MagasinService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/historique")
@CrossOrigin("*")
@Tag(name = "historique")
public class HistoriqueController {
    @Autowired
    private HistoriqueRepository repository;
    @Autowired
    private MagasinService magasinService;
    @GetMapping
    public List<Historique> getAll(){
        return repository.findAll();
    }
    @GetMapping(path = "/{id_mag}")
    public List<Historique> getAllByMag(@PathVariable UUID id_mag){
        return repository.findByMagasin(magasinService.getMagasin(id_mag));
    }
    @GetMapping(path = "/date/{date}")
    public List<Historique> getByDay(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        List<Historique> his = new ArrayList<>();
        List<Historique> historiques =  repository.findAll();
        for (Historique hist:historiques){
            if (hist.getDate().equals(date)){
                his.add(hist);
            }
        }
        return his;
    }
    @GetMapping(path = "/magasin/{date}/{id_mag}")
    public List<Historique> getByDayMag(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,@PathVariable UUID id_mag){
        List<Historique> his = new ArrayList<>();
        List<Historique> historiques =  repository.findByMagasin(magasinService.getMagasin(id_mag));
        for (Historique hist:historiques){
            if (hist.getDate().equals(date)){
                his.add(hist);
            }
        }
        return his;
    }

}
