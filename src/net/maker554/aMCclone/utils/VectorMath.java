package net.maker554.aMCclone.utils;

import org.joml.Vector3f;
import org.joml.Vector3i;

public class VectorMath {

    public static Vector3i getNormal(DirectionEnum direction) {
        switch (direction) {
            case DirectionEnum.UP -> {
                return new Vector3i(0, 1, 0);
            }
            case DirectionEnum.DOWN -> {
                return new Vector3i(0, -1, 0);
            }
            case DirectionEnum.NORD -> {
                return new Vector3i(1, 0, 0);
            }
            case DirectionEnum.SUD -> {
                return new Vector3i(-1, 0, 0);
            }
            case DirectionEnum.EST -> {
                return new Vector3i(0, 0, 1);
            }
            case DirectionEnum.WEST -> {
                return new Vector3i(0, 0, -1);
            }
        }
        return new Vector3i(0, 0, 0);
    }

    public static Vector3f getNormalf(DirectionEnum direction) {
        switch (direction) {
            case DirectionEnum.UP -> {
                return new Vector3f(0, 1, 0);
            }
            case DirectionEnum.DOWN -> {
                return new Vector3f(0, -1, 0);
            }
            case DirectionEnum.NORD -> {
                return new Vector3f(1, 0, 0);
            }
            case DirectionEnum.SUD -> {
                return new Vector3f(-1, 0, 0);
            }
            case DirectionEnum.EST -> {
                return new Vector3f(0, 0, 1);
            }
            case DirectionEnum.WEST -> {
                return new Vector3f(0, 0, -1);
            }
        }
        return new Vector3f(0, 0, 0);
    }
}
