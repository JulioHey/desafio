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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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

    public Optional<Vehicle> getVeiculeById(int id) {
        return veiculeRepository.findById(id);
    }

    public boolean deleteVeicule(int id) {
        if (veiculeRepository.existsById(id)) {
            veiculeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Vehicle> getFilteredVehiclesLastWeek() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Vehicle> query = cb.createQuery(Vehicle.class);
        Root<Vehicle> vehicle = query.from(Vehicle.class);

        List<Predicate> predicates = new ArrayList<>();

        LocalDate oneWeekAgo = LocalDate.now().minusWeeks(1);
        Date lastWeekStart = Date.from(oneWeekAgo.atStartOfDay(ZoneId.systemDefault()).toInstant());

        predicates.add(cb.greaterThanOrEqualTo(vehicle.get("created"), lastWeekStart));

        query.select(vehicle)
                .where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }

    public List<Vehicle> getFilteredVehiclesByDecade(int decada) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Vehicle> query = cb.createQuery(Vehicle.class);
        Root<Vehicle> vehicle = query.from(Vehicle.class);

        List<Predicate> predicates = new ArrayList<>();

        // Calculate start and end of the decade
        int startYear = decada;
        int endYear = decada + 9;

        // Ensure the 'ano' field is within the specified decade
        predicates.add(cb.between(vehicle.get("ano"), startYear, endYear));

        query.select(vehicle)
                .where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }

    public List<Vehicle> getNotSold() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Vehicle> query = cb.createQuery(Vehicle.class);
        Root<Vehicle> vehicle = query.from(Vehicle.class);

        List<Predicate> predicates = new ArrayList<>();


        // Ensure the 'ano' field is within the specified decade
        predicates.add(cb.equal(vehicle.get("vendido"), false));

        query.select(vehicle)
                .where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}
