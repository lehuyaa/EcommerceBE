package com.ldh.ecommerce.controller;


import com.ldh.ecommerce.model.Product;
import com.ldh.ecommerce.repository.CategoryRepository;
import com.ldh.ecommerce.repository.ProductRepository;
import com.ldh.ecommerce.repository.UserRepository;
import com.ldh.ecommerce.request.AddProductRequest;
import com.ldh.ecommerce.response.CommonResponse;
import com.ldh.ecommerce.response.MessageResponse;
import com.ldh.ecommerce.service.imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    public ProductServiceImp productServiceImp = new ProductServiceImp();

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public CategoryRepository categoryRepository;
    @Autowired
    public ProductRepository productRepository;
    @GetMapping("/getAllProduct")
    public CommonResponse getAllProduct () {
        return new CommonResponse(HttpStatus.OK,new MessageResponse(""), productServiceImp.getAllProduct());
    }
    @Autowired
    private EntityManager entityManager;


    @GetMapping("/searchProduct")
    public CommonResponse searchProduct (@RequestBody String searchKey) {
        return new CommonResponse(HttpStatus.OK,new MessageResponse(""), productServiceImp.searchProduct(searchKey));
    }

    @PostMapping("/addProduct")
    public CommonResponse addProduct(@RequestBody AddProductRequest addProductRequest) {

        if (addProductRequest == null) {
            return new CommonResponse(HttpStatus.BAD_REQUEST, new MessageResponse("FAILURE"),null);
        } else {

            Product product = new Product();

            product.setProductName(addProductRequest.getProduct_name());
            product.setProductPrice(addProductRequest.getProduct_price());
            product.setProductImage(addProductRequest.getProduct_image());
            product.setCategory(categoryRepository.findById(addProductRequest.getCategory_id()).get());
            product.setUser(userRepository.findById(addProductRequest.getUser_id()).get());
            productRepository.save(product);

            return new CommonResponse(HttpStatus.OK, new MessageResponse("SCUCESS"), null);

        }
    }

    @PutMapping("/editProduct/{idProduct}")
    public CommonResponse editProduct(@RequestBody AddProductRequest addProductRequest,@PathVariable("idProduct") Long idProduct) {

        if (addProductRequest == null) {
            return new CommonResponse(HttpStatus.BAD_REQUEST, new MessageResponse("FAILURE"),null);
        } else {

            Product product = productRepository.findById(idProduct).get();
            product.setProductName(addProductRequest.getProduct_name());
            product.setProductPrice(addProductRequest.getProduct_price());
            product.setProductImage(addProductRequest.getProduct_image());
            product.setCategory(categoryRepository.findById(addProductRequest.getCategory_id()).get());
            product.setUser(userRepository.findById(addProductRequest.getUser_id()).get());
            productRepository.save(product);

            return new CommonResponse(HttpStatus.OK, new MessageResponse("SCUCESS"), null);

        }


    }
    @DeleteMapping("/removeProduct/{idProduct}")
    public CommonResponse removeProduct(@PathVariable("idProduct") Long idProduct) {
            productRepository.deleteById(idProduct);
            return new CommonResponse(HttpStatus.OK, new MessageResponse("SUCESS"), null);
    }

    @GetMapping("/getByUserId/{userId}")
    public CommonResponse getByUserId(@PathVariable("userId") Long userId) {
        if (userRepository.findById(userId).get() == null) {
            return new CommonResponse(HttpStatus.BAD_REQUEST, new MessageResponse("FAILURE"), null);
        }
        return new CommonResponse(HttpStatus.OK, new MessageResponse("SUCESS"), productServiceImp.getByUserId(userId));
    }
    @GetMapping("/getByCategoryId/{categoryId}")
    public CommonResponse getByCategoryId(@PathVariable("categoryId") Long categoryId) {
        if (categoryRepository.findById(categoryId).get() == null) {
            return new CommonResponse(HttpStatus.BAD_REQUEST, new MessageResponse("FAILURE"), null);
        }
        return new CommonResponse(HttpStatus.OK, new MessageResponse("SUCESS"), productServiceImp.getByCategoryId(categoryId));
    }

}
