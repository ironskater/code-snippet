package codesnippet.test.service;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import codesnippet.test.persistence.dao.HistoryGradeDao;
import codesnippet.test.persistence.dao.MathGradeDao;
import codesnippet.test.persistence.dao.ScienceGradeDao;
import codesnippet.test.persistence.dao.StudentDao;
import codesnippet.test.persistence.entity.grade.HistoryGrade;
import codesnippet.test.persistence.entity.grade.MathGrade;
import codesnippet.test.persistence.entity.grade.ScienceGrade;
import codesnippet.test.persistence.entity.student.CollegeStudent;

@Service
@Transactional
public class StudentAndGradeService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private MathGradeDao mathGradeDao;

    @Autowired
    private ScienceGradeDao scienceGradeDao;

    @Autowired
    private HistoryGradeDao historyGradeDao;

    public void createStudent(String firstName, String lastName, String emailAddress) {

        CollegeStudent collegeStudent = new CollegeStudent(firstName, lastName, emailAddress);
        this.studentDao.save(collegeStudent);
    }

    public boolean isStudentNull(int id) {

        return !studentDao.existsById(id);
    }

    public void deleteStudent(int id) {
        this.studentDao.deleteById(id);
    }

    public List<CollegeStudent> getGradebook() {

        return this.studentDao.findAll();
    }

    public void createGrade(double grade, int studentId, String gradeType) {

        if(!this.studentDao.existsById(studentId)) {
            throw new IllegalArgumentException(MessageFormat.format("Student not exist. id[{0}]", studentId));
        }

        switch(gradeType) {

            case "math":
                this.mathGradeDao.save(new MathGrade(grade, studentId));
                break;

            case "science":
                this.scienceGradeDao.save(new ScienceGrade(grade, studentId));
                break;

            case "history":
                this.historyGradeDao.save(new HistoryGrade(grade, studentId));
                break;

            default:
                throw new IllegalArgumentException(MessageFormat.format("Invalide grade type[{0}]", gradeType));
        }
    }
}
