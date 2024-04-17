package org.web.service.mybankdepositsweb.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.*;

@RestController
@RequestMapping("/deposit")
public class DepositController {
    @Autowired
    DepositInterface depositInterface;

    Logger logger = LoggerFactory.getLogger(DepositController.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    ResourceBundle messageBundle = ResourceBundle.getBundle("messages");

    @GetMapping("/view/{roi}")
    public ResponseEntity<?> getDepositsByRoi(@PathVariable("roi") double roi) {
        try {
            return depositInterface.searchDepositsByRoi(roi);
        } catch (DepositException depositException) {
            String message = depositException.getMessage();
            logger.warn(resourceBundle.getString("deposit.exception") + ": " + message);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(message);
        } catch (SQLSyntaxErrorException syntaxException) {
            syntaxException.printStackTrace();
            logger.error(resourceBundle.getString("internal.error"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(messageBundle.getString("internal.error"));
        }
    }



    //Get mapping for stream().filter()
//    @GetMapping("/view/{roi}")
//    public ResponseEntity<?> listDepositsByRoi(@PathVariable("roi") double roi) {
//        try {
//            if (roi<0) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageBundle.getString("roi.negative"));
//            }
//            ResponseEntity<?> response = depositInterface.searchDepositsByRoi(roi);
//            return response;
//        } catch (SQLSyntaxErrorException e) {
//            logger.error(messageBundle.getString("internal.error"));
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(messageBundle.getString("internal.error"));
//        } catch (DepositException depositException) {
//            logger.warn(messageBundle.getString("deposit.exception"));
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(messageBundle.getString("deposit.exception"));
//        }
//    }




//    //Get mapping for procedure
//    @GetMapping("/view/{roi}")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200"),
//            @ApiResponse(responseCode = "404"),
//            @ApiResponse(responseCode = "500")
//    })
//    public ResponseEntity<?> listDepositsByRoi(@Valid @PathVariable("roi") Double roi) {
//        try {
//            if (roi<0) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageBundle.getString("roi.negative"));
//            }
//            ResponseEntity<?> responseEntity = depositInterface.searchDepositsByRoi(roi);
//
//            if (responseEntity.getStatusCode().is2xxSuccessful()) {
//                List<DepositsAvailable> depositsList = (List<DepositsAvailable>) responseEntity.getBody();
//                if (depositsList != null) {
//                    logger.info(messageBundle.getString("roi.fetch.success"));
//                    return ResponseEntity.ok(depositsList);
//                } else {
//                    throw new DepositException(messageBundle.getString("roi.no.deposits"));
//                }
//            } else {
//                return responseEntity;
//            }
//        } catch (DepositException depositException) {
//            String message = depositException.getMessage();
//            logger.warn(resourceBundle.getString("deposit.exception") + ": " + message);
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(message);
//        } catch (SQLSyntaxErrorException syntaxException) {
//            syntaxException.printStackTrace();
//            logger.error(resourceBundle.getString("internal.error"));
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(messageBundle.getString("internal.error"));
//        }
//    }








//    //Rest service - get mapping for stream().filter()
//        @GetMapping("/view/{roi}")
//    public List<DepositsAvailable> listDepositsByRoi(@PathVariable("roi") double roi) {
//        List<DepositsAvailable> depositsList = new ArrayList<>();
//        try {
//            depositsList = depositInterface.searchDepositsByRoi(roi);
//        } catch (SQLSyntaxErrorException e) {
//            logger.error(messageBundle.getString("internal.error"));
//        } catch (DepositException depositException) {
//            logger.warn(messageBundle.getString("deposit.exception"));
//        }
//        return depositsList;
//    }










//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }





}
