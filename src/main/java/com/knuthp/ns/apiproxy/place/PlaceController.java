package com.knuthp.ns.apiproxy.place;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/place")
public class PlaceController {
	private static final Logger LOG = LoggerFactory.getLogger(PlaceController.class);
	private RestTemplate restTemplate;
	private String url;
	
	public PlaceController() {
		this("http://ns-trainstations-place.herokuapp.com/api");
	}

	
	public PlaceController(String url) {
		this.url = url;
		restTemplate = new RestTemplate();
	}
	

    @RequestMapping(method = RequestMethod.GET)
    public List<Place> index() {
		Place[] placeArray = restTemplate.getForObject(url + "/place", Place[].class);
		List<Place> placeList = Arrays.asList(placeArray);
		LOG.info("Got list {}", placeList);
        return placeList;
    }
    
    
    @RequestMapping(value ="/{placeId}", method = RequestMethod.GET)
    public Place get(@PathVariable String placeId) {
    	Place place = restTemplate.getForObject(url + "/place/" + placeId, Place.class);
    	LOG.info("Got place: " + place);
    	return place;
    }
    
    
    @RequestMapping(method = RequestMethod.POST, consumes="application/json")
    public Place add(@RequestBody Place place) {
    	Place postForObject = restTemplate.postForObject(url + "/place", place, Place.class);
    	LOG.info("Added place place={} mapped to {}", place, postForObject);
    	return postForObject;
    }
    
    @RequestMapping(value ="/{placeId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String placeId) {
    	restTemplate.delete(url + "/place/" + placeId);
    	LOG.info("Deleted place placeId={}", placeId);
    }
    
    

}
