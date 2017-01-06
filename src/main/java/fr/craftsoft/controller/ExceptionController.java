package fr.craftsoft.controller;

import fr.craftsoft.exception.CustomException;
import fr.craftsoft.exception.DataBaseException;
import fr.craftsoft.exception.NotFoundException;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/exception")
public class ExceptionController {

    @ApiOperation(value = "Envoyer une exception intercepté par le ExceptionHandlerController")
    @RequestMapping(value = "/dataBaseException", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseBody dataBaseException() throws Exception {
        throw new DataBaseException("Exception intercepté par le ExceptionHandlerController");
    }

    @ApiOperation(value = "Envoyer une exception intercepté par le ExceptionHandlerController")
    @RequestMapping(value = "/notFoundException", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseBody notFoundException() throws Exception {
        throw new NotFoundException("Exception intercepté par le ExceptionHandlerController");
    }

    @ApiOperation(value = "Envoyer une exception intercepté par le ExceptionHandlerController")
    @RequestMapping(value = "/customException", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseBody customException() throws Exception {
        throw new CustomException("Exception intercepté par le ExceptionHandlerController - pas de customException handler spécifique");
    }

}
