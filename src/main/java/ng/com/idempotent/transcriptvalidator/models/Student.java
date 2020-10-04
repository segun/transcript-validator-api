package ng.com.idempotent.transcriptvalidator.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "student")
@Table(name = "student", uniqueConstraints = {@UniqueConstraint(columnNames = "matric_number")})
public class Student {
    public static enum Gender {
        MALE, FEMALE, OTHERS
    }
    @Id
    @GeneratedValue
    private long id;
    private String studentName;    
    @Column(name = "matric_number")
    private String matricNumber;
    private Gender gender;    
    private int yearOfAdmission;
    private int graduatingYear;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private School school;    
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Department department;    
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Faculty faculty;
    
    /**
     * @return String return the studentName
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * @param studentName the studentName to set
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * @return String return the matricNumber
     */
    public String getMatricNumber() {
        return matricNumber;
    }

    /**
     * @param matricNumber the matricNumber to set
     */
    public void setMatricNumber(String matricNumber) {
        this.matricNumber = matricNumber;
    }

    /**
     * @return Gender return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * @return int return the yearOfAdmission
     */
    public int getYearOfAdmission() {
        return yearOfAdmission;
    }

    /**
     * @param yearOfAdmission the yearOfAdmission to set
     */
    public void setYearOfAdmission(int yearOfAdmission) {
        this.yearOfAdmission = yearOfAdmission;
    }

    /**
     * @return int return the graduatingYear
     */
    public int getGraduatingYear() {
        return graduatingYear;
    }

    /**
     * @param graduatingYear the graduatingYear to set
     */
    public void setGraduatingYear(int graduatingYear) {
        this.graduatingYear = graduatingYear;
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

    /**
     * @return Department return the department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(Department department) {
        this.department = department;
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

}