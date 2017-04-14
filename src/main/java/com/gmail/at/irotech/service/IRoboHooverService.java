package com.gmail.at.irotech.service;

import com.gmail.at.irotech.exception.RoboHooverException;
import com.gmail.at.irotech.web.rest.dto.request.RoboHooverRequest;
import com.gmail.at.irotech.web.rest.dto.response.RoboHooverResponse;

public interface IRoboHooverService {

    public RoboHooverResponse runCleaning(RoboHooverRequest roboHooverReq) throws RoboHooverException;

}
