package com.astra.studenthomebackend.accounts.domain.service;

import com.astra.studenthomebackend.accounts.domain.model.LandLord;
import org.springframework.http.ResponseEntity;

public interface LandLordService {

    LandLord getLandLordByIdAndUserId(Long landLordId, Long userId);

    LandLord createLandLord(Long userId, Long subscriptionId, LandLord landLord);

    LandLord updateLandLord(Long userId, Long landLordId,LandLord landLordRequest);

    ResponseEntity<?> deleteLandLord(Long userId, Long landLordId);

}
