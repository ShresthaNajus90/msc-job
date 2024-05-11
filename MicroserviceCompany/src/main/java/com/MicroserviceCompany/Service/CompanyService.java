package com.MicroserviceCompany.Service;



import com.MicroserviceCompany.Entity.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();
    boolean updateCompany(Company company, Long Id);

    void createCompany(Company company);

    boolean deleteCompanyById(Long Id);

    Company getCompanyById(Long Id);
}
