package com.user.service;

import com.user.service.entity.Rating;
import com.user.service.externalServices.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {
	@Autowired
	private RatingService ratingService;
	@Test
	void contextLoads() {
	}


/*	@Test
	void createRating()
	{
		Rating rating=Rating.builder().rating(9).userID("6f6cca54-e414-47cb-9ada-73919f525430").hotelID("2fabdc6d-c546-4bb8-9e2c-b46ec2e5b9ee").feedback("This is using Feign client").build();
		Rating rating1 = ratingService.createRating(rating);
		System.out.println("Rating Created ");
	}*/
}
