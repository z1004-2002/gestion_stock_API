package com.info.gestion_stock.services;

import com.info.gestion_stock.models.FileStorageProperties;
import com.info.gestion_stock.models.Image;
import com.info.gestion_stock.repository.ImageRepository;
import com.info.gestion_stock.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageService {
    ImageRepository imageRepository;
    private Path fileStorageLocationProduct;
    @Autowired
    ProduitRepository produitRepository;
    private String dir = "/src/main/resources/static/products";
    @Autowired
    public ImageService(
            ImageRepository imageRepository
    ) {
        super();
        this.imageRepository = imageRepository;
        this.fileStorageLocationProduct = Paths
                .get(System.getProperty("user.dir") + dir ).toAbsolutePath()
                .normalize();
        try {
            Files.createDirectories(this.fileStorageLocationProduct);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory to upload.");
        }
    }

    public Image submitImage(MultipartFile file, UUID productId) {

        //GENERATION OF VARCHAR

        String completeName = "abe";
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder s = new StringBuilder(50);
        for (int i = 0; i < 50; i++) {
            int index = (int)(str.length() * Math.random());
            s.append(str.charAt(index));
        }
        completeName = String.valueOf(s);

        //END OF GENERATION

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            Path targetLocation = this.fileStorageLocationProduct.resolve(completeName+fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(System.getProperty("user.dir") + "/src/main/resources/static/products/").path(completeName + fileName)
                    .toUriString();
            Image i = imageRepository.save(
                    new Image( completeName+fileName, fileDownloadUri, file.getSize(), file.getContentType()));

            Optional<Object> t = Optional.ofNullable(produitRepository.findById(productId).map(a -> {
                a.setImage(i);
                return produitRepository.save(a);
            }).orElseThrow(() -> new EntityNotFoundException("Produit introuvable")));
            return i;
        } catch (IOException e) {
            throw new RuntimeException("Could not store file " + completeName+fileName + ". Please try again!", e);
        }
    }
    public Resource loadProfileImage(String fileName) {
        try {
            Path filePath = this.fileStorageLocationProduct.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("Le fichier: " + fileName + " est introuvable");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Le fichier: " + fileName + " est introuvable");
        }
    }
}
