package com.example.vehiclelocatorservice;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleController {

/*
    @Value("${server.port}")
    int portInstance;
*/

    @RequestMapping(path = "/locate", method = RequestMethod.GET)
    public Vehicle locateVehicle(@RequestParam(name = "vehicle", required = true) String vehicle) {

        Vehicle lookupV = new Vehicle(vehicle, "Semi-Truck");

//        System.out.println("Request handled by: " + portInstance);
        return lookupV;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Vehicle {

    String id;
    String description;
}