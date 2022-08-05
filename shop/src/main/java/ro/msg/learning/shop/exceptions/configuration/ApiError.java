package ro.msg.learning.shop.exceptions.configuration;

import lombok.Data;
import ro.msg.learning.shop.utils.Status;

@Data
public class ApiError {

    private Status errorStatus;
    private String errorMessage;
}
