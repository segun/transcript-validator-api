package ng.com.idempotent.transcriptvalidator.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "course")
public class Course {
    @Id
    @GeneratedValue
    private long id;
    private String courseName;
    private String courseCode;
    private int year;
    private String semester;  
    private int score;
    private char grade;      
    private int gradePoint;
    private int creditUnit;
    private int cummulativePoint;
    private double gradePointAverage;    

    /**
     * @return String return the courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * @return String return the courseCode
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * @param courseCode the courseCode to set
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * @return int return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return String return the semester
     */
    public String getSemester() {
        return semester;
    }

    /**
     * @param semester the semester to set
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * @return int return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return char return the grade
     */
    public char getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(char grade) {
        this.grade = grade;
    }


    /**
     * @return int return the gradePoint
     */
    public int getGradePoint() {
        return gradePoint;
    }

    /**
     * @param gradePoint the gradePoint to set
     */
    public void setGradePoint(int gradePoint) {
        this.gradePoint = gradePoint;
    }

    /**
     * @return int return the creditUnit
     */
    public int getCreditUnit() {
        return creditUnit;
    }

    /**
     * @param creditUnit the creditUnit to set
     */
    public void setCreditUnit(int creditUnit) {
        this.creditUnit = creditUnit;
    }

    /**
     * @return int return the cummulativePoint
     */
    public int getCummulativePoint() {
        return cummulativePoint;
    }

    /**
     * @param cummulativePoint the cummulativePoint to set
     */
    public void setCummulativePoint(int cummulativePoint) {
        this.cummulativePoint = cummulativePoint;
    }

    /**
     * @return double return the gradePointAverage
     */
    public double getGradePointAverage() {
        return gradePointAverage;
    }

    /**
     * @param gradePointAverage the gradePointAverage to set
     */
    public void setGradePointAverage(double gradePointAverage) {
        this.gradePointAverage = gradePointAverage;
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