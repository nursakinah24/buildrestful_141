/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pws.a.learningRESTful;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Asus
 */
//Define a class that extends the RuntimeException class
public class ProductNotfoundException extends RuntimeException {
    //
    private static final long serialVersionUID = 1L;
    //annotation that used to define the Request URI to access the REST Endpoints and request method PUT
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    //updateProduct method
    public ResponseEntity<Object> updateProduct(){
        //to throw exception from the API
        throw new ProductNotfoundException();
    }
    
}
