package com.MicroserviceCompany.companyImpl;


import com.MicroserviceCompany.Entity.Company;
import com.MicroserviceCompany.Repository.CompanyRepository;
import com.MicroserviceCompany.Service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long Id) {
        Optional<Company> companyOptional= companyRepository.findById(Id);

        if(companyOptional.isPresent()) {
            Company updatedCompany = companyOptional.get();
            updatedCompany.setDescription(company.getDescription());
            updatedCompany.setName(company.getName());

            companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company) {
    companyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long Id) {
        if(companyRepository.existsById(Id)){
            companyRepository.deleteById(Id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Company getCompanyById(Long Id) {
        return companyRepository.findById(Id).orElse(null);
    }


}
