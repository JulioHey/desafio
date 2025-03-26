package desafiotinnova.exercicio5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import desafiotinnova.exercicio5.model.Vehicle;
import desafiotinnova.exercicio5.repository.VeiculeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.Valid;
import desafiotinnova.exercicio5.dto.VehicleFilterDTO;
import desafiotinnova.exercicio5.exception.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Validated // This ensures the validation is enabled for the service class
public class VeiculeService {

    @Autowired
    private VeiculeRepository veiculeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Vehicle saveVeicule(@Valid Vehicle veicule, BindingResult result) throws ValidationException {
        if (result.hasErrors()) {
            throw new ValidationException(result.getFieldErrors(), result.getGlobalErrors());
        }
        return veiculeRepository.save(veicule);
    }

    public List<Vehicle> getFilteredVehicles(VehicleFilterDTO req) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Vehicle> query = cb.createQuery(Vehicle.class);
        Root<Vehicle> vehicle = query.from(Vehicle.class);

        List<Predicate> predicates = new ArrayList<>();

        if (req.getMarca() != null) {
            predicates.add(cb.equal(vehicle.get("marca"), req.getMarca().get()));
        }
        if (req.getAno() != null) {
            predicates.add(cb.equal(vehicle.get("ano"), req.getAno().get()));
        }
        if (req.getCor() != null) {
            predicates.add(cb.equal(vehicle.get("cor"), req.getCor().get()));
        }

        query.select(vehicle)
                .where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }

    // Find Vehicle by ID
    public Optional<Vehicle> getVeiculeById(int id) {
        return veiculeRepository.findById(id);
    }

    // Delete Vehicle by ID
    public boolean deleteVeicule(int id) {
        if (veiculeRepository.existsById(id)) {
            veiculeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
