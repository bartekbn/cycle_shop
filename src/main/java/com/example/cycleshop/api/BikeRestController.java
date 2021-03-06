package com.example.cycleshop.api;

import com.example.cycleshop.api.model.Bike;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BikeRestController {

    private BikeService bikeService;

    public BikeRestController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping("/bikes")
    public ResponseEntity<List<Bike>> getAllBikes() {
        List<Bike> bikes = bikeService.getAllBikes();
        if (bikes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bikes, HttpStatus.OK);
    }

    @GetMapping("/bikes/{id}")
    public ResponseEntity<Bike> getBikeId(@PathVariable Long id) {
        Bike bike = bikeService.getBikeById(id);
        if (bike == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bike, HttpStatus.OK);
    }

    @PostMapping("/bikes")
    public ResponseEntity<Bike> createOrUpdateBike(@RequestBody Bike bike) {
        if (bike.getId() == null) {
            return new ResponseEntity<>(bikeService.createOrUpdateBike(bike), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(bikeService.createOrUpdateBike(bike), HttpStatus.OK);
    }

    @DeleteMapping("/bikes/{id}/delete")
    public  ResponseEntity<?> deleteBikeById(@PathVariable Long id) {
        if (bikeService.deleteBikeById(id) > 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    //endpoint pozwalający na podnoszenie zbiorcze cen rowerów o odpowiedniej wartości
    @GetMapping("/bikes/{value}/price")
    public void switchPrice(@PathVariable BigDecimal value) {
        bikeService.switchPrice(value);
    }
}
