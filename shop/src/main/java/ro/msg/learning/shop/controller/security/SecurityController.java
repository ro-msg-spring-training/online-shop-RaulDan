package ro.msg.learning.shop.controller.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.service.SecurityService;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/security")
public class SecurityController {

    private final SecurityService securityService;

    @PostMapping(value = "/getUser")
    public ResponseEntity<Customer> findCustomerByUsername(@RequestBody String username){
        return new ResponseEntity<>(securityService.findCustomerByUsername(username), HttpStatus.OK);
    }

}
