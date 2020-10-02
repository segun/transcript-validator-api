package ng.com.idempotent.transcriptvalidator.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "school")
@Table(name = "school", uniqueConstraints = {@UniqueConstraint(columnNames = {"school_name", "school_code"})})
public class School {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "school_name")
    private String schoolName;
    @Column(name = "school_code")
    private String schoolCode;

    /**
     * @return String return the schoolName
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * @param schoolName the schoolName to set
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    /**
     * @return String return the schoolCode
     */
    public String getSchoolCode() {
        return schoolCode;
    }

    /**
     * @param schoolCode the schoolCode to set
     */
    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
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