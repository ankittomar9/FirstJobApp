package com.embarkx.firstjobapp.company;

import com.embarkx.firstjobapp.Review.Review;
import com.embarkx.firstjobapp.job.Job;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
// ByDefault When Entity is mapped to a class (in this case its Company class) the name of the Table is same name of the class (in this case table name is Company).
// @Table(name = "Company_Table")
// If Developer doesn't want the default name of the table to be the class name, then "@Table" helps the Dev an option to rename the Table with "name" parameter with desired name.
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // we don't need to generate values for id's and by this annotation JPA manages it
    private Long id; // by using @Id from jakarta, it indicates that "id" is a primary key for the table Company_Table
    private String name;
    private String description;

    @JsonIgnore  // to avoid recursion (stackoverflow)
    @OneToMany (mappedBy = "company") // one company has many jobs, so to link the jobs with the company, we use one to many annotation
    private List<Job> jobs;

    @OneToMany (mappedBy = "company")
    private List<Review> reviews;

    public Company() {
        // Entities are Obj that represent the persistent data in the RDB
        // 1 of the Req for JPA entities is to have a default no arg Constructor, because JPA needs to create instances of entity class during the retrieval of data from the DB and JPA uses Reflections to create instances of entities & populates the properties with the retrieved data from the DB,
        // without Default constructor JPA won't be able to instantiate any entity object
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
