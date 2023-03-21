package com.BookClub.BookClub.Controllers.Util;

import com.BookClub.BookClub.Entities.Util.Location;
import com.BookClub.BookClub.Services.Util.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Location")
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService _location_service) {
        this.locationService = _location_service;
    }

    @GetMapping
    public List<Location> getAllLocations(){
        return locationService.getAllLocations();
    }

    @GetMapping("/get-by-code/{area_code}")
    public Location getLocation(@PathVariable("area_code") long area_code){
        return locationService.getLocationByAreaCode(area_code);
    }

    @PostMapping
    public boolean createLocation(@RequestBody Location new_location){
        return locationService.insertLocation(new_location);
    }

    @DeleteMapping("/{area_code}")
    public boolean DeleteLocation(@PathVariable("area_code") long area_code){
        return locationService.deleteLocationByCode(area_code);
    }
}
