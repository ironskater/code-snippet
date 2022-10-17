package codesnippet.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import codesnippet.test.models.ScienceGrade;

@Repository
public interface ScienceGradesDao extends JpaRepository<ScienceGrade, Integer> {

    public Iterable<ScienceGrade> findGradeByStudentId (int id);

    public void deleteByStudentId(int id);
}
