package codesnippet.test.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import codesnippet.test.persistence.entity.grade.ScienceGrade;

@Repository
public interface ScienceGradeDao extends JpaRepository<ScienceGrade, Integer> {

    public List<ScienceGrade> findGradesByStudentId(int id);
}
