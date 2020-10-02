package ng.com.idempotent.transcriptvalidator.requestobjects;

public class DepartmentRequestObject {
    private long id;
    private String departmentName;
    private String departmentCode;    
    private long facultyId;

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

}