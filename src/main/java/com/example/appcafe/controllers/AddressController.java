package com.example.appcafe.controllers;

import com.example.appcafe.models.Address;
import com.example.appcafe.services.AddressServie;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressServie addressServie;

    @GetMapping
    public List<Address> getAllAddress() {
        return addressServie.getAllAddress();
    }

    @GetMapping("/{id}")
    public List<Address> getAddressById(@PathVariable long id) {
        return addressServie.getAddressById(id);
    }

    @PostMapping
    public Address addAddress(@RequestBody Address address) {
        return addressServie.saveAddress(address);
    }

    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable Long id ,@RequestBody Address address) {
        return addressServie.updateAddress(id, address);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable long id) {
        addressServie.deleteAddressById(id);
    }
}
