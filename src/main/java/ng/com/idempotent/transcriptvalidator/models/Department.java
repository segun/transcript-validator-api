package ng.com.idempotent.transcriptvalidator.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "department")
@Table(name = "department", uniqueConstraints = {@UniqueConstraint(columnNames = {"department_name", "department_code", "faculty_id"})})
public class Department {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "department_name")
    private String departmentName;
    @Column(name = "department_code")
    private String departmentCode;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Faculty faculty;
    
    /**
     * @return String return the departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName the departmentName to set
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return String return the departmentCode
     */
    public String getDepartmentCode() {
        return departmentCode;
    }

    /**
     * @param departmentCode the departmentCode to set
     */
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    /**
     * @return Faculty return the faculty
     */
    public Faculty getFaculty() {
        return faculty;
    }

    /**
     * @param faculty the faculty to set
     */
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
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