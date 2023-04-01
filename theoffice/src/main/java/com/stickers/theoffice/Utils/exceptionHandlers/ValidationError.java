package com.stickers.theoffice.Utils.exceptionHandlers;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private List<FieldError> errors = new ArrayList<>();

    public List<FieldError> getErros() {
        return errors;
    }

    public void addError(String fieldName, String message){
        errors.add(new FieldError(fieldName, message));
    }
}
