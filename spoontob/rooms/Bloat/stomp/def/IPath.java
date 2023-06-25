package net.runelite.client.plugins.spoontob.rooms.Bloat.stomp.def;

import java.util.Arrays;
import java.util.List;
import net.runelite.client.plugins.spoontob.rooms.Bloat.stomp.SSLine;
import org.apache.commons.lang3.tuple.Pair;

public interface IPath {
    public static final int[] NW_OFFSETS = new int[] { -1, 0, 0, 1 };

    public static final int[] NE_OFFSETS = new int[] { 1, 0, 0, 1 };

    public static final int[] SW_OFFSETS = new int[] { -1, 0, 0, -1 };

    public static final int[] SE_OFFSETS = new int[] { 1, 0, 0, -1 };

    public static final int SIDE_MAX_OFFSET = 4;

    public static final int SIDE_MIN_OFFSET = 2;

    default List<Pair<SSLine[], int[]>> getCornerSafespots(SSLine[][][] bloatGrid) {
        return null;
    }

    default SSLine[] getSideSafespotLines(SSLine[][][] bloatGrid) {
        return null;
    }

    default boolean areOffsetsNegative() {
        return false;
    }

    default boolean shouldOffsetX() {
        return false;
    }

    default boolean shouldOffsetY() {
        return false;
    }

    default List<Integer> getSideOffsets(boolean sideMin) {
        return Arrays.asList(new Integer[] { Integer.valueOf(sideMin ? (areOffsetsNegative() ? -4 : 4) : (areOffsetsNegative() ? -2 : 2)), Integer.valueOf(sideMin ? (areOffsetsNegative() ? -2 : 2) : (areOffsetsNegative() ? -4 : 4)) });
    }

    static SSLine[] getNWCornerLines(SSLine[][][] bloatGrid) {
        return new SSLine[] { bloatGrid[0][1][0], bloatGrid[1][1][0] };
    }

    static SSLine[] getNECornerLines(SSLine[][][] bloatGrid) {
        return new SSLine[] { bloatGrid[0][0][0], bloatGrid[1][1][1] };
    }

    static SSLine[] getSWCornerLines(SSLine[][][] bloatGrid) {
        return new SSLine[] { bloatGrid[0][1][1], bloatGrid[1][0][0] };
    }

    static SSLine[] getSECornerLines(SSLine[][][] bloatGrid) {
        return new SSLine[] { bloatGrid[0][0][1], bloatGrid[1][0][1] };
    }

    static SSLine[] getNorthLines(SSLine[][][] bloatGrid) {
        return new SSLine[] { bloatGrid[1][0][0], bloatGrid[1][0][1] };
    }

    static SSLine[] getEastLines(SSLine[][][] bloatGrid) {
        return new SSLine[] { bloatGrid[0][1][0], bloatGrid[0][1][1] };
    }

    static SSLine[] getSouthLines(SSLine[][][] bloatGrid) {
        return new SSLine[] { bloatGrid[1][1][1], bloatGrid[1][1][0] };
    }

    static SSLine[] getWestLines(SSLine[][][] bloatGrid) {
        return new SSLine[] { bloatGrid[0][0][1], bloatGrid[0][0][0] };
    }
}
