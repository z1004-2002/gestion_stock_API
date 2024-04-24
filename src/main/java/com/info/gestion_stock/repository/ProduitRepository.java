package com.info.gestion_stock.repository;

import com.info.gestion_stock.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ProduitRepository extends JpaRepository<Produit, UUID> {
    @Query("select p from Produit p where p.active = ?1")
    List<Produit> findActive(Boolean active);
}
