package com.hotel.HotelServices.controller;

import com.hotel.HotelServices.entities.Hotel;
import com.hotel.HotelServices.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }

    @GetMapping("/{hotelID}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelID)
    {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotel(hotelID));
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels()
    {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAllHotels());
    }

}
