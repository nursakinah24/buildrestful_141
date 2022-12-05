/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pws.a.learningRESTful;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Asus
 */
//an annotation, to handle the exceptions globally.
@ControllerAdvice
//
public class ProductExceptionController {
    //an annotation used to handle the specific exceptions and sending the custom responses to the client
    @ExceptionHandler(value = ProductNotfoundException.class)
    //exception method to return exception when product not found and HttpStatus set not found
   public ResponseEntity<Object> exception(ProductNotfoundException exception) {
       //
      return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
   }
}
