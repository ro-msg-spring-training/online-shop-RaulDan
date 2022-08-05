package ro.msg.learning.shop.exceptions.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ro.msg.learning.shop.exceptions.MissingStockException;
import ro.msg.learning.shop.exceptions.OrderNotCreatedException;
import ro.msg.learning.shop.exceptions.ProductNotFoundException;
import ro.msg.learning.shop.utils.Status;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ProductNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleProductNotFoundException(ProductNotFoundException exception) {
        return new ResponseEntity<>(buildApiError("Product Not Found! " + exception.getMessage(), Status.PRODUCT_NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MissingStockException.class)
    @ResponseStatus(value = HttpStatus.INSUFFICIENT_STORAGE)
    public ResponseEntity<ApiError> handleMissingStockException(MissingStockException exception) {
        return new ResponseEntity<>(buildApiError(exception.getMessage(), Status.INSUFFICIENT_STOCK), HttpStatus.INSUFFICIENT_STORAGE);
    }

    @ExceptionHandler(value = OrderNotCreatedException.class)
    @ResponseStatus(value = HttpStatus.INSUFFICIENT_STORAGE)
    public ResponseEntity<ApiError> handleOrderNotCreatedException(OrderNotCreatedException exception) {

        return new ResponseEntity<>(buildApiError(exception.getMessage(), Status.ORDER_NOT_CREATED), HttpStatus.INSUFFICIENT_STORAGE);
    }

    private ApiError buildApiError(String message, Status status) {
        ApiError error = new ApiError();
        error.setErrorMessage(message);
        error.setErrorStatus(status);
        return error;
    }
}
