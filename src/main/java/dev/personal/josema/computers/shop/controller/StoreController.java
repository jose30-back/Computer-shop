package dev.personal.josema.computers.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.personal.josema.computers.shop.models.Computer;
import dev.personal.josema.computers.shop.models.Store;
import dev.personal.josema.computers.shop.services.StoreService;



@RestController
@RequestMapping("api/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;


    @PostMapping
    public ResponseEntity<Store> createStore(@RequestBody Store store) {
        Store newStore = storeService.createStore(store);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStore);
    }

    // Listar todas las tiendas
    @GetMapping
    public ResponseEntity<List<Store>> listAllStores() {
        return ResponseEntity.ok(storeService.listAllStores());
    }

    // Agregar un computador a la tienda
    @PostMapping("{storeId}/computers")
    public ResponseEntity<Computer> addComputerToStore(
            @PathVariable Long storeId,
            @RequestBody Computer computer) {
        Computer addedComputer = storeService.addComputerToStore(storeId, computer);
        return ResponseEntity.ok(addedComputer);
    }

    
    @DeleteMapping("{storeId}/computers")
    public ResponseEntity<Void> deleteComputersByBrand(
            @PathVariable Long storeId,
            @RequestParam String brand) {
        storeService.deleteComputerByBrand(storeId, brand);
        return ResponseEntity.noContent().build();
    }

    // Buscar computadores en la tienda por marca
    @GetMapping("{storeId}/computers/search")
    public ResponseEntity<List<Computer>> findComputerByBrand(
            @PathVariable Long storeId,
            @RequestParam String brand) {
        List<Computer> computers = storeService.findComputersByBrand(storeId, brand);
        return ResponseEntity.ok(computers);
    }

    // Listar todos los computadores de la tienda
    @GetMapping("{storeId}/computers")
    public ResponseEntity<List<Computer>> listAllComputers(@PathVariable Long storeId) {
        List<Computer> computers = storeService.listAllComputers(storeId);
        return ResponseEntity.ok(computers);
    }

}
