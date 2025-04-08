package com.example.appcafe.services;

import com.example.appcafe.models.Address;
import com.example.appcafe.repository.AddressRepository;
import com.example.appcafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServie {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    public List<Address> getAddressById(long id) {
        return addressRepository.findByUserId(id);
    }

    public List<Address> getAddressByUser(long id) {
        return addressRepository.findByUserId(id);
    }

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }
    public void deleteAddress(Long id,Address address) {
        address.setId(id);
        addressRepository.delete(address);
    }
    public void deleteAddressById(long id) {
        addressRepository.deleteById(id);
    }
    public Address updateAddress(Long id ,Address address) {
        address.setId(id);
        return addressRepository.save(address);
    }

}
