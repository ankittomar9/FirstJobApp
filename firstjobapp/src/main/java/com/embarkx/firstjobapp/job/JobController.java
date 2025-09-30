package com.embarkx.firstjobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
// @RestController is a REST-ful web service controller. that combines the functionality of both @Controller and @ResponseBody. --->@Controller → Handles HTTP requests and returns a view name (like an HTML page).---> @ResponseBody → Tells Spring to take the method’s return value and directly write it as JSON or XML in the HTTP response.
//@RequestMapping("/jobs")  // when @RequestMapping is applied at a class level it sets a base URL (/jobs....) path for all the methods that are handling requests in that particular controller
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {    //This is constructor-based dependency injection.
        this.jobService = jobService;
    }

    // GET /jobs: Get all jobs
    // ResponseEntity<> in Spring Boot is a special class that represents the entire HTTP response — not just the data (body), but also the status code and headers.  📦 ResponseEntity = [HTTP Status] + [Headers] + [Body]
    // HttpStatus is the class in Spring is basically a list of constants that represent all standard HTTP status codes and their meaning.
    @GetMapping("/jobs")
    // @RequestMapping(value = "/jobs", method = RequestMethod.GET)
    public ResponseEntity<List<Job>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    // POST/jobs: Create a new job (request body should contain the job details)
    // @RequestBody in the context of web development and APIs refers to the data sent by a client  to a server as part of an HTTP request. This data can be in various formats, such as JSON, XML, form data, or plain text, depending on the application's requirements
    @PostMapping("/jobs")
    // @RequestMapping(value = "/jobs", method = RequestMethod.POST)
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job added Succesfully", HttpStatus.CREATED);
    }

    // GET /jobs/{id}: Get a specific job by ID
    // @PathVariable makes sure whatever var is in the URL path is assigned to the particular variable defined  with @PathVariable annotation which will be taken care by the Spring Boot
    @GetMapping("/jobs/{id}")
    // @RequestMapping(value = "/jobs/{id}", method = RequestMethod.GET)
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        if (job != null) {
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE /jobs/{id}: Delete a specific job by ID
    // @PathVariable makes sure whatever var is in the URL path is assigned to the particular variable defined  with @PathVariable annotation which will be taken care by the Spring Boot
    // Used a boolean flag, where if the job is deleted then the flag is true else false
    @DeleteMapping("/jobs/{id}")
    // @RequestMapping(value = "/jobs/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        boolean deleted = jobService.deleteJobById(id);
        if (deleted) {
            return new ResponseEntity<>("Job Deleted Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // PUT /jobs/{id}: Update a specific job by ID (request body should contain the updated job
    // Here we use @pathVariable which makes sure whatever var is in the URL path is assigned to the particular variable defined  with @PathVariable annotation which will be taken care by the Spring Boot
    // And along with that we take @RequestBody, @RequestBody in the context of web development and APIs refers to the data sent by a client  to a server as part of an HTTP request. This data can be in various formats, such as JSON, XML, form data, or plain text, depending on the application's requirements
    @PutMapping("/jobs/{id}")
    // @RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        boolean updated = jobService.updateJob(id, updatedJob);
        if (updated) {
            return new ResponseEntity<>("Job Updated Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}



/*
GET /jobs: Get all jobs
GET /jobs/{id}: Get a specific job by ID
POST/jobs: Create a new job (request body should contain the job details)
DELETE /jobs/{id}: Delete a specific job by ID
PUT /jobs/{id}: Update a specific job by ID (request body should contain the updated jol
GET/jobs/{id}/company: Get the company associated with a specific job by ID

Example API URLS:
GET {base_url}/jobs
GET {base_url}/jobs/1
POST {base_url}/jobs
DELETE {base_url}/jobs/1
PUT {base_url}/jobs/1
GET {base_url}/jobs/1/company
*/