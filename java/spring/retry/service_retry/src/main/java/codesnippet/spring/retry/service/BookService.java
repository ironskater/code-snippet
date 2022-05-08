package codesnippet.spring.retry.service;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import codesnippet.java_utility.Slf4jLogger;
import codesnippet.spring.retry.jpa.Book;
import codesnippet.spring.retry.jpa.BookDao;

@Service
public class BookService
{
	private final static Slf4jLogger LOGGER =
		new Slf4jLogger(MethodHandles.lookup().lookupClass());

	private final AtomicInteger atomicInteger = new AtomicInteger(0);

	@Autowired
	private BookDao bookDao;

	/**
	 * include: 當捕捉到指定例外時會進行retry
	 * maxAttemps: 最多執行retrye幾次
	 * backoff: 當捕捉到例外時，停多少ms後再retry
	 */
	@Retryable(	include = {NoResultException.class},
				maxAttempts = 3,
				backoff = @Backoff(value = 2000))
	public List<Book>
		getAll(String sql)
	{
		int count = atomicInteger.incrementAndGet();

		LOGGER.info("count = {}",
					count);

		if(count < 5) {
			/**
			 * 因maxAttempts為3, 所以第一次call這個service只會一直走到這
			 */
			throw new NoResultException();
		} else {
			/**
			 * 第二次呼叫會從4開始計數，直到count>=5時就會執行這邊
			 */
			return bookDao.findAll();
		}
	}

	/**
	 * 1. @Recover用來定義例外處理，僅能寫在與@Retryable同一class
	 * 2. 當retry次數超過maxAttempts就會跳到對應的@Recover方法來處理
	 * 3. recover method should have the first parameter of type Throwable(optional)
	 * 4. recover method should have the same return type as getAll
	 * 5. The following arguments are populated from the argument list of the failed method in the same order.
	 */
	@Recover
	public List<Book>
		recover(NoResultException ex,
				String sql)
	{
		LOGGER.info("get NoResultException and return null, failed sql[{}]",
					sql);

		return null;
	}
}
