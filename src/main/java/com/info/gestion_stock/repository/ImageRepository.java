package com.info.gestion_stock.repository;

import com.info.gestion_stock.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
}
