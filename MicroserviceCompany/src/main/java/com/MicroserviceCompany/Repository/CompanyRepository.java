package com.MicroserviceCompany.Repository;


import com.MicroserviceCompany.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
