package edu.tomerbu.blogfinalproject.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


// if we throw this exception -> the default status of the controller === 404
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private final String resourceName; //ex: Post / Comment
    private final String name;  //ex: id/title
    private final String value;//ex: 1/JPA

    public ResourceNotFoundException(String resourceName, String name, String value) {
        super("%s not found with %s = %s".formatted(resourceName, name, value));
        this.resourceName = resourceName;
        this.name = name;
        this.value = value;
    }
}
//Runtime exception can be avoided by smarter programming!