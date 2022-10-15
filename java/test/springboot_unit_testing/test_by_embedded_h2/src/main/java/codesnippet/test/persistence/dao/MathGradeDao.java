package codesnippet.test.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import codesnippet.test.persistence.entity.grade.MathGrade;

@Repository
public interface MathGradeDao extends JpaRepository<MathGrade, Integer> {

    public List<MathGrade> findGradesByStudentId(int studentId);
}
