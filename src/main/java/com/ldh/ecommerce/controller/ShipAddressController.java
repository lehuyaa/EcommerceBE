package com.ldh.ecommerce.controller;


import com.ldh.ecommerce.model.Product;
import com.ldh.ecommerce.model.ShipAddress;
import com.ldh.ecommerce.repository.ShipAddressRepository;
import com.ldh.ecommerce.repository.UserRepository;
import com.ldh.ecommerce.request.AddProductRequest;
import com.ldh.ecommerce.request.AddShipAddressRequest;
import com.ldh.ecommerce.response.CommonResponse;
import com.ldh.ecommerce.response.MessageResponse;
import com.ldh.ecommerce.service.imp.CategoryServiceImp;
import com.ldh.ecommerce.service.imp.ShipAddressServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/shipAddress")
public class ShipAddressController {

    @Autowired
    public ShipAddressServiceImp shipAddressServiceImp = new ShipAddressServiceImp();

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ShipAddressRepository shipAddressRepository;
    @GetMapping("/getByUserId/{userId}")
    public CommonResponse getByUserId (@PathVariable("userId") Long userId) {
        if (userRepository.findById(userId).get() == null) {
            return new CommonResponse(HttpStatus.BAD_REQUEST, new MessageResponse("FAILURE"), null);
        }
        return new CommonResponse(HttpStatus.OK, new MessageResponse("SUCCESS"), shipAddressServiceImp.getAllByUserId(userId));
    }

    @PostMapping("/addShipAddress")
    public CommonResponse addShipAddress(@RequestBody AddShipAddressRequest addShipAddressRequest) {

        if (addShipAddressRequest == null) {
            return new CommonResponse(HttpStatus.BAD_REQUEST, new MessageResponse("FAILURE"),null);
        } else {

            ShipAddress shipAddress = new ShipAddress();

            shipAddress.setNameReceiver(addShipAddressRequest.getNameReceiver());
            shipAddress.setStreetAddress(addShipAddressRequest.getStreetAddress());
            shipAddress.setCity(addShipAddressRequest.getCity());
            shipAddress.setDistrict(addShipAddressRequest.getDistrict());
            shipAddress.setPhoneNumber(addShipAddressRequest.getPhoneNumber());
            shipAddress.setUserId(addShipAddressRequest.getUser_id());
            shipAddress.setZipCode(addShipAddressRequest.getZipCode());
            shipAddress.setLocation(addShipAddressRequest.getLocation());
            shipAddressRepository.save(shipAddress);
            return new CommonResponse(HttpStatus.OK, new MessageResponse("SUCCESS"), null);

        }
    }

    @PutMapping("/editShipAddress/{idShipAddress}")
    public CommonResponse editProduct(@RequestBody AddShipAddressRequest addShipAddressRequest,@PathVariable("idShipAddress") Long idShipAddress) {

        if (addShipAddressRequest == null) {
            return new CommonResponse(HttpStatus.BAD_REQUEST, new MessageResponse("FAILURE"),null);
        } else {

            ShipAddress shipAddress = shipAddressRepository.findById(idShipAddress).get();
            shipAddress.setNameReceiver(addShipAddressRequest.getNameReceiver());
            shipAddress.setStreetAddress(addShipAddressRequest.getStreetAddress());
            shipAddress.setCity(addShipAddressRequest.getCity());
            shipAddress.setPhoneNumber(addShipAddressRequest.getPhoneNumber());
            shipAddress.setUserId(addShipAddressRequest.getUser_id());
            shipAddress.setZipCode(addShipAddressRequest.getZipCode());
            shipAddress.setLocation(addShipAddressRequest.getLocation());

            shipAddressRepository.save(shipAddress);

            return new CommonResponse(HttpStatus.OK, new MessageResponse("SUCCESS"), null);

        }


    }
    @DeleteMapping("/removeShipAddress/{idShipAddress}")
    public CommonResponse removeShipAddress(@PathVariable("idShipAddress") Long idShipAddress) {
        shipAddressRepository.deleteById(idShipAddress);
        return new CommonResponse(HttpStatus.OK, new MessageResponse("SUCCESS"), null);
    }
}
