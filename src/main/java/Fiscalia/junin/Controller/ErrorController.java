package Fiscalia.junin.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);


    public String getErrorPath() {
        return null;
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model){
        String errorPage = "error";
        String pageTitle = "Error";
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(status!=null){
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()){
                errorPage = "error/error_404";
                LOGGER.error("Error 404");
            }
        }
        return errorPage;
    }
}

