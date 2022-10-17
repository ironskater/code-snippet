package codesnippet.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import codesnippet.test.models.MathGrade;

@Repository
public interface MathGradesDao extends JpaRepository<MathGrade, Integer> {

    public Iterable<MathGrade> findGradeByStudentId (int id);

    public void deleteByStudentId(int id);
}
