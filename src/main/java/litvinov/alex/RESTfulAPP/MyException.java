package litvinov.alex.RESTfulAPP;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class MyException extends RuntimeException{
}
