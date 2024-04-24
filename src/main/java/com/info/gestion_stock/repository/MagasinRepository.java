package com.info.gestion_stock.repository;

import com.info.gestion_stock.models.Magasin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MagasinRepository extends JpaRepository<Magasin, UUID> {
    @Query("select m from Magasin m where m.login = ?1")
    List<Magasin> findByLogin(String login);
}
