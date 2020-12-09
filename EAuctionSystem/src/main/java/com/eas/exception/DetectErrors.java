package com.eas.exception;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class DetectErrors {
	
	public static void detectErrors(Errors errors) {
		
		if (errors.hasErrors()) {
            // Extract ConstraintViolation list from body errors
            List<ConstraintViolation<?>> violationsList = new ArrayList<>();
                for (ObjectError e : errors.getAllErrors()) {
                violationsList.add(e.unwrap(ConstraintViolation.class));
            }

            String exceptionMessage = "";
            
            // Construct a helpful message for each input violation
            for (ConstraintViolation<?> violation : violationsList) {
                exceptionMessage += violation.getMessage() + ". ";
            }
            throw new InvalidInputDataException(exceptionMessage);
        }
	}
	

}
