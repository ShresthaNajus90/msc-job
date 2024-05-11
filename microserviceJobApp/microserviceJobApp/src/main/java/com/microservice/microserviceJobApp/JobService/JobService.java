package com.microservice.microserviceJobApp.JobService;



import com.microservice.microserviceJobApp.DTO.JobDTO;
import com.microservice.microserviceJobApp.JobEntity.Job;

import java.util.List;

public interface JobService {

    List<JobDTO>findAll();
   void createJob(Job job);

    JobDTO getJobById(Long Id);

    boolean deleteJobById(Long Id);

    boolean UpdatejobById(Long Id, Job UpdateJob);
}
