package gng4120.group3.project.infrastructure.controllers.error;

import gng4120.group3.project.infrastructure.exceptions.ErrorCode;
import gng4120.group3.project.payload.response.ErrorResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.Map;

@Controller
public class WebErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    public WebErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public String handleError(WebRequest request, HttpServletResponse response, Model model) {

        HttpStatus status = HttpStatus.valueOf(response.getStatus());
        Map<String, Object> errorAttributesMap = errorAttributes.getErrorAttributes(request, ErrorAttributeOptions.defaults());
        ErrorResponse exceptionResponse = new ErrorResponse();

        exceptionResponse.setStatus(status.toString());
        exceptionResponse.setStatusCode(status.value());
        exceptionResponse.setMessage((String) errorAttributesMap.get("error"));
        exceptionResponse.setPath((String) errorAttributesMap.get("path"));
        exceptionResponse.setTimestamp((Date) errorAttributesMap.get("timestamp"));

        if (status.series() == HttpStatus.Series.CLIENT_ERROR) {
            exceptionResponse.setErrorCode(ErrorCode.CLIENT_ERROR);
        } else if (status.series() == HttpStatus.Series.SERVER_ERROR) {
            exceptionResponse.setErrorCode(ErrorCode.INTERNAL_ERROR);
        }

        model.addAttribute("exception", exceptionResponse);

        return "pages/error";
    }
}
