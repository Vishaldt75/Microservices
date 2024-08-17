package com.hotel.HotelServices.services;

import com.hotel.HotelServices.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel create(Hotel hotel);

    List<Hotel> getAllHotels();

    Hotel getHotel(String id);

}
