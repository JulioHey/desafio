package desafiotinnova.exercicio5.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import desafiotinnova.exercicio5.dto.PatchVeiculeDTO;
import desafiotinnova.exercicio5.dto.VehicleFilterDTO;
import desafiotinnova.exercicio5.dto.VeiculeDTO;
import desafiotinnova.exercicio5.model.Vehicle;
import desafiotinnova.exercicio5.response.GlobalExceptionHandler;
import desafiotinnova.exercicio5.service.VeiculeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

class VehicleControllerTest {

    private MockMvc mockMvc;

    @Mock
    private VeiculeService veiculeService;

    @InjectMocks
    private VehicleController vehicleController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Support for Java 8 Time API
        objectMapper.registerModule(new Jdk8Module());

        mockMvc = MockMvcBuilders.standaloneSetup(vehicleController)
                .setControllerAdvice(new GlobalExceptionHandler()) // Add your exception handler
                .build();
        ;
    }

    @Test
    void testGetVehicle_Success() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setVeiculo("Car Model");

        when(veiculeService.getVeiculeById(1)).thenReturn(Optional.of(vehicle));

        mockMvc.perform(get("/veiculos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.veiculo").value("Car Model"));
    }

    @Test
    void testGetVehicle_NotFound() throws Exception {
        when(veiculeService.getVeiculeById(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/veiculos/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteVehicle_Success() throws Exception {
        when(veiculeService.deleteVeicule(1)).thenReturn(true);

        mockMvc.perform(delete("/veiculos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteVehicle_NotFound() throws Exception {
        when(veiculeService.deleteVeicule(1)).thenReturn(false);

        mockMvc.perform(delete("/veiculos/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSaveVehicle_Success() throws Exception {
        VeiculeDTO vehicleDTO = new VeiculeDTO();
        vehicleDTO.setVeiculo("New Car");
        vehicleDTO.setMarca("TOYOTA");
        vehicleDTO.setAno(2022);
        vehicleDTO.setDescricao("Description");
        vehicleDTO.setCor("Color");
        vehicleDTO.setVendido(false);

        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setVeiculo("New Car");

        when(veiculeService.saveVeicule(any(), any())).thenReturn(vehicle);

        mockMvc.perform(post("/veiculos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehicleDTO)))
                .andExpect(status().isCreated()).andReturn();
    }

    @Test
    void testSaveVehicle_InvalidMarca() throws Exception {
        VeiculeDTO vehicleDTO = new VeiculeDTO();
        vehicleDTO.setVeiculo("New Car");
        vehicleDTO.setMarca("INVALID_BRAND"); // Invalid brand
        vehicleDTO.setAno(2022);
        vehicleDTO.setDescricao("Description");
        vehicleDTO.setCor("Color");
        vehicleDTO.setVendido(false);

        MvcResult result = mockMvc.perform(post("/veiculos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehicleDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals("{\"marca\":\"Deve ser um nome de marca válido\"}", result.getResponse().getContentAsString());
    }

    @Test
    void testSaveVehicle_InvalidAno() throws Exception {
        VeiculeDTO vehicleDTO = new VeiculeDTO();
        vehicleDTO.setVeiculo("New Car");
        vehicleDTO.setMarca("TOYOTA");
        vehicleDTO.setAno(1800); // Invalid year (below the minimum of 1886)
        vehicleDTO.setDescricao("Description");
        vehicleDTO.setCor("Color");
        vehicleDTO.setVendido(false);

        MvcResult result = mockMvc.perform(post("/veiculos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehicleDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals("{\"ano\":\"O ano deve ser maior ou igual a 1886\"}", result.getResponse().getContentAsString());
    }

    @Test
    void testGetVehicles_Success() throws Exception {
        VehicleFilterDTO filter = new VehicleFilterDTO();
        filter.setMarca(Optional.of("TOYOTA"));
        filter.setAno(Optional.of(2020));
        filter.setCor(Optional.of("Red"));

        List<Vehicle> vehicles = List.of(new Vehicle());

        when(veiculeService.getFilteredVehicles(filter)).thenReturn(vehicles);

        mockMvc.perform(get("/veiculos")
                .param("marca", "TOYOTA")
                .param("ano", "2020")
                .param("cor", "Red")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void testGetVehicles_InvalidMarca() throws Exception {
        MvcResult result = mockMvc.perform(get("/veiculos")
                .param("marca", "TOYOTA1")
                .param("ano", "2020")
                .param("cor", "Red")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();
        assertEquals("{\"marca\":\"Deve ser um nome de marca válido\"}", result.getResponse().getContentAsString());
    }

    @Test
    void testUpdateVehicle_Success() throws Exception {
        VeiculeDTO vehicleDTO = new VeiculeDTO();
        vehicleDTO.setVeiculo("Updated Car");
        vehicleDTO.setMarca("TOYOTA");
        vehicleDTO.setAno(2022);
        vehicleDTO.setDescricao("Updated Description");
        vehicleDTO.setCor("Blue");
        vehicleDTO.setVendido(true);

        Vehicle updatedVehicle = new Vehicle();
        updatedVehicle.setId(1L);
        updatedVehicle.setVeiculo("Updated Car");

        when(veiculeService.saveVeicule(any(), any())).thenReturn(updatedVehicle);

        mockMvc.perform(put("/veiculos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehicleDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.veiculo").value("Updated Car"));
    }

    @Test
    void testUpdateVehicle_InvalidYearLessThan1886() throws Exception {
        VeiculeDTO vehicleDTO = new VeiculeDTO();
        vehicleDTO.setVeiculo("Updated Car");
        vehicleDTO.setMarca("TOYOTA");
        vehicleDTO.setAno(1800); // Invalid year
        vehicleDTO.setDescricao("Updated Description");
        vehicleDTO.setCor("Blue");
        vehicleDTO.setVendido(true);

        mockMvc.perform(put("/veiculos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehicleDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.ano").value("O ano deve ser maior ou igual a 1886"));
    }

    @Test
    void testUpdateVehicle_InvalidBrand() throws Exception {
        VeiculeDTO vehicleDTO = new VeiculeDTO();
        vehicleDTO.setVeiculo("Updated Car");
        vehicleDTO.setMarca("TOYOTA1");
        vehicleDTO.setAno(1800); // Invalid year
        vehicleDTO.setDescricao("Updated Description");
        vehicleDTO.setCor("Blue");
        vehicleDTO.setVendido(true);

        mockMvc.perform(put("/veiculos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehicleDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.marca").value("Deve ser um nome de marca válido"));
    }

    @Test
    void testUpdateVehicle_InvalidYearMoreThan2025() throws Exception {
        VeiculeDTO vehicleDTO = new VeiculeDTO();
        vehicleDTO.setVeiculo("Updated Car");
        vehicleDTO.setMarca("TOYOTA");
        vehicleDTO.setAno(2026); // Invalid year
        vehicleDTO.setDescricao("Updated Description");
        vehicleDTO.setCor("Blue");
        vehicleDTO.setVendido(true);

        mockMvc.perform(put("/veiculos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehicleDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.ano").value("O ano deve ser menor ou igual a 2025"));
    }

    @Test
    void testPatchVehicle_Success() throws Exception {
        PatchVeiculeDTO patchVeiculeDTO = new PatchVeiculeDTO();
        patchVeiculeDTO.setVeiculo(Optional.of("Patched Car"));
        patchVeiculeDTO.setMarca(Optional.of("TOYOTA"));
        patchVeiculeDTO.setAno(Optional.of(2023));
        patchVeiculeDTO.setDescricao(Optional.of("Updated Description"));
        patchVeiculeDTO.setCor(Optional.of("Red"));
        patchVeiculeDTO.setVendido(Optional.of(true));

        Vehicle existingVehicle = new Vehicle();
        existingVehicle.setId(1L);
        existingVehicle.setVeiculo("Old Car");

        Vehicle updatedVehicle = new Vehicle();
        updatedVehicle.setId(1L);
        updatedVehicle.setVeiculo("Patched Car");

        when(veiculeService.getVeiculeById(1)).thenReturn(Optional.of(existingVehicle));
        when(veiculeService.saveVeicule(any(), any())).thenReturn(updatedVehicle);

        mockMvc.perform(patch("/veiculos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patchVeiculeDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.veiculo").value("Patched Car"));
    }

    @Test
    void testPatchVehicle_InvalidYear() throws Exception {
        PatchVeiculeDTO patchVeiculeDTO = new PatchVeiculeDTO();
        patchVeiculeDTO.setAno(Optional.of(1800)); // Invalid year

        mockMvc.perform(patch("/veiculos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patchVeiculeDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.ano").value("O ano deve estar entre 1886 e 2025"));

        patchVeiculeDTO.setAno(Optional.of(2026)); // Another invalid year

        mockMvc.perform(patch("/veiculos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patchVeiculeDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.ano").value("O ano deve estar entre 1886 e 2025"));
    }

    @Test
    void testPatchVehicle_InvalidBrand() throws Exception {
        PatchVeiculeDTO patchVeiculeDTO = new PatchVeiculeDTO();
        patchVeiculeDTO.setMarca(Optional.of("INVALID_BRAND")); // Invalid brand

        mockMvc.perform(patch("/veiculos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patchVeiculeDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.marca").value("Deve ser um nome de marca válido"));
    }

    @Test
    void testGetVehiclesLastWeek() throws Exception {
        List<Vehicle> vehicles = List.of(new Vehicle(), new Vehicle());
        when(veiculeService.getFilteredVehiclesLastWeek()).thenReturn(vehicles);

        mockMvc.perform(get("/veiculos/last-week"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetVehiclesByDecade() throws Exception {
        List<Vehicle> vehicles = List.of(new Vehicle());
        when(veiculeService.getFilteredVehiclesByDecade(1980)).thenReturn(vehicles);

        mockMvc.perform(get("/veiculos/decade/1980"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetNotSold() throws Exception {
        List<Vehicle> vehicles = List.of(new Vehicle());
        when(veiculeService.getNotSold()).thenReturn(vehicles);

        mockMvc.perform(get("/veiculos/not-sold"))
                .andExpect(status().isOk());
    }

}
