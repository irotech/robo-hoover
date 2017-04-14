package com.gmail.at.irotech.domain.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Point;
import java.util.*;

public class RoboHoover {

    private final Logger logger = LoggerFactory.getLogger(RoboHoover.class);

    private final Point initialPosition;
    private final Room roomToClean;

    public RoboHoover(Point initialPosition, Room roomToClean) {
        this.initialPosition = initialPosition;
        this.roomToClean = roomToClean;
        initHoover(initialPosition);
    }

    public void executeCommands(List<Character> commands) {
        logger.info("Executing commands: " + commands.toString());
        commands.stream().forEach(command -> {
            switch(Character.toUpperCase(command)) {
                case 'N':
                    moveHoover(moveNorth());
                    break;
                case 'E':
                    moveHoover(moveEast());
                    break;
                case 'S':
                    moveHoover(moveSouth());
                    break;
                case 'W':
                    moveHoover(moveWest());
                    break;
                default:
                    logger.error("Command not recognised: " + command);
            }
            if(roomToClean.hasCoveredAnyStain()) {
                roomToClean.incrementCountAndRemoveStainRecord();
            }
        });
    }

    public Point getInitialPosition() {
        return initialPosition;
    }

    public Point hooverPosition() {
        return this.roomToClean.getHooverPosition();
    }

    public Point moveNorth() {
        Point currentPosition = hooverPosition();
        int upperRoomEdge = this.roomToClean.getRoomEdges().y;
        if(currentPosition.y < upperRoomEdge) {
            return new Point(currentPosition.x, currentPosition.y+1);
        }
        return currentPosition;
    }

    public Point moveSouth() {
        Point currentPosition = hooverPosition();
        if(currentPosition.y > 0) {
            return new Point(currentPosition.x, currentPosition.y-1);
        }
        return currentPosition;
    }

    public Point moveWest() {
        Point currentPosition = hooverPosition();
        if(currentPosition.x > 0) {
            return new Point(currentPosition.x-1, currentPosition.y);
        }
        return currentPosition;
    }

    public Point moveEast() {
        Point currentPosition = hooverPosition();
        int easternRoomEdge = this.roomToClean.getRoomEdges().x;
        if(currentPosition.x < easternRoomEdge) {
            return new Point(currentPosition.x+1, currentPosition.y);
        }
        return currentPosition;
    }

    private void initHoover(Point initPosition) {
        this.roomToClean.initHoover(initPosition);
    }

    private void moveHoover(Point nextPosition) {
        this.roomToClean.moveHoover(nextPosition);
    }

}
