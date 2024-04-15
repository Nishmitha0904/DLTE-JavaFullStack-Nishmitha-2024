package org.web.service.mybankdepositsweb.rest;

import mybank.dao.mybankdeposits.entity.DepositsAvailable;
import mybank.dao.mybankdeposits.exception.DepositException;
import mybank.dao.mybankdeposits.interfaces.DepositInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLSyntaxErrorException;
import java.util.*;

@RestController
@RequestMapping("/deposit")
public class DepositController {
    @Autowired
    DepositInterface depositInterface;

    Logger logger = LoggerFactory.getLogger(DepositController.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


//    @GetMapping("/view/{roi}")
//    public List<DepositsAvailable> listDepositsByRoi(@PathVariable("roi") double roi) {
//        List<DepositsAvailable> depositsList = new ArrayList<>();
//        try {
//            depositsList = depositInterface.searchDepositsByRoi(roi);
//        } catch (SQLSyntaxErrorException e) {
//            logger.error(resourceBundle.getString("internal.error"));
//        } catch (DepositException depositException) {
//            logger.warn(resourceBundle.getString("deposit.exception"));
//        }
//        return depositsList;
//    }

    @GetMapping("/view/{roi}")
    public ResponseEntity<?> listDepositsByRoi(@Valid @PathVariable("roi") Double roi) {
        try {
            ResponseEntity<?> responseEntity = depositInterface.searchDepositsByRoi(roi);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                List<DepositsAvailable> depositsList = (List<DepositsAvailable>) responseEntity.getBody();
                if (depositsList != null) {
                    return ResponseEntity.ok(depositsList);
                } else {
                    throw new DepositException("No deposits found with given ROI");
                }
            } else {
                return responseEntity;
            }
        } catch (DepositException depositException) {
            String message = depositException.getMessage();
            logger.warn(resourceBundle.getString("deposit.exception") + ": " + message);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(message);
//        } catch (SQLSyntaxErrorException syntaxException) {
//            logger.error(resourceBundle.getString("internal.error"));
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Internal server error occurred");
//        }

        }

    }

}
