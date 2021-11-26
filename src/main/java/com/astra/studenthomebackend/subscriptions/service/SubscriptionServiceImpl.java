package com.astra.studenthomebackend.subscriptions.service;


import com.astra.studenthomebackend.subscriptions.domain.model.Subscription;
import com.astra.studenthomebackend.subscriptions.domain.repository.SubscriptionRepository;
import com.astra.studenthomebackend.subscriptions.domain.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public Page<Subscription> getAllSubscriptions(Pageable pageable) {
        return subscriptionRepository.findAll(pageable);
    }
}
