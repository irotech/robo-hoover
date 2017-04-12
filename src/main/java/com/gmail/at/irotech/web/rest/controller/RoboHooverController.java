package com.gmail.at.irotech.web.rest.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * some_docs_here
 */
@RestController
@RequestMapping("/roboHoover")
public class RoboHooverController {

    private final Logger logger = LoggerFactory.getLogger(RoboHooverController.class);

    @ApiOperation(value = "Some desc here ...",
            notes = "Some other desc here ...",
            response = Object.class,
            httpMethod = "POST"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 500,
                    message = "Exception"
            ),
            @ApiResponse(
                    code = 200,
                    message = "message_here",
                    response = Object.class
            )
    })
    @RequestMapping(value = "/patches", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public HttpEntity<Object> roboHooverPatches(@RequestBody Object roboHooverReq) {
        logger.info("Fetching ...");
        try {
            //implement_here
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
