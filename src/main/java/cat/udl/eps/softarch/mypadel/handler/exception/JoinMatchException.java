package cat.udl.eps.softarch.mypadel.handler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value= HttpStatus.FORBIDDEN)
public class JoinMatchException extends RuntimeException{

	public JoinMatchException(String message){ super(message); }
}
