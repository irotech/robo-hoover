package com.gmail.at.irotech.web.rest.controller;

import com.gmail.at.irotech.service.RoboHooverService;
import com.gmail.at.irotech.web.rest.dto.request.RoboHooverRequest;
import com.gmail.at.irotech.web.rest.dto.response.RoboHooverError;
import com.gmail.at.irotech.web.rest.dto.response.RoboHooverResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * some_docs_here
 */
@RestController
@RequestMapping("/roboHoover")
public class RoboHooverController {

    private final Logger logger = LoggerFactory.getLogger(RoboHooverController.class);

    @Autowired
    private RoboHooverService roboHooverService;

    @ApiOperation(value = "Some desc here ...",
            notes = "Some other desc here ...",
            response = RoboHooverResponse.class,
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
                    response = RoboHooverResponse.class
            )
    })
    @RequestMapping(value = "/patches", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public HttpEntity<RoboHooverResponse> roboHooverPatches(@RequestBody RoboHooverRequest roboHooverReq) {
        logger.info("Fetching ...");
        RoboHooverResponse roboHooverResp;
        try {
            //implement_here
            roboHooverResp = roboHooverService.runCleaning(roboHooverReq);
        } catch (Exception e) {
            logger.error(e.getMessage());
            roboHooverResp = RoboHooverResponse.errorRoboHooverResponse(
                    Arrays.asList(new RoboHooverError(false, e.getMessage(), "")));
            return new ResponseEntity<>(roboHooverResp, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(roboHooverResp, HttpStatus.OK);
    }

}
