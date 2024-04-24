package com.info.gestion_stock.services;

import com.info.gestion_stock.models.*;
import com.info.gestion_stock.repository.HistoriqueRepository;
import com.info.gestion_stock.repository.ProduitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
public class ProduitService {
    @Autowired
    private ProduitRepository repository;
    @Autowired
    private MagasinService magasinService;
    @Autowired
    private HistoriqueRepository historiqueRepository;
    @Autowired
    private ImageService imageService;

    public Produit createProduit(Produit produit, UUID id_magasin){
        produit.setActive(true);
        produit.setMagasin(magasinService.getMagasin(id_magasin));
        Produit p = repository.save(produit);
        historiqueRepository.save(new Historique("creation","Ajout du produit "+p.getIntitule()+" dans la base de données",p.getQte(), LocalDate.now(),p, magasinService.getMagasin(id_magasin)));
        return p;
    }
    public List<Produit> getAllproduit(){
        return repository.findAll();
    }
    public Produit getProduit(UUID id){
        return repository.findById(id).orElseThrow(()->new EntityNotFoundException("Produit introuvable"));
    }
    public Produit changeActive(UUID id,UUID id_magasin){
        Optional<Object> s = Optional.ofNullable(repository.findById(id).map(a -> {
            a.setActive(!a.getActive());
            return repository.save(a);
        }).orElseThrow(() -> new EntityNotFoundException("Produit introuvable")));
        Produit p =  repository.findById(id).get();
        if (p.getActive())
            historiqueRepository.save(new Historique("activation","Activation du produit "+p.getIntitule(),p.getQte(), LocalDate.now(),p, magasinService.getMagasin(id_magasin)));
        else
            historiqueRepository.save(new Historique("deactivation","Desactivation du produit "+p.getIntitule(),p.getQte(), LocalDate.now(),p));
        return p;
    }
    public Produit addQte(UUID id, Integer qte,UUID id_magasin){
        Optional<Object> s = Optional.ofNullable(repository.findById(id).map(a -> {
            Integer i = a.getQte()+qte;
            a.setQte(i);
            return repository.save(a);
        }).orElseThrow(() -> new EntityNotFoundException("Produit introuvable")));
        Produit p =  repository.findById(id).get();
        historiqueRepository.save(new Historique("ajout de quantité","Ajouter de "+qte+" pour le produit "+p.getIntitule(),qte, LocalDate.now(),p, magasinService.getMagasin(id_magasin)));
        return p;
    }
    public Produit reduceQte(UUID id, Integer qte,UUID id_magasin){
        Optional<Object> s = Optional.ofNullable(repository.findById(id).map(a -> {
            Integer i = a.getQte()-qte;
            a.setQte(i);
            return repository.save(a);
        }).orElseThrow(() -> new EntityNotFoundException("Produit introuvable")));
        Produit p =  repository.findById(id).get();
        historiqueRepository.save(new Historique("reduction de quantité","Reduction de "+qte+" pour le produit "+p.getIntitule(),qte, LocalDate.now(),p, magasinService.getMagasin(id_magasin)));
        return p;
    }
    public Produit updateProduit(Produit produit, UUID id,UUID id_magasin){
        Optional<Object> s = Optional.ofNullable(repository.findById(id).map(a -> {
            a.setIntitule(produit.getIntitule());
            a.setDescription(produit.getDescription());
            return repository.save(a);
        }).orElseThrow(() -> new EntityNotFoundException("Produit introuvable")));
        Produit p =  repository.findById(id).get();
        historiqueRepository.save(new Historique("Modification Produit","Modification des information du produit p",p.getQte(), LocalDate.now(),p, magasinService.getMagasin(id_magasin)));
        return p;
    }
    public List<Produit> getActiveProduit(){
        return repository.findActive(true);
    }
    public Alert getAlertByMagasin(UUID id_magasin){
        List<Produit> rouge = new ArrayList<>();
        List<Produit> orange = new ArrayList<>();
        List<Produit> produits = magasinService.getMagasin(id_magasin).getProduits();
        for (Produit p:produits){
            if (p.getQte()<10){
                rouge.add(p);
            } else if (p.getQte()<20) {
                orange.add(p);
            }
        }
        return new Alert(rouge,orange);
    }
    public Alert getAlert(){
        List<Produit> rouge = new ArrayList<>();
        List<Produit> orange = new ArrayList<>();
        List<Produit> produits = repository.findAll();
        for (Produit p:produits){
            if (p.getQte()<10){
                rouge.add(p);
            } else if (p.getQte()<20) {
                orange.add(p);
            }
        }
        return new Alert(rouge,orange);
    }
}
