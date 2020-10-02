package ng.com.idempotent.transcriptvalidator.requestobjects;

public class StudentRequestObject {
    private String studentName;    
    private String matricNumber;
    private String gender;    
    private int yearOfAdmission;
    private int graduatingYear;
    private long schoolId;
    private long facultyId;
    private long departmentId;

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
     * @return String return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
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
     * @return long return the schoolId
     */
    public long getSchoolId() {
        return schoolId;
    }

    /**
     * @param schoolId the schoolId to set
     */
    public void setSchoolId(long schoolId) {
        this.schoolId = schoolId;
    }

    /**
     * @return long return the facultyId
     */
    public long getFacultyId() {
        return facultyId;
    }

    /**
     * @param facultyId the facultyId to set
     */
    public void setFacultyId(long facultyId) {
        this.facultyId = facultyId;
    }

    /**
     * @return long return the departmentId
     */
    public long getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

}
