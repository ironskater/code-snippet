package codesnippet.test.persistence.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import codesnippet.test.persistence.entity.student.CollegeStudent;

@Repository
public interface StudentDao extends JpaRepository<CollegeStudent, Integer> {

    Optional<CollegeStudent> findByEmailAddress(String emailAddress);
}
