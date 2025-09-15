package com.embarkx.firstjobapp.Review;


import com.embarkx.firstjobapp.company.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
// ByDefault When Entity is mapped to a class (in this case its Review class) the name of the Table is same name of the class (in this case table name is Review)
// @Table(name = "Review_Table")
// If Developer doesn't want the default name of the table to be the class name, then "@Table" helps the Dev an option to rename the Table with "name" parameter with desired name.
public class Review {    //contains the structure
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // we don't need to generate values for id's and by this annotation JPA manages it
    private Long id;
    private String title;
    private String description;
    private double rating;



    @JsonIgnore
    @ManyToOne
    private Company company;

    public Review() {
        // Entities are Obj that represent the persistent data in the RDB
        // 1 of the Req for JPA entities is to have a default no arg Constructor, because JPA needs to create instances of entity class during the retrieval of data from the DB and JPA uses Reflections to create instances of entities & populates the properties with the retrieved data from the DB,
        // without Default constructor JPA won't be able to instantiate any entity object
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
