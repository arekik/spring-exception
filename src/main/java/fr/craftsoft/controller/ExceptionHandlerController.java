package fr.craftsoft.controller;

import fr.craftsoft.exception.CustomException;
import fr.craftsoft.exception.DataBaseException;
import fr.craftsoft.exception.ErrorInfo;
import fr.craftsoft.exception.NotFoundException;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ayr on 05/01/17.
 */

@ControllerAdvice
public class ExceptionHandlerController {

    final static Logger LOG = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NotFoundException.class)
    @ResponseBody
    public ErrorInfo handleNotFoundException(final HttpServletRequest request, final Exception exception) {
        logException(exception);
        return new ErrorInfo(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = DataBaseException.class)
    @ResponseBody
    public ErrorInfo handleDataBaseException(final HttpServletRequest request, final Exception exception) {
        logException(exception);
        return new ErrorInfo(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ CustomException.class, Exception.class })
    @ResponseBody
    public ErrorInfo handleBadRequest(HttpServletRequest request, Exception exception) {
        logException(exception);
        return new ErrorInfo(exception, request.getRequestURI());
    }


    private void logException(Exception exception) {
        LOG.error(exception.getMessage(), exception);
    }

}
