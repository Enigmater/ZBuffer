package windows;

import constants.Constants;
import logic.ZBuffer;

import javax.swing.*;
import java.awt.*;

public class ZBufferGrid extends JPanel {
    private static final int GRID_SIZE = Constants.WIDTH;
    private static final int SQUARE_SIZE = 6;

    private final ZBuffer zBuffer;
    public ZBufferGrid(ZBuffer zBuffer) {
        this.zBuffer = zBuffer;
        setSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int maxDepth = zBuffer.getMaxDepth();
        for (int y = 0; y < Constants.HEIGHT; y++) {
            for (int x = 0; x < Constants.WIDTH; x++) {
                // Нормализуем значение глубины в диапазоне [0, 255]
                int depthValue = (int) (zBuffer.getDepth(x, y) * 255.0 / maxDepth);
                depthValue = Math.min(255, Math.max(0, depthValue)); // Ограничиваем значения

                // Создаем цвет в градациях серого
                int color = (depthValue << 16) | (depthValue << 8) | depthValue; // RGB (R | G | B)
                g.setColor(new Color(color));
                g.drawLine(x, y, x, y);
            }
        }
    }
}
