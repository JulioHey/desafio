package desafiotinnova.exercicio5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import desafiotinnova.exercicio5.model.Vehicle;

@Repository
public interface VeiculeRepository extends JpaRepository<Vehicle, Integer> {
    // You can add custom query methods here if needed, for example:
    // List<Veicule> findByBrand(String brand);
}