package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Places {

    @JsonProperty("place name")
    private String placeName;
    private String longitude;
    private String state;
    @JsonProperty("state abbreviation")
    private String stateAbbreviation;
    private String latitude;



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
