package com.embarkx.firstjobapp.job.impl;

import com.embarkx.firstjobapp.job.Job;
import com.embarkx.firstjobapp.job.JobRepository;
import com.embarkx.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService{
    //private List<Job> jobs=new ArrayList<>();

    JobRepository jobRepository;
    private long nextId=1L;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
        // jobRepository is a Bean that is being managed by Spring.
        // jobRepository will be autowired during runtime
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        //job.setId(nextId++);
        jobRepository.save(job); // save() is a method in JpaRepository
    }

    @Override
    public Job getJobById(Long id) {
        /*for (Job job:jobs){
            if(job.getId().equals(id)){
                return job;
            }
        }
        return null;   // traditional way to find job by id   */
        return jobRepository.findById(id).orElse(null);  // to find job by id using jpa
    }

    @Override
    public boolean deleteJobById(Long id) {
        /* Iterator<Job> iterator=jobs.iterator();
        while(iterator.hasNext()){
            Job job=iterator.next();
            if(job.getId().equals(id)){
              iterator.remove();
                return true;
            }
        }
        return false;    // traditional way to delete job by id   */

    /* this can also be used for removing a job in clean, safe manner and doesn’t cause ConcurrentModificationException like for each loop
    @Override
    public boolean deleteJobById(Long id) {
        return jobs.removeIf(job -> job.getId().equals(id));
    }*/

        try{      // try-catch is used because if the job isn't found it throws an Exception, but to avoid the exception we use try-catch block.
            jobRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }


    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional=jobRepository.findById(id);
        if(jobOptional.isPresent()) {
            Job job=jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());

            jobRepository.save(job);
            return true;
            }
        return false;
    }

}
