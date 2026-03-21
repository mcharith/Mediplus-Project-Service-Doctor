package lk.ijse.eca.doctorservice.mapper;

import lk.ijse.eca.doctorservice.dto.DoctorDTO;
import lk.ijse.eca.doctorservice.entity.Doctor;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface DoctorMapper {

    DoctorDTO toDto(Doctor doctor);

    Doctor toEntity(DoctorDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "doctorId", ignore = true)
    void updateEntity(DoctorDTO dto, @MappingTarget Doctor doctor);

}
