package com.example.task.controller;

import com.example.task.DTO.VehicleDTO;
import com.example.task.model.Vehicle;
import com.example.task.service.BrandService;
import com.example.task.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;
    private BrandService brandService;

    @GetMapping() //don't use verb in path
    public List<Vehicle> getVehicles(){  //use DTO to return data
        return vehicleService.getAllVehicle();
    }


    @PostMapping() //don't use verb in path
    public Vehicle addVehicle(@RequestBody VehicleDTO vehicleDTO) { //use DTO to return data
        return vehicleService.addVehicle(vehicleDTO);
    }

    @PutMapping("/{id}") //don't use verb in path
    public ResponseEntity<?> updateVehicle(@PathVariable Long id, @RequestBody VehicleDTO vehicleDTO) {
        try {
            Vehicle updatedVehicle = vehicleService.updateVehicle(id, vehicleDTO); //use DTO to return data
            return ResponseEntity.ok(updatedVehicle);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cập nhật không thành công");
        }
    }

    @DeleteMapping("/{id}") //don't use verb in path
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
        try {
            vehicleService.deleteVehicle(id);
            return ResponseEntity.ok("Xóa thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Xóa không thành công");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Vehicle>> searchVehicles(
            @RequestParam(required = false) Long brandId,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) BigDecimal price,
            @RequestParam(required = false) String owner) {

        List<Vehicle> vehicles = vehicleService.searchVehicles(brandId, year, price, owner); //use DTO to return data

        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/find-vehicles")
    public List<Vehicle> findVehiclesWithConditions() {
        return vehicleService.findVehiclesWithConditions(); //use DTO to return data
    }

}



