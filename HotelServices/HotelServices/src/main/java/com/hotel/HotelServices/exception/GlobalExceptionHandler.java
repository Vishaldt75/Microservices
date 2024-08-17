package com.hotel.HotelServices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;


public class GlobalExceptionHandler {

    public ResponseEntity<Map<String, Object>> notFoundHandler(ResourceNotFoundException exception)
    {
        Map map=new HashMap();
        map.put("Message",exception.getMessage());
        map.put("Success",false);
        map.put("Status", HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }
}
