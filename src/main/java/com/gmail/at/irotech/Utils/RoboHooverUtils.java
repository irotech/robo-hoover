package com.gmail.at.irotech.utils;

import com.gmail.at.irotech.exception.RoboHooverException;
import com.gmail.at.irotech.vo.RoboHooverInputsVO;
import com.gmail.at.irotech.web.rest.dto.request.RoboHooverRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Point;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RoboHooverUtils {

    private final Logger logger = LoggerFactory.getLogger(RoboHooverUtils.class);

    public RoboHooverInputsVO validateInputs(RoboHooverRequest roboHooverReq) throws RoboHooverException {
        final int[] roomSize = roboHooverReq.getRoomSize();
        validateRoomSizeInput(roomSize);
        final int[] coords = roboHooverReq.getCoords();
        validateInitialPositionInput(coords, roomSize);
        final int[][] patches = roboHooverReq.getPatches();
        final Set<Point> patchesInput = validateAndConvertPatchesInput(patches, roomSize);
        final List<Character> commands = validateInstructionsInput(roboHooverReq.getInstructions());

        return new RoboHooverInputsVO(
                new Point(roomSize[0], roomSize[1]),
                new Point(coords[0], coords[1]),
                patchesInput,
                commands);
    }

    protected void validateRoomSizeInput(int[] roomSize) throws RoboHooverException {
        logger.debug("Validating input RoomSize: ", roomSize);
        if(null == roomSize || roomSize.length != 2 || roomSize[0] != roomSize[1] || roomSize[0] < 1 || roomSize[1] < 1) {
            throw new RoboHooverException(RoboHooverConstants.ERROR_INPUT_ROOMSIZE);
        }
        logger.debug("RoomSize input validation passed!");
    }

    protected void validateInitialPositionInput(int[] coords, int[] roomSize) throws RoboHooverException {
        logger.debug("Validating input Coords: ", coords);
        if(null == coords || coords.length != 2 || coords[0] > roomSize[0] || coords[1] > roomSize[1] || coords[0] < 0 || coords[1] < 0) {
            throw new RoboHooverException(RoboHooverConstants.ERROR_INPUT_INITIAL_POSITION);
        }
        logger.debug("Coords input validation passed!");
    }

    protected Set<Point> validateAndConvertPatchesInput(int[][] patches, int[] roomSize) throws RoboHooverException {
        logger.debug("Validating input Patches: ", patches);
        if(null != patches) {
            Set<Point> patchesSet = new HashSet<>();
            for(int i=0; i<patches.length; i++) {
                if(patches[i].length==2) {
                    int currentX = patches[i][0];
                    int currentY = patches[i][1];
                    if(currentX<roomSize[0] && currentY<roomSize[1]) {
                        patchesSet.add(new Point(currentX, currentY));
                    }
                }
            }
            logger.debug("RoomSize input validation passed!");
            return patchesSet;
        }
        throw new RoboHooverException(RoboHooverConstants.ERROR_INPUT_PATCHES);
    }

    protected List<Character> validateInstructionsInput(String instructions) throws RoboHooverException {
        logger.debug("Validating input Instructions: ", instructions);
        if(null != instructions) {
            LinkedList<Character> list;
            instructions = instructions.replaceAll("\\s+","").toUpperCase();
            Pattern pt = Pattern.compile("[^NESW]+");
            Matcher match = pt.matcher(instructions);
            if(!match.matches()) {
                list = new LinkedList<>();
                final char[] chars = instructions.toCharArray();
                for(int i=0; i<chars.length;i++) {
                    list.add(chars[i]);
                }
            } else {
                throw new RoboHooverException(RoboHooverConstants.ERROR_INPUT_INSTRUCTIONS);
            }
            logger.debug("RoomSize input validation passed!");
            return list;
        } else {
            throw new RoboHooverException(RoboHooverConstants.ERROR_INPUT_INSTRUCTIONS);
        }
    }

}
