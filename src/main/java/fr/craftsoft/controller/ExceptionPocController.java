package fr.craftsoft.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by ayr on 30/12/16.
 */

@RestController
@RequestMapping(value = "/api/exception")
public class ExceptionPocController {

    final static Logger LOG = LoggerFactory.getLogger(ExceptionPocController.class);

    @ApiOperation(value = "Envoyer une exception generique")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseBody sendException() throws Exception {
        throw new Exception();
    }
}
