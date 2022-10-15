package codesnippet.test.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import codesnippet.test.persistence.entity.grade.HistoryGrade;

@Repository
public interface HistoryGradeDao extends JpaRepository<HistoryGrade, Integer> {

    public List<HistoryGrade> findGradesByStudentId(int id);
}
