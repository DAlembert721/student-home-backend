package com.astra.studenthomebackend.locations.service;


import com.astra.studenthomebackend.locations.domain.model.District;
import com.astra.studenthomebackend.locations.domain.model.Province;
import com.astra.studenthomebackend.locations.domain.model.Region;
import com.astra.studenthomebackend.locations.domain.repository.DistrictRepository;
import com.astra.studenthomebackend.locations.domain.repository.ProvinceRepository;
import com.astra.studenthomebackend.locations.domain.repository.RegionRepository;
import com.astra.studenthomebackend.locations.domain.service.LocationService;
import com.astra.studenthomebackend.shared.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;


    @Override
    public Page<Region> getAllRegions(Pageable pageable) {
        return regionRepository.findAll(pageable);
    }

    @Override
    public Page<Province> getAllProvincesByRegionId(Long regionId, Pageable pageable) {
        if(!regionRepository.existsById(regionId))
            throw new ResourceNotFoundException("Region", "Id", regionId);
        return provinceRepository.findAllProvincesByRegionId(regionId, pageable);
    }

    @Override
    public Page<District> getAllDistrictsByProvinceId(Long provinceId, Pageable pageable) {
        if(!provinceRepository.existsById(provinceId))
            throw new ResourceNotFoundException("Province", "Id", provinceId);
        return districtRepository.findAllDistrictsByProvinceId(provinceId, pageable);
    }
}