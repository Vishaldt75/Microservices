package com.user.service.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.service.entity.Hotel;
import com.user.service.entity.Rating;
import com.user.service.entity.User;
import com.user.service.exceptions.ResourceNotFound;
import com.user.service.externalServices.HotelService;
import com.user.service.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private HotelService hotelService;
    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User saveUser(User user) {
        String randomUserID= UUID.randomUUID().toString();
        user.setUserID(randomUserID);
        return userRepository.save(user);
    }
    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
    @Override
    public User getUser(String userID) {
        User user=userRepository.findById(userID).orElseThrow(() -> new ResourceNotFound("User By Given ID is not Found : "+userID));
        //ArrayList<Rating> ratingsOfUser= restTemplate.getForObject("http://localhost:8083/ratings/users/" + user.getUserID(), ArrayList.class);
        /*Rating[] ratingsOfUsers=restTemplate.getForObject("http://localhost:8083/ratings/users/" + user.getUserID(), Rating[].class);
        List<Rating> ratings=Arrays.stream(ratingsOfUsers).toList();
*/
        ResponseEntity<List<Rating>> response = restTemplate.exchange(
                "http://RATINGSERVICES/ratings/users/" + user.getUserID(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Rating>>() {}
        );
        List<Rating> ratingsOfUser = response.getBody();
       List<Rating> ratingList= ratingsOfUser.stream().map(rating -> {

         //http://localhost:8082/hotels/2fabdc6d-c546-4bb8-9e2c-b46ec2e5b9ee
           if (rating.getHotelID() == null) {
               logger.warn("Hotel ID is null for rating: {}", rating);
               return rating; // Or handle as appropriate
           }
        // ResponseEntity<Hotel> forEntity=restTemplate.getForEntity("http://HOTELSERVICES/hotels/"+rating.getHotelID(), Hotel.class);
         Hotel hotel=hotelService.getHotel(rating.getHotelID());
         //logger.info("Response status code : {}", forEntity.getStatusCode());
         rating.setHotel(hotel);
          return rating;
      }).collect(Collectors.toList());

       user.setRatings(ratingList);
       return user;

    }

}
