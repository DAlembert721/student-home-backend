package com.astra.studenthomebackend.subscriptions.domain.repository;


import com.astra.studenthomebackend.subscriptions.domain.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
