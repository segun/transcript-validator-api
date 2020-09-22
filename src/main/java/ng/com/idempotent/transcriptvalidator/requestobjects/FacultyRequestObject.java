package ng.com.idempotent.transcriptvalidator.requestobjects;

public class FacultyRequestObject {
    private String facultyName;
    private String facultyCode;
    private long schoolId;

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

}
