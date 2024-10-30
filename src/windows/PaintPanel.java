package windows;

import constants.Constants;
import geometry.Flat;
import logic.ZBuffer;
import geometry.Point3D;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PaintPanel extends JPanel {

    private final ZBuffer zBuffer;

    public PaintPanel(ZBuffer zBuffer) {
        this.zBuffer = zBuffer;
    }

    public boolean isPointInsidePolygon(List<Point3D> polygon, Point p) {
        int n = polygon.size();
        boolean inside = false;

        for (int i = 0, j = n - 1; i < n; j = i++) {
            Point3D pi = polygon.get(i);
            Point3D pj = polygon.get(j);

            // Проверьте, пересекает ли луч от точки p сторону многоугольника
            if ((pi.y > p.y) != (pj.y > p.y) &&
                    (p.x < (pj.x - pi.x) * (p.y - pi.y) / (pj.y - pi.y) + pi.x)) {
                inside = !inside;
            }
        }

        return inside;
    }

    public void DrawPolygon(List<Point3D> polygon, int color) {
        Flat flat = new Flat(polygon);
        for (int i = 0; i < Constants.HEIGHT; i++) {
            for (int j = 0; j < Constants.WIDTH; j++) {
                if (isPointInsidePolygon(polygon, new Point(i, j))) {
                    zBuffer.update(i, j, flat.getZ(i, j), color);
                }
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Рисуем фрейм-буфер
        for (int y = 0; y < Constants.HEIGHT; y++) {
            for (int x = 0; x < Constants.WIDTH; x++) {
                g.setColor(new Color(zBuffer.getColor(x, y)));
                g.drawLine(x, y, x, y);
            }
        }
    }

}
