package ro.msg.learning.shop.service;

import ro.msg.learning.shop.model.Location;

import java.util.List;

public interface LocationService {

    List<Location> findAll();
    void save(Location location);
    void deleteAll();
}
