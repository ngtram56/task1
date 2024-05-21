package com.example.task.service;

import com.example.task.DTO.VehicleDTO;
import com.example.task.model.Brand;
import com.example.task.model.Vehicle;
import com.example.task.repository.BrandRepository;
import com.example.task.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private BrandRepository brandRepository;

    public List<Vehicle> getAllVehicle() {
        return vehicleRepository.findAll();
    }

    public Vehicle saveOrUpdateVehicle(Long id, VehicleDTO vehicleDTO) {
        Vehicle vehicle;
        if (id != null) {
            vehicle = vehicleRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phương tiện với ID: " + id));
        } else {
            vehicle = new Vehicle();
        }

        Brand brand = brandRepository.findById(vehicleDTO.getBrandId())
                .orElseThrow(() -> new IllegalArgumentException("ID không hợp lệ"));

        vehicle.setVehicleName(vehicleDTO.getVehicleName());
        vehicle.setYear(vehicleDTO.getYear());
        vehicle.setPrice(vehicleDTO.getPrice());
        vehicle.setOwner(vehicleDTO.getOwner());
        vehicle.setInstant(LocalDate.now());
        vehicle.setBrand(brand);

        return vehicleRepository.save(vehicle);
    }


    public Vehicle addVehicle(VehicleDTO vehicleDTO) {
        return saveOrUpdateVehicle(null, vehicleDTO);
    }

    public Vehicle updateVehicle(Long id, VehicleDTO vehicleDTO) {
        return saveOrUpdateVehicle(id, vehicleDTO);
    }

    public void deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phương tiện"));

        vehicleRepository.delete(vehicle);
    }


    public List<Vehicle> searchVehicles(Long brandId, Integer year, BigDecimal price, String owner) {
        return vehicleRepository.searchVehicles(brandId, year, price, owner);
    }

    public List<Vehicle> findVehiclesWithConditions() {
        return vehicleRepository.findVehiclesWithConditions();
    }
}
