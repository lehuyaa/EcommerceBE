package com.ldh.ecommerce.controller;

import com.ldh.ecommerce.model.Order;
import com.ldh.ecommerce.model.OrderDetails;
import com.ldh.ecommerce.model.Product;
import com.ldh.ecommerce.model.StatusOrder;
import com.ldh.ecommerce.repository.OrderDetailsRepository;
import com.ldh.ecommerce.repository.OrderRepository;
import com.ldh.ecommerce.repository.ProductRepository;
import com.ldh.ecommerce.request.AddProductRequest;
import com.ldh.ecommerce.request.OrderItemRequest;
import com.ldh.ecommerce.request.OrderRequest;
import com.ldh.ecommerce.request.ProductOrderRequest;
import com.ldh.ecommerce.response.CommonResponse;
import com.ldh.ecommerce.response.MessageResponse;
import com.ldh.ecommerce.service.imp.OrderServiceImp;
import com.ldh.ecommerce.service.imp.PaymentMethodServiceImp;
import com.ldh.ecommerce.service.imp.StatusOrderImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    public PaymentMethodServiceImp paymentMethodServiceImp = new PaymentMethodServiceImp();

    @Autowired
    public StatusOrderImp statusOrderImp = new StatusOrderImp();

    @Autowired
    public OrderServiceImp orderServiceImp = new OrderServiceImp();
    @Autowired
    public OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public ProductRepository productRepository;
    @Autowired
    public OrderRepository orderRepository;

    @GetMapping("/getAllPaymentMethod")
    public CommonResponse getAllPaymentMethod () {
        return new CommonResponse(HttpStatus.OK,new MessageResponse(""), paymentMethodServiceImp.getAllPaymentMethod());
    }

    @GetMapping("/getAllStatusOrder")
    public CommonResponse getAllStatusOrder () {
        return new CommonResponse(HttpStatus.OK,new MessageResponse(""), statusOrderImp.getAll());
    }

    @GetMapping("/getOrderByUserId/{userId}")
    public CommonResponse getOrderByUserId (@PathVariable("userId") Long userId) {
        return new CommonResponse(HttpStatus.OK,new MessageResponse(""), orderServiceImp.getAllOrderByUserId(userId));
    }

    @GetMapping("/getOrderBySellerId/{sellerId}")
    public CommonResponse getOrderBySellerId (@PathVariable("sellerId") Long sellerId) {
        return new CommonResponse(HttpStatus.OK,new MessageResponse(""), orderServiceImp.getAllOrderBySellerId(sellerId));
    }

    @PostMapping("/order")
    public CommonResponse order(@RequestBody OrderRequest orderRequest) {
        StatusOrder statusOrder = new StatusOrder(1L,"Packing");
       for (OrderItemRequest orderItemRequest : orderRequest.getListSeller()) {
           Order order = new Order();
           order.setUserId(orderRequest.getUserID());
           order.setSellerId(orderItemRequest.getIdSeller());
           order.setPayment(orderRequest.getPayment());
           order.setShipAddress(orderRequest.getAddress());
           order.setStatusOrder(statusOrder);
           List<OrderDetails> listOrderDetails = new ArrayList<>();
           for (ProductOrderRequest productOrderRequest : orderItemRequest.getListProduct()){
               OrderDetails orderDetails = new OrderDetails();
               orderDetails.setProductId(productOrderRequest.getId());
               orderDetails.setProductName(productOrderRequest.getProductName());
               orderDetails.setProductImage(productOrderRequest.getProductImage());
               orderDetails.setProductPrice(Long.toString(productOrderRequest.getProductPrice()));
               orderDetails.setProductQuantity(productOrderRequest.getQuantity());
               orderDetails.setOrderId(order.getId());
               orderDetails.setTotal(productOrderRequest.getQuantity() * productOrderRequest.getProductPrice());
               orderDetailsRepository.save(orderDetails);
               listOrderDetails.add(orderDetails);
           }
           order.setOrderDetails(listOrderDetails);
           orderRepository.save(order);
       }
        return new CommonResponse(HttpStatus.OK,new MessageResponse("ORDER SUCCESS"), null);
    }
    @PutMapping("/updateOrderShop/{orderId}")
    public CommonResponse updateOrderShop(@PathVariable("orderId") Long orderId) {

      Order order = orderRepository.findById(orderId).get();
      order.setStatusOrder( new StatusOrder(2L,"Shipping"));
        orderRepository.save(order);
        return new CommonResponse(HttpStatus.OK,new MessageResponse("SUCCESS"), null);

    }
    @PutMapping("/updateOrderUser/{orderId}")
    public CommonResponse updateOrderUser(@PathVariable("orderId") Long orderId) {

        Order order = orderRepository.findById(orderId).get();
        order.setStatusOrder( new StatusOrder(3L,"Success"));
        orderRepository.save(order);
        List<OrderDetails> listOrderDetails = order.getOrderDetails();
        for (OrderDetails orderDetails : listOrderDetails) {
            Product product = productRepository.findById(orderDetails.getProductId()).get();
            product.setQuantity(product.getQuantity()-orderDetails.getProductQuantity());
            productRepository.save(product);
        }
        return new CommonResponse(HttpStatus.OK,new MessageResponse("SUCCESS"), null);

    }
}
