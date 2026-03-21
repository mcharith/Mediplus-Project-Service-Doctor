package lk.ijse.eca.doctorservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorDTO {

    public interface OnCreate {}

    @NotBlank(groups = OnCreate.class, message = "Doctor ID is required")
    @Pattern(groups = OnCreate.class, regexp = "^[A-Z]+$",message = "Doctor ID must contain uppercase letters only (A-Z)")
    private String doctorId;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Specialization is required")
    private String specialization;
}
