package fr.craftsoft.controller;

import fr.craftsoft.exception.CustomException;
import fr.craftsoft.exception.ErrorInfo;
import fr.craftsoft.exception.HandledException;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/exception/withHandler")
public class WithHandlerExceptionController {

    final static Logger LOG = LoggerFactory.getLogger(WithHandlerExceptionController.class);

    @ApiOperation(value = "Envoyer une exception intercepté par le controller WithHandlerExceptionController")
    @RequestMapping(value = "/controllerHandledException", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseBody controllerHandledException() throws Exception {
        throw new CustomException("Exception intercepté par le controlleur WithHandlerExceptionController.");
    }

    @ApiOperation(value = "Envoyer une exception handled par configuration de l'exception")
    @RequestMapping(value = "/handledException", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseBody handledException() throws Exception {
        throw new HandledException("Exception Handled");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ErrorInfo handleBadRequest(HttpServletRequest request, Exception ex) {
        return new ErrorInfo(ex, request.getRequestURI());
    }
}
