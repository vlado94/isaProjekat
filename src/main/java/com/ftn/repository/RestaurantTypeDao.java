package com.ftn.repository;

import com.ftn.model.RestaurantType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantTypeDao extends JpaRepository<RestaurantType, Long> {

    Optional<RestaurantType> findByIdAndNameAndDescription(Long id, String name, String description);
}
