/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pws.a.learningRESTful;

import java.util.HashMap;
import java.util.Map;
import model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Asus
 */
//annotation that used to define the restful web services
@RestController
//public class controller
public class Controller {
    private Double totalAmount;
    //hash map method used to storing data
    private static Map<String, Product> productRepo = new HashMap<>();
    static {
        //initialization product honey
        Product honey = new Product();
        //calling honey and method setId
        honey.setId("1");
        //calling honey and method setName
        honey.setName("Honey");
        //set Price
        honey.setPrice(35000.0);
        //set Disc
        honey.setDisc(0.05);
        //set Total
        //insert a mapping into a map
        Double totalDiscount = honey.getPrice() * honey.getDisc();
        Double totalAmount = honey.getPrice() - totalDiscount;
        honey.setTotal(totalAmount);
        
        productRepo.put(honey.getId(), honey);
        
        //initialization product honey
        Product almond = new Product();
        //calling almond and method setId
        almond.setId("2");
        //calling almond and method setName
        almond.setName("Almond");
        almond.setPrice(50000.0);
        almond.setDisc(0.1);
        almond.setTotal(0.0);
        //insert a mapping into a map
        productRepo.put(almond.getId(), almond);
        
        
    }
    
    //annotation that used to define the Request URI to access the REST Endpoints
    @RequestMapping(value = "/products")
    //getProduct method
    public ResponseEntity<Object> getProduct(){
        //return response entity represent the HttpStatus
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }
    //annotation that used to define the Request URI to access the REST Endpoints and request method Post
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    //createProduct method with parameter product
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        //condition if product id already exists
        if(productRepo.containsKey(product.getId())){
            //it would return response entity that the product Id already exists
            return new ResponseEntity<>("Product Id is already exists. Please enter another Id.", HttpStatus.OK);
        }
        else {
            product.setTotal(totalAmount);
            //else the product Id is new
            productRepo.put(product.getId(), product);
            //return Product is created
        return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
        }
    }
    
    //annotation that used to define the Request URI to access the REST Endpoints and request method Put
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    //updateProduct with parameter id and product
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product){
        //conditional if the Product Id is not found, then it throws the ProductNotFoundException class.
        if(!productRepo.containsKey(id))throw new ProductNotfoundException();
        //remove a mapping
        productRepo.remove(id);
        //set Id
        product.setId(id);
        product.setTotal(totalAmount);
        //insert a mapping into a map 
        productRepo.put(id, product);
        //return response entity that product is update
        return new ResponseEntity<>("Product is update successfully", HttpStatus.OK);
    }
    
    //annotation that used to define the Request URI to access the REST Endpoints and request method Delete
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    //delete method with parameter id
    public ResponseEntity<Object> delete(@PathVariable("id")String id) {
        //remove a mapping
        productRepo.remove(id);
        //return response entity that product is deleted
        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
    }
}
