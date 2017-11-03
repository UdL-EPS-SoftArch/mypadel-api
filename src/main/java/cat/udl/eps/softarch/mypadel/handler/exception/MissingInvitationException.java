package cat.udl.eps.softarch.mypadel.handler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value= HttpStatus.FORBIDDEN)
public class MissingInvitationException extends RuntimeException{

	public MissingInvitationException(String message){ super(message); }
}
