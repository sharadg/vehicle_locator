# vehicle_locator
## Vehicle Locator Application Assets

This repo contains modules for Vehicle Locator application template for a customer workshop

`vehicle-location-client` houses the consumer end of the application

`vehicle-location-service` houses the server piece which houses the smarts to track vehicle locations using GPS and incoming sensor data

`service-registry` houses the eureka server piece which keeps the registry of variuous modules

`config-server` retains all the configuration pieces

1. Create Service Registry in Spring Cloud Services on PCF  
  `cf create-service p-service-registry standard vehicle-service-registry`
  
2. Create Circuit Breaker Dashboard also in SCS on PCF  
  `cf create-service p-circuit-breaker-dashboard standard vehicle-circuit-breaker`
  
3. First, demonstrate the Spring Cloud Contracts piece by going to `vehicle-location-service` and demoing the groovy contract templates.  
  Use `mvn clean package` to create tests and stubs, and then `mvn install` to install the maven repository for consumer testing
4. Then, change over to `vehicle-location-client` and run the stubbed out Wiremock service functionality to test consumer in isolation.
  Once, the consumer piece is finished, make sure to uncomment the following pieces to run in full-online mode (i.e. location service is running and accessible)

  In [pom.xml](./vehicle-location-client/pom.xml)
  ```
  <dependency>
          <groupId>io.pivotal.spring.cloud</groupId>
          <artifactId>spring-cloud-services-starter-circuit-breaker</artifactId>
  </dependency>
  ```

  In [VehicleClientController.java](./vehicle-location-client/src/main/java/com/workshop/vehiclelocationclient/VehicleClientController.java)
  ```
  @HystrixCommand(fallbackMethod = "getDefaultVehicleDetails")
  public Vehicle getDetails(@RequestParam(name = "vehicle") String vehicle) {
  
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://vehicle-location-service/locate");
  ```

  In [VehicleLocationClientApplication.java](./vehicle-location-client/src/main/java/com/workshop/vehiclelocationclient/VehicleLocationClientApplication.java)
  ```
  @LoadBalanced
  ```


