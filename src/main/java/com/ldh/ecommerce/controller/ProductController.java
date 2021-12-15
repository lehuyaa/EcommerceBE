package com.ldh.ecommerce.controller;


import com.ldh.ecommerce.model.Notification;
import com.ldh.ecommerce.model.Product;
import com.ldh.ecommerce.model.Review;
import com.ldh.ecommerce.model.User;
import com.ldh.ecommerce.repository.*;
import com.ldh.ecommerce.request.AddMultiProduct;
import com.ldh.ecommerce.request.AddProductRequest;
import com.ldh.ecommerce.request.ReviewRequest;
import com.ldh.ecommerce.response.CommonResponse;
import com.ldh.ecommerce.response.MessageResponse;
import com.ldh.ecommerce.response.ProfileUserResponse;
import com.ldh.ecommerce.search.ProductSearch;
import com.ldh.ecommerce.service.imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    public ReviewRepository reviewRepository;
    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public NotificationRepository notificationRepository;


    @Autowired
    private EntityManager entityManager;


    @Autowired
    private ProductSearch productSearch;

    @GetMapping("/getAllProduct/{pageNo}")
    public CommonResponse getAllProduct (@PathVariable int pageNo) {
        return new CommonResponse(HttpStatus.OK,new MessageResponse(""), productServiceImp.getAllProduct(pageNo));
    }

    @GetMapping("/getRandomProduct/{userId}")
    public CommonResponse getRandomProduct (@PathVariable Long userId) {
        return new CommonResponse(HttpStatus.OK,new MessageResponse(""), productServiceImp.getRanDomProductByUserId(userId));
    }

    @GetMapping("/search/{searchKey}")
    public CommonResponse search(@PathVariable("searchKey") String searchKey) {
        List<Product> searchResults = new ArrayList<>();
        try {
            searchResults = productRepository.searchByName(searchKey.replace(' ', '&'));
        }
        catch (Exception ex) {
        }

        return new CommonResponse(HttpStatus.OK, new MessageResponse("SUCCESS"), searchResults);
    }
    @PostMapping("/addProduct")
    public CommonResponse addProduct(@RequestBody AddProductRequest addProductRequest) {

        if (addProductRequest == null) {
            return new CommonResponse(HttpStatus.BAD_REQUEST, new MessageResponse("FAILURE"),null);
        } else {

            Product product = new Product();

            product.setProductName(addProductRequest.getProduct_name());
            product.setProductPrice(addProductRequest.getProduct_price()+"  ");
            product.setProductImage(addProductRequest.getProduct_image());
            product.setCategory(categoryRepository.findById(addProductRequest.getCategory_id()).get());
            product.setUser(userRepository.findById(addProductRequest.getUser_id()).get());
            product.setQuantity(addProductRequest.getQuantity());
            product.setRate(0L);
            productRepository.save(product);

            return new CommonResponse(HttpStatus.OK, new MessageResponse("SUCCESS"), null);

        }
    }
    @PostMapping("/addMultiProduct")
    public CommonResponse addMultiProduct(@RequestBody AddMultiProduct addMultiProduct) {

       for (AddProductRequest addProductRequest : addMultiProduct.getListProduct()) {
           Product product = new Product();

           product.setProductName(addProductRequest.getProduct_name());
           product.setProductPrice(addProductRequest.getProduct_price()+"  ");
           product.setProductImage(addProductRequest.getProduct_image());
           product.setCategory(categoryRepository.findById(addProductRequest.getCategory_id()).get());
           product.setUser(userRepository.findById(addProductRequest.getUser_id()).get());
           product.setQuantity(addProductRequest.getQuantity());
           product.setRate(0L);
           productRepository.save(product);
       }
        return new CommonResponse(HttpStatus.OK, new MessageResponse("SUCCESS"), null);
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
            product.setQuantity(addProductRequest.getQuantity());
            productRepository.save(product);

            return new CommonResponse(HttpStatus.OK, new MessageResponse("SUCCESS"), null);

        }


    }
    @DeleteMapping("/removeProduct/{idProduct}")
    public CommonResponse removeProduct(@PathVariable("idProduct") Long idProduct) {
            productRepository.deleteById(idProduct);
            return new CommonResponse(HttpStatus.OK, new MessageResponse("SUCCESS"), null);
    }

    @GetMapping("/getByUserId/{userId}")
    public CommonResponse getByUserId(@PathVariable("userId") Long userId) {
        if (userRepository.findById(userId).get() == null) {
            return new CommonResponse(HttpStatus.BAD_REQUEST, new MessageResponse("FAILURE"), null);
        }

        return new CommonResponse(HttpStatus.OK, new MessageResponse("SUCCESS"), productServiceImp.getByUserId(userId));
    }

    @GetMapping("/getProfileByUserId/{userId}")
    public CommonResponse getProfileByUserId(@PathVariable("userId") Long userId) {
        if (userRepository.findById(userId).get() == null) {
            return new CommonResponse(HttpStatus.BAD_REQUEST, new MessageResponse("FAILURE"), null);
        }
        ProfileUserResponse profileUserResponse = new ProfileUserResponse(userRepository.findById(userId).get(),productServiceImp.getByUserId(userId));

        return new CommonResponse(HttpStatus.OK, new MessageResponse("SUCCESS"),profileUserResponse);
    }
    
    @GetMapping("/getByCategoryId/{categoryId}")
    public CommonResponse getByCategoryId(@PathVariable("categoryId") Long categoryId) {
        if (categoryRepository.findById(categoryId).get() == null) {
            return new CommonResponse(HttpStatus.BAD_REQUEST, new MessageResponse("FAILURE"), null);
        }
        return new CommonResponse(HttpStatus.OK, new MessageResponse("SUCCESS"), productServiceImp.getByCategoryId(categoryId));
    }

    @PutMapping("/updateRateProduct/{idProduct}")
    public CommonResponse updateRateProduct(@PathVariable("idProduct") Long idProduct) {
            Product product = productRepository.findById(idProduct).get();
            product.setRate(product.getRate() + 1000);
            productRepository.save(product);
            return new CommonResponse(HttpStatus.OK, new MessageResponse("SUCCESS"), null);
    }

    @GetMapping("/getReviewByProductId/{productId}")
    public CommonResponse getReviewByProductId(@PathVariable("productId") Long productId) {
        return new CommonResponse(HttpStatus.OK, new MessageResponse("SUCCESS"), reviewRepository.findAllByProductId(productId));
    }

    @PostMapping("/addReview")
    public CommonResponse addReview(@RequestBody ReviewRequest reviewRequest) {

        if (reviewRequest == null) {
            return new CommonResponse(HttpStatus.BAD_REQUEST, new MessageResponse("FAILURE"),null);
        } else {

            Review review = new Review();
            User user = userRepository.findById(reviewRequest.getUserId()).get();
            Product product = productRepository.findById(reviewRequest.getProductId()).get();
            switch ( reviewRequest.getStarNumber() ) {
                case  1:
                    // Làm gì đó tại đây ...
                    product.setRate(product.getRate() - 50L);
                    break;
                case  2:
                    product.setRate(product.getRate() - 40L);
                    // Làm gì đó tại đây ...
                    break;
                case  3:
                    product.setRate(product.getRate() + 10L);
                    // Làm gì đó tại đây ...
                    break;
                case 4:
                    product.setRate(product.getRate() + 50L);
                    // Làm gì đó tại đây ...
                    break;
                default:
                    product.setRate(product.getRate() + 80L);
                    // Làm gì đó tại đây ...
            }
            review.setContent(reviewRequest.getContent());
            review.setProductId(reviewRequest.getProductId());
            review.setCreatedTime(reviewRequest.getCreatedTime());
            review.setStarNumber(reviewRequest.getStarNumber());
            review.setUser(user);

            reviewRepository.save(review);
            return new CommonResponse(HttpStatus.OK, new MessageResponse("SUCCESS"), null);

        }
    }
    @PostMapping("/requestUpdateRate/{productId}")
    public CommonResponse requestUpdateRate(@PathVariable("productId") Long productId) {

        Notification notification = new Notification();
        notification.setIdeReceiver(21L);
        notification.setTitle("Update Rate Product");
        notification.setContent("Request Update Rate for ProductId:" + productId);
        notification.setType(1);
        Date myDate = new Date();
        notification.setCreateTime(myDate.toString());
        notificationRepository.save(notification);
        return new CommonResponse(HttpStatus.OK, new MessageResponse("SUCCESS"), null);


    }

}
