package com.info.gestion_stock.models;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "data")
public class FileStorageProperties {
    private String productDir;

    public String getProductDir() {
        return productDir;
    }

    public void setProductDir(String productDir) {
        this.productDir = productDir;
    }
}