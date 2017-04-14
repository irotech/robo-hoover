package com.gmail.at.irotech.domain.model;

import java.awt.*;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

public class Room {

    private final Point roomEdges;
    private final Set<Point> stains;
    private Optional<Point> hooverPosition;
    private int stainRemovalCount = 0;

    public Room(Point roomEdges, Set<Point> stains) {
        this.roomEdges = roomEdges;
        this.stains = stains;
        this.hooverPosition = Optional.empty();
    }

    public void initHoover(Point initialPosition) {
        hooverPosition = Optional.of(initialPosition);
    }

    public void moveHoover(Point newPosition) {
        hooverPosition = Optional.of(newPosition);
    }

    public boolean hasCoveredAnyStain() {
//        Iterator<Point> iterator = stains.iterator();
//        while(iterator.hasNext()) {
//            Point current = iterator.next();
//            if(current.equals(hooverPosition.get())) {
//                System.out.println(current + " -> " + hooverPosition.get());
//                return true;
//            }
//        }
//        return false;
        return stains.stream()
//                .peek(num -> System.out.println(num + " " + hooverPosition.get()))
                .filter(stain -> stain.equals(hooverPosition.get()))
                .findFirst()
                .isPresent()
                ;
    }

    public void incrementCountAndRemoveStainRecord() {
        stainRemovalCount += 1;
        stains.remove(hooverPosition.get());
    }

    public Point getRoomEdges() {
        return roomEdges;
    }

    public Set<Point> getStains() {
        return stains;
    }

    public Point getHooverPosition() {
        return hooverPosition.orElseThrow(IllegalStateException::new);
    }

    public int getStainRemovalCount() {
        return stainRemovalCount;
    }

}
