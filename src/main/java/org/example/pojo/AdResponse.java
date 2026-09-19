package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdResponse {
    private int id;
    private String name;
    private String category;
    private String city;
    private String description;
    private int price;

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getCity() { return city; }
    public String getDescription() { return description; }
    public int getPrice() { return price; }
}