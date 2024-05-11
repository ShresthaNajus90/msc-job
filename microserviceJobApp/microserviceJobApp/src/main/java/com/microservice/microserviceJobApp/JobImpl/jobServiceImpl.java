package com.microservice.microserviceJobApp.JobImpl;


import com.microservice.microserviceJobApp.DTO.JobDTO;
import com.microservice.microserviceJobApp.JobRepository.JobRepository;
import com.microservice.microserviceJobApp.JobService.JobService;
import com.microservice.microserviceJobApp.JobEntity.Job;
import com.microservice.microserviceJobApp.clients.CompanyClients;
import com.microservice.microserviceJobApp.clients.ReviewClients;
import com.microservice.microserviceJobApp.external.Company;
import com.microservice.microserviceJobApp.external.Review;
import com.microservice.microserviceJobApp.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class jobServiceImpl implements JobService {

    @Autowired
    // private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private CompanyClients companyClients;
    @Autowired
    private ReviewClients reviewClients;

    public jobServiceImpl(JobRepository jobRepository, CompanyClients companyClients, ReviewClients reviewClients) {
        this.jobRepository = jobRepository;
        this.companyClients = companyClients;
        this.reviewClients = reviewClients;
    }
    private Long nextId = 1L;

    @Override
    @CircuitBreaker(name="companyBreaker")
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobDTO = new ArrayList<>();
        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());


        // convert to Method level for JobWithCompanyDTO
       /* RestTemplate = new RestTemplate();
        for(Job job : jobs){
            JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
            jobWithCompanyDTO.setJob(job);
            Company company = restTemplate.getForObject
                    ("http://localhost:9094/companies/" + job.getCompanyId(), Company.class);
            jobWithCompanyDTO.setCompany(company);
            jobWithCompanyDTOs.add(jobWithCompanyDTO);
        }

        return jobWithCompanyDTOs;

        */
    }

    private JobDTO convertToDto(Job job){
        Company company = companyClients.getCompany(job.getCompanyId());
        List<Review> reviews = reviewClients.getReviews(job.getCompanyId());
        JobDTO jobDTO = JobMapper.mapToJobWithCompanyDto(job, company, reviews);
        return jobDTO;
           /* JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
            jobWithCompanyDTO.setJob(job);
       RestTemplate restTemplate = new RestTemplate(); */

       // Company company = companyClients.getCompany(job.getCompanyId());

          //restTemplate.getForObject
            //        ("http://Microservice-Company:8082/companies/"+job.getCompanyId(), Company.class);
        /*
       ResponseEntity<List<Review>> reviewResponse =
               restTemplate.exchange("http://reviews-Microservice:8083/reviews?companyId=" + job.getCompanyId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Review>>() {
                });
                */
      // List<Review> reviews = reviewClients.getReviews(job.getCompanyId());
      // List<Review> reviews = reviewResponse.getBody();

          //  JobDTO jobDTO = JobMapper.mapToJobWithCompanyDto(job, company, reviews);
       // jobDTO.setCompany(company);

       // return jobDTO;
    }

    @Override
    public void createJob(Job job) {
       // job.setId(nextId++);
    jobRepository.save(job);
    }

    @Override
    public JobDTO getJobById(Long Id) {
       Job job = jobRepository.findById(Id).orElse(null);
       return convertToDto(job);
           }

    @Override
    public boolean deleteJobById(Long Id) {
     try{
         jobRepository.deleteById(Id);
         return true;
     } catch (Exception e){
         return false;
              }
        }

    @Override
    public boolean UpdatejobById(Long Id, Job UpdateJob) {
        Optional<Job> jobOptional = jobRepository.findById(Id);

            if(jobOptional.isPresent()) {
                Job job = jobOptional.get();
                job.setTitle(UpdateJob.getTitle());
                job.setMinSalary(UpdateJob.getMinSalary());
                job.setMaxSalary(UpdateJob.getMaxSalary());
                job.setLocation(UpdateJob.getLocation());
                jobRepository.save(job);
                return true;
            }
        return false;
        }

    }

