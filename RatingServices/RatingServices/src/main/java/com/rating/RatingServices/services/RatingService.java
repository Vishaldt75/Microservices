package com.rating.RatingServices.services;

import com.rating.RatingServices.entities.Rating;

import java.util.List;

public interface RatingService {
    Rating createRating(Rating rating);

    List<Rating> getAllRatings();

    List<Rating> getRatingsByUserID(String userId);

    List<Rating> getRatingsByHotelID(String hotelId);

}
