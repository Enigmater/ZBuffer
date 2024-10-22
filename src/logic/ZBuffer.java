package logic;

import java.util.ArrayList;
import java.util.List;

public class ZBuffer {
    List<List<Integer>> zBuffer;
    List<List<Integer>> frameBuffer;

    ZBuffer() {
        zBuffer = new ArrayList<>();
        frameBuffer = new ArrayList<>();
    }
}
