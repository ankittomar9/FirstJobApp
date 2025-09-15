package com.embarkx.firstjobapp.job;

import com.embarkx.firstjobapp.company.Company;
import jakarta.persistence.*;

@Entity
// ByDefault When Entity is mapped to a class (in this case its Job class) the name of the Table is same name of the class (in this case table name is Job).
// @Table(name = "Job_Table")
// If Developer doesn't want the default name of the table to be the class name, then "@Table" helps the Dev an option to rename the Table with "name" parameter with desired name.
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // we don't need to generate values for id's and by this annotation JPA manages it
    private Long id; // by using @Id from jakarta, it indicates that "id" is a primary key for the table Job_Table
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;

    @ManyToOne  // Many jobs may be in 1 company, so we use many to one annotation
    private Company company;

    public Job() {
        // Entities are Obj that represent the persistent data in the RDB
        // 1 of the Req for JPA entities is to have a default no arg Constructor, because JPA needs to create instances of entity class during the retrieval of data from the DB and JPA uses Reflections to create instances of entities & populates the properties with the retrieved data from the DB,
        // without Default constructor JPA won't be able to instantiate any entity object
    }

    public Job(Long id, String title, String description, String minSalary, String maxSalary, String location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.location = location;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
