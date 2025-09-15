package com.embarkx.firstjobapp.company;

import com.embarkx.firstjobapp.job.Job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @RestController is a REST-ful web service controller. that combines the functionality of both @Controller and @ResponseBody. --->@Controller → Handles HTTP requests and returns a view name (like an HTML page).---> @ResponseBody → Tells Spring to take the method’s return value and directly write it as JSON or XML in the HTTP response.
@RequestMapping("/companies") // when @RequestMapping is applied at a class level it sets a base URL (/companies....) path for all the methods that are handling requests in that particular controller
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // GET /companies: Get all companies
    // ResponseEntity<> in Spring Boot is a special class that represents the entire HTTP response — not just the data (body), but also the status code and headers.  📦 ResponseEntity = [HTTP Status] + [Headers] + [Body]
    // HttpStatus is the class in Spring is basically a list of constants that represent all standard HTTP status codes and their meaning.
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    // PUT /companies/{id}
    // ResponseEntity<> in Spring Boot is a special class that represents the entire HTTP response — not just the data (body), but also the status code and headers.  📦 ResponseEntity = [HTTP Status] + [Headers] + [Body]
    // HttpStatus is the class in Spring is basically a list of constants that represent all standard HTTP status codes and their meaning.
    // Here we use @pathVariable which makes sure whatever var is in the URL path is assigned to the particular variable defined  with @PathVariable annotation which will be taken care by the Spring Boot
    // And along with that we take @RequestBody, @RequestBody in the context of web development and APIs refers to the data sent by a client  to a server as part of an HTTP request. This data can be in various formats, such as JSON, XML, form data, or plain text, depending on the application's requirements
    @PutMapping("/{id}")
    public ResponseEntity<String> UpdateCompany(@PathVariable Long id, @RequestBody Company company){
        companyService.updateCompany(company, id);
        return new ResponseEntity<>("Company Updated Successfully", HttpStatus.OK);
    }

    // POST /companies
    // @RequestBody in the context of web development and APIs refers to the data sent by a client  to a server as part of an HTTP request. This data can be in various formats, such as JSON, XML, form data, or plain text, depending on the application's requirements
    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("Company added Successfully", HttpStatus.CREATED);
    }

    // DELETE /companies/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        boolean isdeleted = companyService.deleteCompanyById(id);
        if (isdeleted) {
            return new ResponseEntity<>("Company Deleted Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // GET /companies/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company company = companyService.getCompanyById(id);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}




// GET /companies
// PUT /companies/{id}
// POST /companies
// DELETE /companies/{id}
// GET /companies/{id}