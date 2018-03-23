package com.workshop.vehiclelocationclient;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class VehicleClientController {

    @Autowired
    private RestTemplate restTemplate;

    //Comment out next line for WebMock testing since we are running locally with no running locator service
    //@HystrixCommand(fallbackMethod = "getDefaultVehicleDetails")
    @RequestMapping(method = RequestMethod.GET, value = "/locate")
    public Vehicle getDetails(@RequestParam(name = "vehicle") String vehicle) {

        //Comment out next line for WebMock testing since we are running locally with no running locator service
        //UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://vehicle-location-service/locate");

        //UnComment next line for WebMock testing since we are running locally with no running locator service
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:8090/locate");
        builder.queryParam("vehicle", vehicle);
        ResponseEntity<Vehicle> lookupV = restTemplate.getForEntity(builder.toUriString(), Vehicle.class);

        return lookupV.getBody();
    }


    public Vehicle getDefaultVehicleDetails(@RequestParam(name = "vehicle") String vehicle) {

        return new Vehicle("NULL_VEHICLE", "Total Loss!");
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Vehicle {

    String id;
    String description;
}