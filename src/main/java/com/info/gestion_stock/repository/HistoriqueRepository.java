package com.info.gestion_stock.repository;

import com.info.gestion_stock.models.Admin;
import com.info.gestion_stock.models.Historique;
import com.info.gestion_stock.models.Magasin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface HistoriqueRepository extends JpaRepository<Historique, UUID> {
    @Query("select h from Historique h where h.magasin = ?1")
    List<Historique> findByMagasin(Magasin magasin);
}
