package com.example.appcafe.repository;

import com.example.appcafe.models.Address;
import com.example.appcafe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUserId(long userId);
}
