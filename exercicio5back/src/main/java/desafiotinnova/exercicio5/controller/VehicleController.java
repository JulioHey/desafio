package desafiotinnova.exercicio5.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import desafiotinnova.exercicio5.config.ModelMapperConfig;
import desafiotinnova.exercicio5.dto.PatchVeiculeDTO;
import desafiotinnova.exercicio5.dto.VehicleFilterDTO;
import desafiotinnova.exercicio5.dto.VeiculeDTO;
import desafiotinnova.exercicio5.exception.NotFoundException;
import desafiotinnova.exercicio5.exception.ValidationException;
import desafiotinnova.exercicio5.model.Brand;
import desafiotinnova.exercicio5.model.Vehicle;
import desafiotinnova.exercicio5.service.VeiculeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/veiculos")
public class VehicleController {

    @Autowired
    private VeiculeService veiculeService;

    @PostMapping()
    public ResponseEntity<VeiculeDTO> saveVehicle(@Valid @RequestBody VeiculeDTO vehicle, BindingResult result)
            throws ValidationException {
        if (result.hasErrors()) {
            throw new ValidationException(result.getFieldErrors(), result.getGlobalErrors());
        }

        ModelMapperConfig modelMapperConfig = new ModelMapperConfig();

        Vehicle savedVehicle = veiculeService.saveVeicule(modelMapperConfig.modelMapper().map(vehicle, Vehicle.class),
                result);
        return new ResponseEntity<>(modelMapperConfig.modelMapper().map(savedVehicle, VeiculeDTO.class),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculeDTO> getVehicle(@PathVariable int id) throws NotFoundException {
        Optional<Vehicle> res = veiculeService.getVeiculeById(id);

        if (res.isEmpty()) {
            throw new NotFoundException();
        }

        ModelMapperConfig modelMapperConfig = new ModelMapperConfig();

        return new ResponseEntity<>(modelMapperConfig.modelMapper().map(res, VeiculeDTO.class), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<VeiculeDTO>> getVehicles(@Valid @ModelAttribute VehicleFilterDTO filter,
            BindingResult result) throws ValidationException {
        if (result.hasErrors()) {
            throw new ValidationException(result.getFieldErrors(), result.getGlobalErrors());
        }

        List<Vehicle> vehicles = veiculeService.getFilteredVehicles(filter);

        ModelMapperConfig modelMapperConfig = new ModelMapperConfig();
        List<VeiculeDTO> vehicleDTOs = vehicles.stream()
                .map(vehicle -> modelMapperConfig.modelMapper().map(vehicle, VeiculeDTO.class))
                .toList();

        return new ResponseEntity<>(vehicleDTOs, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable int id) throws NotFoundException {
        boolean deleted = veiculeService.deleteVeicule(id);

        if (!deleted) {
            throw new NotFoundException();
        }

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculeDTO> updateVehicle(@PathVariable int id, @Valid @RequestBody VeiculeDTO vehicle,
            BindingResult result) throws ValidationException {
        if (result.hasErrors()) {
            throw new ValidationException(result.getFieldErrors(), result.getGlobalErrors());
        }
        Long longID = Long.valueOf(id);
        vehicle.setId(longID);

        ModelMapperConfig modelMapperConfig = new ModelMapperConfig();
        Vehicle updatedVehicle = veiculeService.saveVeicule(modelMapperConfig.modelMapper().map(vehicle, Vehicle.class),
                result);

        return new ResponseEntity<>(modelMapperConfig.modelMapper().map(updatedVehicle, VeiculeDTO.class),
                HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VeiculeDTO> patchVehicle(
            @PathVariable int id,
            @Valid @RequestBody PatchVeiculeDTO vehicleDTO, BindingResult result) throws NotFoundException {
        if (result.hasErrors()) {
            throw new ValidationException(result.getFieldErrors(), result.getGlobalErrors());
        }

        Vehicle existingVehicle = veiculeService.getVeiculeById(id)
                .orElseThrow(() -> new NotFoundException());

        if (vehicleDTO.getVeiculo() != null) {
            existingVehicle.setVeiculo(vehicleDTO.getVeiculo().get());
        }if (vehicleDTO.getMarca() != null) {
            existingVehicle.setMarca(Brand.valueOf(vehicleDTO.getMarca().get()));
        }if (vehicleDTO.getAno() != null) {
            existingVehicle.setAno(vehicleDTO.getAno().get());
        }if (vehicleDTO.getDescricao() != null) {
            existingVehicle.setDescricao(vehicleDTO.getDescricao().get());
        }if (vehicleDTO.getCor() != null) {
            existingVehicle.setCor(vehicleDTO.getCor().get());
        }if (vehicleDTO.isVendido() != null) {
            existingVehicle.setVendido(vehicleDTO.isVendido().get());
        }

        Vehicle updatedVehicle = veiculeService.saveVeicule(existingVehicle, result);

        ModelMapperConfig modelMapperConfig = new ModelMapperConfig();
        return ResponseEntity.ok(modelMapperConfig.modelMapper().map(updatedVehicle, VeiculeDTO.class));
    }
}
