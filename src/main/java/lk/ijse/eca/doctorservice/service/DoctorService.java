package lk.ijse.eca.doctorservice.service;

import lk.ijse.eca.doctorservice.dto.DoctorDTO;

import java.util.List;

public interface DoctorService {

    DoctorDTO createDoctor(DoctorDTO dto);

    DoctorDTO getDoctor(String doctorId);

    List<DoctorDTO> getAllDoctors();

    DoctorDTO updateDoctor(String doctorId, DoctorDTO dto);

    void deleteDoctor(String doctorId);
}
