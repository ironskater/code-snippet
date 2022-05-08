package codesnippet.spring.rest.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao
	extends JpaRepository<Book, Integer>
{

}
