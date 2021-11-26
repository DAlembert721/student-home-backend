package com.astra.studenthomebackend.subscriptions.domain.service;


import com.astra.studenthomebackend.subscriptions.domain.model.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubscriptionService {
    Page<Subscription> getAllSubscriptions(Pageable pageable);
}
