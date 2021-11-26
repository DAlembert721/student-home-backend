package com.astra.studenthomebackend.properties.services;

import com.astra.studenthomebackend.accounts.domain.model.LandLord;
import com.astra.studenthomebackend.accounts.domain.repository.LandLordRepository;
import com.astra.studenthomebackend.locations.domain.model.District;
import com.astra.studenthomebackend.locations.domain.repository.DistrictRepository;
import com.astra.studenthomebackend.properties.domain.repositories.ServiceRepository;
import com.astra.studenthomebackend.shared.exceptions.ResourceNotFoundException;
import com.astra.studenthomebackend.properties.domain.models.Property;
import com.astra.studenthomebackend.properties.domain.repositories.PropertyRepository;
import com.astra.studenthomebackend.properties.domain.services.PropertyService;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private LandLordRepository landLordRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public Page<Property> getAllProperties(Pageable pageable) {
        return propertyRepository.findAll(pageable);
    }

    @Override
    public Property createProperty(Long landLordId, Long districtId, Property property) {
        LandLord landLord = landLordRepository.findById(landLordId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("LandLord", "Id", landLordId));
        District district = districtRepository.findById(districtId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("District", "Id", districtId));
        property.setDistrict(district);
        property.setLandLord(landLord);
        return propertyRepository.save(property);
    }

    @Override
    public Property getPropertyById(Long propertyId) {
        return propertyRepository.findById(propertyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property", "Id", propertyId));
    }

    @Override
    public Property updateProperty(Long landLordId, Long propertyId, Long districtId, Property propertyRequest) {
        if (!landLordRepository.existsById(landLordId))
            throw new ResourceNotFoundException("LandLord", "Id", landLordId);
        District district = districtRepository.findById(districtId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("District", "Id", districtId));
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property", "Id", propertyId));
        property.setTitle(propertyRequest.getTitle());
        property.setDescription(propertyRequest.getDescription());
        property.setActive(propertyRequest.getActive());
        property.setCost(propertyRequest.getCost());
        property.setRooms(propertyRequest.getRooms());
        property.setSize(propertyRequest.getSize());
        property.setAddress(propertyRequest.getAddress());
        property.setDistrict(district);
        return propertyRepository.save(property);
    }

    @Override
    public ResponseEntity<?> deleteProperty(Long landLordId, Long propertyId) {
        if (!landLordRepository.existsById(landLordId))
            throw new ResourceNotFoundException("LandLord", "Id", landLordId);
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property", "Id", propertyId));
        return ResponseEntity.ok().build();
    }

    @Override
    public Property assignPropertyService(Long propertyId, Long serviceId) {
        com.astra.studenthomebackend.properties.domain.models.Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Service", "Id", serviceId));
        return propertyRepository.findById(propertyId)
                .map(property -> propertyRepository.save(property.addService(service)))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property", "Id", propertyId));
    }

    @Override
    public Property unAssignPropertyService(Long propertyId, Long serviceId) {
        com.astra.studenthomebackend.properties.domain.models.Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Service", "Id", serviceId));
        return propertyRepository.findById(propertyId)
                .map(property -> propertyRepository.save(property.removeService(service)))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property", "Id", propertyId));
    }

    @Override
    public Page<Property> getAllPropertiesByServiceId(Long serviceId, Pageable pageable) {
        return serviceRepository.findById(serviceId)
                .map(service -> {
                    List<Property> properties = service.getProperties();
                    return new PageImpl<>(properties, pageable, properties.size());
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("Service", "Id", serviceId));
    }

    @Override
    public Page<Property> getAllPropertiesByLandLordId(Long landLordId, Pageable pageable) {
        return propertyRepository.findByLandLordId(landLordId, pageable);
    }

    @Override
    public Page<Property> getAllActiveProperties(Boolean active, Pageable pageable) {
        return propertyRepository.findByActive(active, pageable);
    }

    @Override
    public byte[] generateReports(Pageable pageable) {
        byte[] data = null;
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("txt_titulo", "Prueba de titulo");

        try {
            File file = new ClassPathResource("/reports/inmuebles.jasper").getFile();
            JasperPrint print = JasperFillManager.fillReport(file.getPath(), parametros, new JRBeanCollectionDataSource((Collection<?>) this.getAllActiveProperties(true, pageable)));
            data = JasperExportManager.exportReportToPdf(print);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
