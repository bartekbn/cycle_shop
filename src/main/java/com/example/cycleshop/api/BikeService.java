package com.example.cycleshop.api;

import com.example.cycleshop.api.model.Bike;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {

    private final BikeRepository bikeRepository;

    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public List<Bike> getAllBikes(){
        return  bikeRepository.findAll();
    }
}
