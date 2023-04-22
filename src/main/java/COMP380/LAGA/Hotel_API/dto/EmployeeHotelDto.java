package COMP380.LAGA.Hotel_API.dto;

public class EmployeeHotelDto {

    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String employeePhoneNumber;
    private String role;
    private String hotelName;
    private String hotelAddress;
    private String hotelPhoneNumber;

    public EmployeeHotelDto(Long employeeId, String firstName, String lastName, String email,
                            String employeePhoneNumber, String role, String hotelName,
                            String hotelAddress, String hotelPhoneNumber) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.employeePhoneNumber = employeePhoneNumber;
        this.role = role;
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.hotelPhoneNumber = hotelPhoneNumber;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployeePhoneNumber() {
        return employeePhoneNumber;
    }

    public void setEmployeePhoneNumber(String employeePhoneNumber) {
        this.employeePhoneNumber = employeePhoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelPhoneNumber() {
        return hotelPhoneNumber;
    }

    public void setHotelPhoneNumber(String hotelPhoneNumber) {
        this.hotelPhoneNumber = hotelPhoneNumber;
    }
}
