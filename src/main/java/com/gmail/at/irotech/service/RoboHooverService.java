package com.gmail.at.irotech.service;

import com.gmail.at.irotech.domain.model.RoboHoover;
import com.gmail.at.irotech.domain.model.Room;
import com.gmail.at.irotech.exception.RoboHooverException;
import com.gmail.at.irotech.utils.RoboHooverConstants;
import com.gmail.at.irotech.vo.RoboHooverInputsVO;
import com.gmail.at.irotech.web.rest.dto.request.RoboHooverRequest;
import com.gmail.at.irotech.web.rest.dto.response.RoboHooverError;
import com.gmail.at.irotech.web.rest.dto.response.RoboHooverResponse;
import com.gmail.at.irotech.utils.RoboHooverUtils;
import org.springframework.stereotype.*;
import org.springframework.stereotype.Component;


import java.awt.*;
import java.util.Arrays;

@Service
public class RoboHooverService implements IRoboHooverService {

    @Override
    public RoboHooverResponse runCleaning(RoboHooverRequest roboHooverReq) throws RoboHooverException {
        if(null != roboHooverReq) {
            //validate_inputs
            RoboHooverInputsVO inputsVO = new RoboHooverUtils().validateInputs(roboHooverReq);
            //init_room
            Room room = new Room(inputsVO.getRoomSize(), inputsVO.getPatchesPosition());
            //init_hoover
            RoboHoover hoover = new RoboHoover(inputsVO.getHooverInitialPosition(), room);
            //move_hoover
            hoover.executeCommands(inputsVO.getCommands());
            //display_results
            final Point finalHooverPosition = hoover.hooverPosition();
            return new RoboHooverResponse(
                    new int[] {finalHooverPosition.x, finalHooverPosition.y},
                    room.getStainRemovalCount(),
                    null);
        }
        return new RoboHooverResponse(null,
                0,
                Arrays.asList(new RoboHooverError(
                        false,
                        RoboHooverConstants.ERROR_INPUT_SERVICE,
                        "")));
    }

}
