package com.creeps.tokenstore.tokens;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private Object fieldValue;
    private final static String MESSAGE_TEMPLATE = "%s not found with %s:%s ";
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue){
        super(String.format(MESSAGE_TEMPLATE, resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }


    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
