package lk.ijse.eca.doctorservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lk.ijse.eca.doctorservice.dto.DoctorDTO;
import lk.ijse.eca.doctorservice.service.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor")
@RequiredArgsConstructor
@Slf4j
@Validated
public class DoctorController {

    private final DoctorService doctorService;

    private static final String DOCTOR_ID_REGEXP = "^[A-Z]+$";

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DoctorDTO> createDoctor(
            @Validated ({Default.class, DoctorDTO.OnCreate.class})
            @RequestBody DoctorDTO dto){
        log.info("POST /api/v1/doctors - Creating doctor: {}", dto.getDoctorId());
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.createDoctor(dto));
    }

    @GetMapping(value = "/{doctorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DoctorDTO> getDoctor(
            @PathVariable
            @Pattern(regexp = DOCTOR_ID_REGEXP, message = "Doctor ID must be contain uppercase letters only (A-Z)")
            String doctorId
            ){
        log.info("GET /api/v1/doctors/{}", doctorId);
        return ResponseEntity.ok(doctorService.getDoctor(doctorId));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        log.info("GET /api/v1/doctors - retrieving all doctors");
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @PutMapping(
            value = "/{doctorId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DoctorDTO> updateDoctor(
            @PathVariable
            @Pattern(regexp = DOCTOR_ID_REGEXP, message = "Doctor ID must contain uppercase letters only (A-Z)")
            String doctorId,
            @Valid @RequestBody DoctorDTO dto) {
        log.info("PUT /api/v1/doctors/{}", doctorId);
        return ResponseEntity.ok(doctorService.updateDoctor(doctorId, dto));
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<Void> deleteDoctor(
            @PathVariable
            @Pattern(regexp = DOCTOR_ID_REGEXP, message = "Doctor ID must contain uppercase letters only (A-Z)")
            String doctorId) {
        log.info("DELETE /api/v1/doctors/{}", doctorId);
        doctorService.deleteDoctor(doctorId);
        return ResponseEntity.noContent().build();
    }

}
