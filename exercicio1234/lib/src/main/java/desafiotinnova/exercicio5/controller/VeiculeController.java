package desafiotinnova.exercicio5.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import desafiotinnova.exercicio5.dto.VeiculeDTO;
import desafiotinnova.exercicio5.model.Vehicle;
import desafiotinnova.exercicio5.service.VeiculeService;
import desafiotinnova.exercicio5.config.ModelMapperConfig;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
public class VeiculeController {

    private ModelMapper modelMapper;

    public VeiculeController() {
        ModelMapperConfig config = new ModelMapperConfig();
        this.modelMapper = config.modelMapper();
    }
    
    @Autowired
    private VeiculeService veiculeService;

    @PostMapping
    public VeiculeDTO createVeicule(@Validated @RequestBody VeiculeDTO veiculeDTO) {
        Vehicle veicule = modelMapper.map(veiculeDTO, Vehicle.class);
        veicule = veiculeService.saveVeicule(veicule);

        VeiculeDTO response = modelMapper.map(veicule, VeiculeDTO.class);
        return response;
    }

    @GetMapping
    public List<Vehicle> getAllVeicules() {
        return veiculeService.getAllVeicules();
    }

    @GetMapping("/{id}")
    public Optional<Vehicle> getVeicule(@PathVariable int id) {
        return veiculeService.getVeiculeById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteVeicule(@PathVariable int id) {
        veiculeService.deleteVeicule(id);
    }
}
