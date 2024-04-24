package com.info.gestion_stock.controllers;

import com.info.gestion_stock.models.Alert;
import com.info.gestion_stock.models.Image;
import com.info.gestion_stock.models.Produit;
import com.info.gestion_stock.services.ImageService;
import com.info.gestion_stock.services.ProduitService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/produit")
@CrossOrigin("*")
@Tag(name = "Produit")
public class ProduitController {
    @Autowired
    private ProduitService service;
    @Autowired
    private ImageService imageService;

    @PostMapping(path = "/{id_magasin}")
    public Produit createProduit(@PathVariable UUID id_magasin, @RequestBody Produit produit){
        return service.createProduit(produit, id_magasin);
    }
    @GetMapping
    public List<Produit> getAll(){
        return service.getAllproduit();
    }
    @GetMapping(path = "/active")
    public List<Produit> getActive(){
        return service.getActiveProduit();
    }
    @GetMapping(path = "/{id}")
    public Produit getProduit(@PathVariable UUID id){
        return service.getProduit(id);
    }
    @PutMapping(path = "/{id}/{id_magasin}")
    public Produit updateProduit(@PathVariable UUID id,@RequestBody Produit produit,@PathVariable UUID id_magasin){
        return service.updateProduit(produit, id,id_magasin);
    }
    @PutMapping(path = "/add/{id}/{qte}/{id_magasin}")
    public Produit addQte(@PathVariable UUID id,@PathVariable Integer qte,@PathVariable UUID id_magasin){
        return service.addQte(id, qte,id_magasin);
    }
    @PutMapping(path = "/reduce/{id}/{qte}/{id_magasin}")
    public Produit reduceQte(@PathVariable UUID id,@PathVariable Integer qte,@PathVariable UUID id_magasin){
        return service.reduceQte(id, qte,id_magasin);
    }
    @PutMapping(path = "/change/{id}/{id_magasin}")
    public Produit changeActive(@PathVariable UUID id,@PathVariable UUID id_magasin){
        return service.changeActive(id,id_magasin);
    }
    @GetMapping(path = "/alert/{id_magasin}")
    public Alert getAlertByMag(@PathVariable UUID id_magasin){
        return service.getAlertByMagasin(id_magasin);
    }
    @GetMapping(path = "/alert")
    public Alert getAlert(){
        return service.getAlert();
    }

    @GetMapping("/image/{productImageName:.+}")
    public ResponseEntity<Resource> downloadProfileImage(@PathVariable String productImageName,
                                                         HttpServletRequest request) {
        Resource resource = imageService.loadProfileImage(productImageName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Could Not Determine file ");
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
    }
    @PutMapping(path = "/image/add/{productId}")
    public Image addImage(@RequestParam("file") MultipartFile[] file, @PathVariable UUID productId){
        return imageService.submitImage(file[0],productId);
    }
}
