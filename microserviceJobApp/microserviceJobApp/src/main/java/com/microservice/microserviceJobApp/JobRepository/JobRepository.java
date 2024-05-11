package com.microservice.microserviceJobApp.JobRepository;


import com.microservice.microserviceJobApp.JobEntity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

}
