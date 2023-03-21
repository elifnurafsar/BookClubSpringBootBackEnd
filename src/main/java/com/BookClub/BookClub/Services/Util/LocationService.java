package com.BookClub.BookClub.Services.Util;

import com.BookClub.BookClub.Entities.Util.Location;
import com.BookClub.BookClub.Repositories.Util.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository _locationRepository) {
        this.locationRepository = _locationRepository;
    }

    public List<Location> getAllLocations(){
        return locationRepository.findAll();
    }

    public Location getLocationByAreaCode(long area_code){
        return locationRepository.findById(area_code).orElse(null);
    }

    public boolean insertLocation(Location new_location){
        int a = locationRepository.insertLocation(new_location.getArea_code(), new_location.getName(), new_location.getAddress(), new_location.getPhone());
        return a>0;
    }

    public boolean deleteLocationByCode(long area_code){
        locationRepository.deleteById(area_code);
        Optional<Location> _location = locationRepository.findById(area_code);
        return _location.isEmpty();
    }
}
