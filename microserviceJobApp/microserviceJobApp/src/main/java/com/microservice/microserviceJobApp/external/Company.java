package com.microservice.microserviceJobApp.external;


public class Company {

    private Long Id;
    private String name;
    private String description;


    public Company() {
    }

    public Company(Long id, String name, String description) {
        Id = id;
        this.name = name;
        this.description = description;

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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


}
