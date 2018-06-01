package in.pune.amanora.idemia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CommonException extends Exception {

	/**
	 * Exception will send : 
	 * Status :404
	 * Message : Whatever we have  passed as parameter
	 */
	private static final long serialVersionUID = 1L;

	public CommonException(String message) {
		super(message);
	}

}