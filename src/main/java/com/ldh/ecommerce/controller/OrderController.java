package com.ldh.ecommerce.controller;

import com.ldh.ecommerce.model.*;
import com.ldh.ecommerce.repository.*;
import com.ldh.ecommerce.request.OrderItemRequest;
import com.ldh.ecommerce.request.OrderRequest;
import com.ldh.ecommerce.request.ProductOrderRequest;
import com.ldh.ecommerce.response.CommonResponse;
import com.ldh.ecommerce.response.InfoProductOrder;
import com.ldh.ecommerce.response.MessageResponse;
import com.ldh.ecommerce.service.imp.OrderServiceImp;
import com.ldh.ecommerce.service.imp.PaymentMethodServiceImp;
import com.ldh.ecommerce.service.imp.StatusOrderImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    public UserRepository userRepository;

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
    @Autowired
    public NotificationRepository notificationRepository;
    @PostMapping("/order")
    public CommonResponse order(@RequestBody OrderRequest orderRequest) {
        StatusOrder statusOrder = new StatusOrder(1L,"Packing");
       for (OrderItemRequest orderItemRequest : orderRequest.getListSeller()) {
           Order order = new Order();
           User user = userRepository.findById(orderRequest.getUserID()).get();
           order.setUser(user);
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
               orderDetails.setLocation(productOrderRequest.getLocation());
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
        Notification notification = new Notification();
        notification.setTitle("Order " + orderId +" is Shipping");
        notification.setIdeReceiver(order.getUser().getId());
        notification.setContent("Your "+orderId+" order is delivering");
        notification.setType(2);
        Date myDate = new Date();
        notification.setCreateTime(myDate.toString());
        orderRepository.save(order);
        notificationRepository.save(notification);
        return new CommonResponse(HttpStatus.OK,new MessageResponse("SUCCESS"), null);

    }
    @PutMapping("/updateOrderUser/{orderId}")
    public CommonResponse updateOrderUser(@PathVariable("orderId") Long orderId) {

        Order order = orderRepository.findById(orderId).get();
        order.setStatusOrder( new StatusOrder(3L,"Success"));
        Notification notification = new Notification();
        notification.setTitle("Order "+orderId + " Success");
        notification.setContent("Customer received the "+ orderId +" order");
        notification.setIdeReceiver(order.getSellerId());
        notification.setType(3);
        Date myDate = new Date();
        notification.setCreateTime(myDate.toString());
        notificationRepository.save(notification);
        orderRepository.save(order);
        List<OrderDetails> listOrderDetails = order.getOrderDetails();
        for (OrderDetails orderDetails : listOrderDetails) {
            Product product = productRepository.findById(orderDetails.getProductId()).get();
            product.setQuantity(product.getQuantity()-orderDetails.getProductQuantity());
            productRepository.save(product);
        }
        return new CommonResponse(HttpStatus.OK,new MessageResponse("SUCCESS"), null);

    }
    @GetMapping("/getAllNotification")
    public CommonResponse getAllProduct () {
        return new CommonResponse(HttpStatus.OK,new MessageResponse(""), notificationRepository.findAll());
    }

    public boolean containArray(List<InfoProductOrder> infoProductOrderList, Long id) {
        for (InfoProductOrder infoProductOrder : infoProductOrderList) {
            if (infoProductOrder.getProductId() == id) {
                return true;
            }
        }
        return false;
    }

    public int findIndex(List<InfoProductOrder> infoProductOrderList, Long id) {
        for(int i = 0 ; i < infoProductOrderList.size() ; i ++) {
            if (infoProductOrderList.get(i).getProductId() == id) {
                return i;
            }
        }
        return -1;
    }

    @GetMapping("/getInfoAllOrderBySellerId/{sellerId}")
    public CommonResponse getInfoAllOrderBySellerId (@PathVariable("sellerId") Long sellerId) {
        List<Order> orderList = orderRepository.findAllBySellerId(sellerId);
        List<InfoProductOrder> infoProductOrderList = new ArrayList<>();
        for (Order order : orderList) {
            String nameSeller = order.getUser().getUsername();
            for (OrderDetails orderDetails : order.getOrderDetails()) {
                if (containArray(infoProductOrderList,orderDetails.getProductId())) {
                    int index = findIndex(infoProductOrderList,orderDetails.getProductId());
                    InfoProductOrder infoProductOrder = infoProductOrderList.get(index);
                    infoProductOrder.setTotal(infoProductOrder.getTotal() + orderDetails.getTotal());
                    infoProductOrder.setProductQuantity(infoProductOrder.getProductQuantity() + orderDetails.getProductQuantity());
                    List<String> listSeller = infoProductOrder.getListUser();
                    listSeller.add(nameSeller);
                    infoProductOrder.setListUser(listSeller);
                    infoProductOrderList.set(index, infoProductOrder);
                }else {
                    InfoProductOrder infoProductOrder2 = new InfoProductOrder();
                    infoProductOrder2.setProductId(orderDetails.getProductId());
                    infoProductOrder2.setProductName(orderDetails.getProductName());
                    infoProductOrder2.setProductQuantity(orderDetails.getProductQuantity());
                    infoProductOrder2.setTotal(orderDetails.getTotal());
                    infoProductOrder2.setProductImage(orderDetails.getProductImage());
                    List<String> listSeller = new ArrayList<>();
                    listSeller.add(nameSeller);
                    infoProductOrder2.setListUser(listSeller);
                    infoProductOrderList.add(infoProductOrder2);
                }
            }
        }
        return new CommonResponse(HttpStatus.OK,new MessageResponse(""), infoProductOrderList);

    }

}
