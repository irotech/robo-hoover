package com.gmail.at.irotech.web.rest.dto;

import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class RoboHooverBase {

    private final int[] coords;

    protected RoboHooverBase(
            @JsonProperty("coords") int[] coords) {
        this.coords = coords;
    }

    public int[] getCoords() {
        return coords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoboHooverBase)) return false;

        RoboHooverBase that = (RoboHooverBase) o;

        return Arrays.equals(coords, that.coords);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coords);
    }

    @Override
    public String toString() {
        return "RoboHooverBase{" +
                "coords=" + Arrays.toString(coords) +
                '}';
    }

}
