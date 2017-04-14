package com.gmail.at.irotech.vo;

import java.awt.Point;
import java.util.*;

public class RoboHooverInputsVO {

    private final Point roomSize;
    private final Point hooverInitialPosition;
    private final Set<Point> patchesPosition;
    private final List<Character> commands;

    public RoboHooverInputsVO(Point roomSize,
            Point hooverInitialPosition,
            Set<Point> patchesPosition,
            List<Character> commands) {
        this.roomSize = roomSize;
        this.hooverInitialPosition = hooverInitialPosition;
        this.patchesPosition = patchesPosition;
        this.commands = commands;
    }

    public Point getRoomSize() {
        return roomSize;
    }

    public Point getHooverInitialPosition() {
        return hooverInitialPosition;
    }

    public Set<Point> getPatchesPosition() {
        return patchesPosition;
    }

    public List<Character> getCommands() {
        return commands;
    }

}
