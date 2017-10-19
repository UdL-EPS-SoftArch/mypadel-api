package cat.udl.eps.softarch.mypadel.handler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
public class createMatchException extends RuntimeException {

	public createMatchException(String message) {
		super(message);
	}
}
