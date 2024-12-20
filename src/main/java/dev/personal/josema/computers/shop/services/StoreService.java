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

    // Método para crear una nueva tienda
    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    // Método para listar todas las tiendas
    public List<Store> listAllStores() {
        return storeRepository.findAll();
    }

    // Método para obtener una tienda por su ID
    public Store getStoreById(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Tienda no encontrada"));
    }

    // Agregar un computador a la tienda
    public Computer addComputerToStore(Long storeId, Computer computer) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Tienda no encontrada"));
        computer.setStore(store);
        System.out.println("Asociando computador con tienda: " + store.getName());
        return computerRepository.save(computer);
    }

    // Eliminar un computador de la tienda dada su marca
    public void deleteComputerByBrand(Long storeId, String brand) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Tienda no encontrada"));
    
        List<Computer> computersToDelete = store.getComputers().stream()
                .filter(computer -> computer.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    
        if (!computersToDelete.isEmpty()) {
            // Eliminar de la lista de la tienda
            store.getComputers().removeAll(computersToDelete);
    
            // Guardar la tienda para sincronizar
            storeRepository.save(store);
    
            // Eliminar de la base de datos
            computerRepository.deleteAll(computersToDelete);
        }
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
        if (store.getComputers() == null || store.getComputers().isEmpty()) {
            System.out.println("No hay computadores en la tienda con ID: " + storeId);
        }
        return store.getComputers();
    }

}
