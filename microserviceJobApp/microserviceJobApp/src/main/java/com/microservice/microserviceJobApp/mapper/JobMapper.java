package com.microservice.microserviceJobApp.mapper;

import com.microservice.microserviceJobApp.DTO.JobDTO;
import com.microservice.microserviceJobApp.JobEntity.Job;
import com.microservice.microserviceJobApp.external.Company;
import com.microservice.microserviceJobApp.external.Review;

import java.util.List;

public class JobMapper {

    public static JobDTO mapToJobWithCompanyDto(Job job, Company company, List<Review> reviews){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);

        return jobDTO;

    }
}
