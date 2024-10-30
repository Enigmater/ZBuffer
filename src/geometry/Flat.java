package geometry;

import java.util.List;

public class Flat {
    int a, b, c, d;

    public Flat(List<Point3D> polygon) {
        int size = polygon.size();
        a = 0; b = 0; c = 0; d = 0;
        // Метод Ньюэла
        for (int i = 0; i < size; i++) {
            int j = i == size - 1 ? 0 : i + 1;
            a += ((polygon.get(i).y - polygon.get(j).y) * (polygon.get(i).z + polygon.get(j).z) );
            b += ((polygon.get(i).z - polygon.get(j).z) * (polygon.get(i).x + polygon.get(j).x) );
            c += ((polygon.get(i).x - polygon.get(j).x) * (polygon.get(i).y + polygon.get(j).y) );
        }
        d = -(a * polygon.get(size - 1).x + b * polygon.get(size - 1).y + c * polygon.get(size - 1).z);
    }

    public int getZ(int x, int y) {
        if (c == 0) return 0;
        return -(a * x + b * y + d) / c;
    }
}
