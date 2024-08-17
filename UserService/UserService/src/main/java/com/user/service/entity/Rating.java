package com.user.service.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {
    private String ratingID;
    private String userID;
    private String hotelID;
    private int rating;
    private String feedback;
    private Hotel hotel;
}
