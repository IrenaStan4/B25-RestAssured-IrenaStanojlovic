package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString

public class Country {

    @JsonProperty("post code")
    private int postCode;
    private String country;
    @JsonProperty("country abbreviation")
    private String countryAbbreviation;
    private List<Places> places;

    /*
    "post code": "22031",
    "country": "United States",
    "country abbreviation": "US",
    "places": [
        {
            "place name": "Fairfax",
            "longitude": "-77.2649",
            "state": "Virginia",
            "state abbreviation": "VA",
            "latitude": "38.8604"
        }
    ]
     */
}
