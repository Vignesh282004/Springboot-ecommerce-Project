package com.project.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.project.model.Order;
import com.project.project.model.OrderDetail;
@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail,Long> {

    @Query("select o from Order o where o.customer.id = ?1")
    List<Order> findAllByCustomerId(Long id);

}
