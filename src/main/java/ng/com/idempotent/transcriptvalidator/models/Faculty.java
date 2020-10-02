package ng.com.idempotent.transcriptvalidator.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "faculty")
@Table(name = "faculty", uniqueConstraints = {@UniqueConstraint(columnNames = {"faculty_name", "faculty_code", "school_id"})})
public class Faculty {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "faculty_name")
    private String facultyName;
    @Column(name = "faculty_code")
    private String facultyCode;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private School school;

    /**
     * @return String return the facultyName
     */
    public String getFacultyName() {
        return facultyName;
    }

    /**
     * @param facultyName the facultyName to set
     */
    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    /**
     * @return String return the facultyCode
     */
    public String getFacultyCode() {
        return facultyCode;
    }

    /**
     * @param facultyCode the facultyCode to set
     */
    public void setFacultyCode(String facultyCode) {
        this.facultyCode = facultyCode;
    }

    /**
     * @return School return the school
     */
    public School getSchool() {
        return school;
    }

    /**
     * @param school the school to set
     */
    public void setSchool(School school) {
        this.school = school;
    }


    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

}