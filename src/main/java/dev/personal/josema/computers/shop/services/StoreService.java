package dev.personal.josema.computers.shop.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.personal.josema.computers.shop.models.Computer;
import dev.personal.josema.computers.shop.models.Store;
import dev.personal.josema.computers.shop.repository.ComputerRepository;
import dev.personal.josema.computers.shop.repository.StoreRepository;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ComputerRepository computerRepository;

    // Agregar un computador a la tienda
    public Computer addComputerToStore(Long storeId, Computer computer) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Tienda no encontrada"));
        computer.setStore(store);
        return computerRepository.save(computer);
    }

    // Eliminar un computador de la tienda dada su marca
    public void deleteComputerByBrand(Long storeId, String brand) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Tienda no encontrada"));
        List<Computer> computersToDelete = store.getComputers().stream()
                .filter(computer -> computer.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
        computerRepository.deleteAll(computersToDelete);
    }

    // Buscar computadores en la tienda por marca
    public List<Computer> findComputersByBrand(Long storeId, String brand) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Tienda no encontrada"));
        return store.getComputers().stream()
                .filter(computer -> computer.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    // Listar todos los computadores de la tienda
    public List<Computer> listAllComputers(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Tienda no encontrada"));
        return store.getComputers();
    }

}
