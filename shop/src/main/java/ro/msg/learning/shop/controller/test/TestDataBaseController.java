package ro.msg.learning.shop.controller.test;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.service.ManipulateDatabaseService;
import ro.msg.learning.shop.utils.Status;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
@Profile("test")
public class TestDataBaseController {

    private final ManipulateDatabaseService manipulateDatabaseService;

    @PostMapping(value = "/populateDatabase")
    public ResponseEntity<Status> populateDatabase(){

        return new ResponseEntity<>(manipulateDatabaseService.populateDatabase(), HttpStatus.OK);
    }

    @PostMapping(value = "/clearDatabase")
    public ResponseEntity<Status> clearDatabase(){
        return new ResponseEntity<>(manipulateDatabaseService.clearDatabase(),HttpStatus.OK);
    }
}
