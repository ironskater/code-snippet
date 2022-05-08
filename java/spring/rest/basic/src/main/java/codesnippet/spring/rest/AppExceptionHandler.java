package codesnippet.spring.rest;

import java.lang.invoke.MethodHandles;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import codesnippet.java_utility.Slf4jLogger;

@ControllerAdvice
public final class AppExceptionHandler
	extends ResponseEntityExceptionHandler
{
	private final static Slf4jLogger LOGGER =
		new Slf4jLogger(MethodHandles.lookup().lookupClass());

	@ExceptionHandler(value = {RuntimeException.class})
	public ResponseEntity<Object>
		handleException(RuntimeException ex)
	{
		LOGGER.error(	ex.getMessage(),
						ex);

		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}