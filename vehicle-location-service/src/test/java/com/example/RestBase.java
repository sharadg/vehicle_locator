package com.example;

import com.example.vehiclelocatorservice.VehicleController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public abstract class RestBase {

    @InjectMocks
    VehicleController vehicleController;

    @Before
    public void setup() {

        RestAssuredMockMvc.standaloneSetup(vehicleController);
    }
}
