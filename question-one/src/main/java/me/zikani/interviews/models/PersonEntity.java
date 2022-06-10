package me.zikani.interviews.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

public class PersonEntity {
    private long id;
    @NotEmpty(message = "First Name cannot be empty")
    private String firstName;

    @NotEmpty(message = "Last Name cannot be empty")
    private String lastName;

    private String dateOfBirth;

    @NotEmpty(message = "Phone number cannot be empty")
    private String phone;

    @NotNull(message = "Created At cannot be null")
    private Timestamp createdAt;

    @NotNull(message = "Updated At cannot be null")
    private Timestamp updatedAt;

    private transient List<ComplaintsEntity> complaintsFiled;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<ComplaintsEntity> getComplaintsFiled() {
        return complaintsFiled;
    }

    public void setComplaintsFiled(List<ComplaintsEntity> complaintsFiled) {
        this.complaintsFiled = complaintsFiled;
    }
}
