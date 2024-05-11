package com.microservice.microserviceJobApp.JobController;



import com.microservice.microserviceJobApp.DTO.JobDTO;
import com.microservice.microserviceJobApp.JobService.JobService;
import com.microservice.microserviceJobApp.JobEntity.Job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // getting all job
    @GetMapping
    public ResponseEntity<List<JobDTO>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    //posting all job
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        //Company c = job.getCompany();
        return new ResponseEntity<>("job Created Successfully", HttpStatus.CREATED);
    }

    // fetching job by Id
    @GetMapping("/{Id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long Id) {
        JobDTO jobWithCompanyDTO = jobService.getJobById(Id);
        if (jobWithCompanyDTO != null)
            return new ResponseEntity<>(jobWithCompanyDTO, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Deleting Job by Id

    @DeleteMapping("/{Id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long Id) {
        boolean deleted = jobService.deleteJobById(Id);
        if (deleted)
            return new ResponseEntity<>("job deleted successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/{Id}")
   // @RequestMapping(value = "/jobs/{Id}", method = RequestMethod.PUT)
    public ResponseEntity<String> UpdateJob(@PathVariable Long Id, @RequestBody Job UpdateJob) {
        Job job;
        boolean updated = jobService.UpdatejobById(Id, UpdateJob);
        if (updated)
            return new ResponseEntity<>("job updated successfully", HttpStatus.OK);

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }
};






