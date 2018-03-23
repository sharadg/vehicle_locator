package com.workshop.vehiclelocationclient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@SpringBootTest
//For Webmock testing - CDC
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureStubRunner(workOffline = true, ids = "com.example:vehicle-location-service:+:8090")
@DirtiesContext
public class VehicleLocationClientApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void contextLoads() {
    }

    @Test
    public void should_return_vehicle_details_if_located() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/locate")
                                              .param("vehicle", "SpringRX")
                                              .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());

    }

}
