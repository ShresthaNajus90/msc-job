package com.MicroserviceCompany.Controller;


import com.MicroserviceCompany.Entity.Company;
import com.MicroserviceCompany.Service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // getting allCompany
    @GetMapping()
    public ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<>(companyService.getAllCompanies(),HttpStatus.OK);
    }


    // updateCompanyById
    @PutMapping("/{Id}")
  public ResponseEntity<String> updateCompany(@PathVariable Long Id, @RequestBody  Company company){
        companyService.updateCompany(company, Id);
        return new ResponseEntity<>("company updated successfully", HttpStatus.OK);
  }


  //Adding Company
  @PostMapping()
  public ResponseEntity<String> createCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("Company Created successfully", HttpStatus.CREATED);
  }

  //deletingCompany
    @DeleteMapping("/{Id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long Id) {
        boolean deleted = companyService.deleteCompanyById(Id);
        if (deleted)
            return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
        return new ResponseEntity<>("Company not Found !!",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long Id){
        Company company = companyService.getCompanyById(Id);
        if(company !=null){
            return new ResponseEntity<>(company,HttpStatus.OK);
        }else {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }


}
