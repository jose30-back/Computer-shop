package dev.personal.josema.computers.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.personal.josema.computers.shop.models.Store;

public interface StoreRepository extends JpaRepository <Store, Long> {
    
}
