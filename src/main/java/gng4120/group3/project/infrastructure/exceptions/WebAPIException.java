package gng4120.group3.project.infrastructure.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class WebAPIException extends WebException {

    private HttpStatus status;

    private int statusCode;

    private String path;

    public WebAPIException(ErrorCode errorCode, HttpStatus status, String path) {
        super(errorCode);
        this.status = status;
        this.statusCode = status.value();
        this.path = path;
    }
}
