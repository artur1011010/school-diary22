package pl.arturzaczek.demoSchool.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController  {

    @RequestMapping("/error")
    public String handleError(final HttpServletRequest request) {
        log.error("url= /error, method=handleError() REQUEST: " + RequestDispatcher.ERROR_STATUS_CODE);
        final Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/error-404";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/error-500";
            }
            else if(statusCode == HttpStatus.FORBIDDEN.value()) {
                return "error/error-403";
            }
        }
        return "error/error";
    }

    @Override
    public String getErrorPath() {
        return "error/error";
    }

}
