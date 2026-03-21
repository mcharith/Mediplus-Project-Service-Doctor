package lk.ijse.eca.doctorservice.exception;

public class DoctorNotFoundException extends RuntimeException {
    public DoctorNotFoundException(String doctorId) {
        super("Doctor not found: " + doctorId);
    }
}
