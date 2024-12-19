package dev.personal.josema.computers.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.personal.josema.computers.shop.models.Computer;

public interface ComputerRepository extends JpaRepository <Computer, Long> {
    List<Computer> findByBrandContainingIgnoreCase(String brand);
}
