package com.ldh.ecommerce.repository;

import com.ldh.ecommerce.model.Notification;
import com.ldh.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {


}
