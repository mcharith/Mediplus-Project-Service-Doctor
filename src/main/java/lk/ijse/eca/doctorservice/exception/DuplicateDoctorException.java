package lk.ijse.eca.doctorservice.exception;

public class DuplicateDoctorException extends RuntimeException {
    public DuplicateDoctorException(String doctorId) {
        super("Doctor already exists: " + doctorId);
    }
}
