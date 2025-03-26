package desafiotinnova.exercicio5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import desafiotinnova.exercicio5.model.Vehicle;
import desafiotinnova.exercicio5.repository.VeiculeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculeService {

    @Autowired
    private VeiculeRepository veiculeRepository;

    // Save or update a Veicule
    public Vehicle saveVeicule(Vehicle veicule) {
        return veiculeRepository.save(veicule);
    }

    // Find all Veicules
    public List<Vehicle> getAllVeicules() {
        return veiculeRepository.findAll();
    }

    // Find Veicule by ID
    public Optional<Vehicle> getVeiculeById(int id) {
        return veiculeRepository.findById(id);
    }

    // Delete Veicule by ID
    public void deleteVeicule(int id) {
        veiculeRepository.deleteById(id);
    }
}
