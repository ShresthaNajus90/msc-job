package com.microservice.microserviceJobApp.clients;



import com.microservice.microserviceJobApp.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@FeignClient(name = "reviews-Microservice")
public interface ReviewClients {

   @GetMapping("/reviews")
   List<Review> getReviews(@RequestParam("companyId") Long companyId);

}
