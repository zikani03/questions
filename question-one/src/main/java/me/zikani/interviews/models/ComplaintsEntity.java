package me.zikani.interviews.models;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;

public class ComplaintsEntity {
    // @Column(name="id")
    private long id;
    // @Column(name="description")
    @NotEmpty(message = "Description cannot be null or empty")
    private String description;

    // @Column(name="areas")
    private String areas;
    // @Column(name="filed_by")
    private int filedById;
    // @Column(name="isresolved")
    private boolean isResolved;
    // @Column(name="filed_on")
    @NotNull(message = "Filed On Date cannot be empty")
    private String filedOn;
    // @Column(name="resolved_on")
    private java.sql.Timestamp resolvedOn;
    // @Column(name="created_at")
    @NotNull(message = "Created At cannot be null or empty")
    private java.sql.Timestamp createdAt;
    // @Column(name="updated_at")
    @NotNull(message = "Updated At cannot be null or empty")
    private java.sql.Timestamp updatedAt;

    private PersonEntity filedBy;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAreas() {
        return areas;
    }

    public void setAreas(String areas) {
        this.areas = areas;
    }

    public int getFiledById() {
        return filedById;
    }

    public void setFiledById(int filedBy) {
        this.filedById = filedBy;
    }

    public PersonEntity getFiledBy() {
        return filedBy;
    }

    public void setFiledBy(PersonEntity filedBy) {
        this.filedBy = filedBy;
    }

    public boolean isIsresolved() {
        return isResolved;
    }

    public void setIsresolved(boolean isresolved) {
        this.isResolved = isresolved;
    }

    public String getFiledOn() {
        return filedOn;
    }

    public void setFiledOn(String filedOn) {
        this.filedOn = filedOn;
    }

    public Timestamp getResolvedOn() {
        return resolvedOn;
    }

    public void setResolvedOn(Timestamp resolvedOn) {
        this.resolvedOn = resolvedOn;
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
}
