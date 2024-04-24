package com.info.gestion_stock.repository;

import com.info.gestion_stock.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {
    @Query("select a from Admin a where a.login = ?1")
    Admin findByLogin(String login);
}
