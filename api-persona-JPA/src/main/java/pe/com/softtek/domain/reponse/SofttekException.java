package pe.com.softtek.domain.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SofttekException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;
	private Object success;

}
