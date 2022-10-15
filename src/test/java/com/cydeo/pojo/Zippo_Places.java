package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Zippo_Places {

    @JsonProperty("place name")
    private String placeName;
    private String longitude;
    @JsonProperty("post code")
    private String postCode;
    private String latitude;

    /*
    {
            "place name": "Fairfax",
            "longitude": "-77.3242",
            "post code": "22030",
            "latitude": "38.8458"
        },
     */
}
