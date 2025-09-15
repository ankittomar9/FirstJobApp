package com.embarkx.firstjobapp.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;  // JpaRepository extends the functionality of CrudRepository.

// In Spring Data JPA, Repository is an Interface that allows us to perform various operations involving a Database like CRUD if records without writing boilerplate code
public interface JobRepository extends JpaRepository<Job, Long> {

    // JobRepository Interface is Spring Data JPA Repository
    // Spring Data JPA will create an implementation of this Interface, and we don't have to write any implementation code for Repository.
    // Spring Data JPA automatically generates the implementation at the runtime and can be used in the Application like Service class.


}
