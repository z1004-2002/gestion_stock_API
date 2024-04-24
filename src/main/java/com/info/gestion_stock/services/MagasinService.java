package com.info.gestion_stock.services;

import com.info.gestion_stock.models.Magasin;
import com.info.gestion_stock.repository.MagasinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MagasinService {
    @Autowired
    private MagasinRepository repository;

    public Magasin createMagasin(Magasin magasin){
        magasin.setActive(true);
        return repository.save(magasin);
    }
    public Magasin getMagasin(UUID id){
        return repository.findById(id).orElseThrow(()->new EntityNotFoundException("Magasin introuvable"));
    }
    public Magasin authenticate(Magasin magasin){
        List<Magasin> m = repository.findByLogin(magasin.getLogin());
        if (!m.isEmpty()){
            if(magasin.getPassword().equals(m.get(0).getPassword()) && m.get(0).getActive()){
                return m.get(0);
            };
        }
        return null;
    }
    public List<Magasin> getAllMagasin(){
        return repository.findAll();
    }
    public Boolean changeActive(UUID id){
        Optional<Object> s = Optional.ofNullable(repository.findById(id).map(a -> {
            a.setActive(!a.getActive());
            return repository.save(a);
        }).orElseThrow(() -> new EntityNotFoundException("Magasin introuvable")));
        return true;
    }
    public Magasin updateMagasin(UUID id, Magasin magasin){
        Optional<Object> s = Optional.ofNullable(repository.findById(id).map(a -> {
            a.setLogin(magasin.getLogin());
            a.setPassword(magasin.getPassword());
            return repository.save(a);
        }).orElseThrow(() -> new EntityNotFoundException("Magasin introuvable")));
        return magasin;
    }
}
