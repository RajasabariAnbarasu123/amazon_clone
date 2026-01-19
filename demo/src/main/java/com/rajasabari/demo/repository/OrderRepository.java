package com.rajasabari.demo.repository;

import com.rajasabari.demo.model.OrderEntity;
import com.rajasabari.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByUser(User user);
}
