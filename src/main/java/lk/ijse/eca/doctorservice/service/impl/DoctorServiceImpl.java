package lk.ijse.eca.doctorservice.service.impl;

import lk.ijse.eca.doctorservice.dto.DoctorDTO;
import lk.ijse.eca.doctorservice.entity.Doctor;
import lk.ijse.eca.doctorservice.exception.DoctorNotFoundException;
import lk.ijse.eca.doctorservice.exception.DuplicateDoctorException;
import lk.ijse.eca.doctorservice.mapper.DoctorMapper;
import lk.ijse.eca.doctorservice.repository.DoctorRepository;
import lk.ijse.eca.doctorservice.service.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    @Override
    @Transactional
    public DoctorDTO createDoctor(DoctorDTO dto) {
        log.info("Creating doctor: {}", dto.getDoctorId());

        if (doctorRepository.existsById(dto.getDoctorId())){
            log.warn("Doctor with ID {} already exists", dto.getDoctorId());
            throw new DuplicateDoctorException(dto.getDoctorId());
        }

        Doctor saved = doctorRepository.save(doctorMapper.toEntity(dto));
        log.info("Doctor Create Successfully: {}", saved.getDoctorId());
        return doctorMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public DoctorDTO getDoctor(String doctorId) {
        log.info("Getting doctor: {}", doctorId);
        return  doctorRepository.findById(doctorId)
                .map(doctorMapper::toDto)
                .orElseThrow(() ->{
                    log.warn("Doctor with ID {} not found", doctorId);
                    return new DoctorNotFoundException(doctorId);
                });
    }

    @Override
    @Transactional(readOnly = true)
    public List<DoctorDTO> getAllDoctors() {
        log.info("Getting all doctors");
        List<DoctorDTO> doctors = doctorRepository.findAll()
                .stream()
                .map(doctorMapper::toDto)
                .toList();
        log.debug("Fetched {} doctors.", doctors.size());
        return doctors;
    }

    @Override
    @Transactional
    public DoctorDTO updateDoctor(String doctorId, DoctorDTO dto) {
        log.info("Updating doctor: {}", doctorId);
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> {
                    log.warn("Doctor with ID {} not found", doctorId);
                    return new DoctorNotFoundException(doctorId);
                });
        doctorMapper.updateEntity(dto, doctor);
        Doctor updated = doctorRepository.save(doctor);
        log.info("Doctor updated successfully: {}", updated.getDoctorId());
        return doctorMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deleteDoctor(String doctorId) {
        log.info("Deleting doctor: {}", doctorId);

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> {
                    log.warn("Doctor with ID {} not found", doctorId);
                    return new DoctorNotFoundException(doctorId);
                });
        doctorRepository.delete(doctor);
        log.info("Doctor deleted successfully: {}", doctorId);
    }
}
