package com.astra.studenthomebackend.accounts.service;


import com.astra.studenthomebackend.accounts.domain.model.LandLord;
import com.astra.studenthomebackend.accounts.domain.model.auth.User;
import com.astra.studenthomebackend.accounts.domain.repository.LandLordRepository;
import com.astra.studenthomebackend.accounts.domain.repository.UserRepository;
import com.astra.studenthomebackend.accounts.domain.service.LandLordService;
import com.astra.studenthomebackend.shared.exceptions.ResourceNotFoundException;
import com.astra.studenthomebackend.subscriptions.domain.model.Subscription;
import com.astra.studenthomebackend.subscriptions.domain.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LandLordServiceImpl implements LandLordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LandLordRepository landLordRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public LandLord getLandLordByIdAndUserId(Long landLordId, Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", "Id", userId);
        }
        return landLordRepository.findByIdAndUserId(landLordId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("LandLord", "Id", landLordId));
    }

    @Override
    public LandLord createLandLord(Long userId, Long subscriptionId, LandLord landLord) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "Id", userId));
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Subscription", "Id", subscriptionId));
        landLord.setUser(user);
        landLord.setSubscription(subscription);
        landLord.setId(user.getId());
        landLord.setType("landlord");
        return landLordRepository.save(landLord);
    }

    @Override
    public LandLord updateLandLord(Long userId, Long landLordId, LandLord landLordRequest) {
        if (!landLordRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", "Id", userId);
        }
        LandLord landLord = landLordRepository.findById(landLordId)
                .orElseThrow(() -> new ResourceNotFoundException("LandLord", "Id", landLordId));
        landLord.setDni(landLordRequest.getDni());
        landLord.setFirstName(landLordRequest.getFirstName());
        landLord.setLastName(landLordRequest.getLastName());
        landLord.setPhone(landLordRequest.getPhone());
        landLord.setSubscription(landLordRequest.getSubscription());
        return landLordRepository.save(landLord);
    }

    @Override
    public ResponseEntity<?> deleteLandLord(Long userId, Long landLordId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", "Id", userId);
        }
        LandLord landLord = landLordRepository.findByIdAndUserId(landLordId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "Id", landLordId));
        landLordRepository.delete(landLord);
        return ResponseEntity.ok().build();
    }
}
