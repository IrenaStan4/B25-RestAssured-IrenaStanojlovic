package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString

public class Zippo_Country {

    @JsonProperty("country abbreviation")
    private String countryAbbreviation;
    private List<Zippo_Places> places;
    private String country;
    @JsonProperty("place name")
    private String placeName;
    private String state;
    @JsonProperty("state abbreviation")
    private String stateAbbreviation;




    /*
    "country abbreviation": "US",
    "places": [
        {
            "place name": "Fairfax",
            "longitude": "-77.3242",
            "post code": "22030",
            "latitude": "38.8458"
        },
        {
            "place name": "Fairfax",
            "longitude": "-77.2649",
            "post code": "22031",
            "latitude": "38.8604"
        },
        {
            "place name": "Fairfax",
            "longitude": "-77.2925",
            "post code": "22032",
            "latitude": "38.8177"
        },
        {
            "place name": "Fairfax",
            "longitude": "-77.3885",
            "post code": "22033",
            "latitude": "38.8776"
        },
        {
            "place name": "Fairfax",
            "longitude": "-77.2888",
            "post code": "22034",
            "latitude": "38.8318"
        },
        {
            "place name": "Fairfax",
            "longitude": "-77.3616",
            "post code": "22035",
            "latitude": "38.8557"
        },
        {
            "place name": "Fairfax",
            "longitude": "-77.0796",
            "post code": "22036",
            "latitude": "38.7351"
        },
        {
            "place name": "Fairfax",
            "longitude": "-77.2888",
            "post code": "22037",
            "latitude": "38.8318"
        },
        {
            "place name": "Fairfax",
            "longitude": "-77.302",
            "post code": "22038",
            "latitude": "38.8528"
        },
        {
            "place name": "Fairfax Station",
            "longitude": "-77.3064",
            "post code": "22039",
            "latitude": "38.7602"
        }
    ],
    "country": "United States",
    "place name": "Fairfax",
    "state": "Virginia",
    "state abbreviation": "VA"
     */
}
