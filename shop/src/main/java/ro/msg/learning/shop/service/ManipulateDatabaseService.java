package ro.msg.learning.shop.service;

import ro.msg.learning.shop.utils.Status;

public interface ManipulateDatabaseService {

    Status populateDatabase();
    Status clearDatabase();
}
