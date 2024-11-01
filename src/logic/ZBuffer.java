package logic;

import constants.Constants;

import java.util.ArrayList;
import java.util.List;

public class ZBuffer {
    public List<List<Integer>> zBuffer;
    public List<List<Integer>> frameBuffer;

    public ZBuffer() {
        // Инициализация буффера кадра и Z-буффера
        zBuffer = new ArrayList<>(Constants.HEIGHT);
        frameBuffer = new ArrayList<>(Constants.HEIGHT);
        for (int i = 0; i < Constants.HEIGHT; i++) {
            List<Integer> zRow = new ArrayList<>(Constants.WIDTH);
            List<Integer> colorRow = new ArrayList<>(Constants.WIDTH);
            for (int j = 0; j < Constants.WIDTH; j++) {
                zRow.add(Integer.MIN_VALUE);                // Изначально Z-значения - бесконечность
                colorRow.add(Constants.DEFAULT_BACKCOLOR);  // Изначально цвет черный (или другой цвет по умолчанию)
            }
            zBuffer.add(zRow);
            frameBuffer.add(colorRow);
        }
    }

    // Метод для обновления Z-буфера и фрейм-буфера
    public void update(int x, int y, int z, int color) {
        // Проверка на границы
        if (x < 0 || x >= Constants.WIDTH || y < 0 || y >= Constants.HEIGHT) {
            return;
        }

        // Если новый Z больше текущего, обновляем буферы
        if (z > zBuffer.get(y).get(x)) {
            zBuffer.get(y).set(x, z);           // Обновляем Z-значение
            frameBuffer.get(y).set(x, color);   // Обновляем цвет пикселя
        }
    }

    public int getDepth(int x, int y) {
        return zBuffer.get(y).get(x);
    }

    public int getMaxDepth() {
        int maxDepth = 0;
        for (List<Integer> row : zBuffer) {
            for (int value : row) {
                if (value > maxDepth) {
                    maxDepth = value;
                }
            }
        }
        return maxDepth;
    }
    // Метод для получения цвета из frameBuffer
    public int getColor(int x, int y) {
        if (x < 0 || x >= Constants.WIDTH || y < 0 || y >= Constants.HEIGHT) {
            return Constants.DEFAULT_BACKCOLOR; // Возвращаем черный цвет, если координаты вне границ
        }
        return frameBuffer.get(y).get(x);
    }
}
