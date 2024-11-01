package windows;

import constants.Constants;
import geometry.Point3D;
import logic.ZBuffer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame implements Runnable {

    private ZBuffer zBuffer;

    private JPanel mainPanel;
    private PaintPanel paintPanel;
    private ZBufferGrid zBufferGrid;
    private List<List<Point3D>> polygons;

    private final Color[] colors = {
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.YELLOW,
            Color.CYAN,
            Color.MAGENTA,
            Color.ORANGE,
            Color.PINK,
    };

    public MainWindow(ZBuffer zBuffer) {
        this.zBuffer = zBuffer;

        // Задать фигуры
        setPolygons();

        setView();
        setMainPanel();
        setVisible(true);

        // Отрисовка фигур с небольшой задержкой между
        int colorID = 0;
        for (List<Point3D> poly: polygons) {
            paintPanel.DrawPolygon(poly, colors[colorID++].getRGB());
            try {
                Thread.sleep(1000);
                // Обноваить отрисовку Z-буффера
                zBufferGrid.repaint();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void setView() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("PI-11 Tihonov");
        setSize(Constants.WIDTH * 2, Constants.HEIGHT + 40);
        //setResizable(false);
        setExtendedState(JFrame.NORMAL);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        paintPanel = new PaintPanel(zBuffer);
        zBufferGrid = new ZBufferGrid(zBuffer);
        mainPanel.add(paintPanel);
        mainPanel.add(zBufferGrid);
        add(mainPanel);
    }

    private void setPolygons() {
        // Полигон 1
        List<Point3D> polygon1 = new ArrayList<>();
        polygon1.add(new Point3D(100, 30, 200));
        polygon1.add(new Point3D(100, 280, 200));
        polygon1.add(new Point3D(220, 280, 200));
        polygon1.add(new Point3D(220, 30, 200));

        // Полигон 2
        List<Point3D> polygon2 = new ArrayList<>();
        polygon2.add(new Point3D(50, 120, 100));
        polygon2.add(new Point3D(50, 200, 100));
        polygon2.add(new Point3D(270, 200, 100));
        polygon2.add(new Point3D(270, 120, 100));

        // Полигон 3
        List<Point3D> polygon3 = new ArrayList<>();
        polygon3.add(new Point3D(150, 150, 250));
        polygon3.add(new Point3D(250, 50, 50));
        polygon3.add(new Point3D(300, 250, 50));

        polygons = new ArrayList<>();
        polygons.add(polygon1);
        polygons.add(polygon2);
        polygons.add(polygon3);

        // Нормализация относительно разрешения 512*512
        for (List<Point3D> flats: polygons) {
            for (Point3D pt: flats) {
                pt.x = pt.x * Constants.WIDTH / 512;
                pt.y = pt.y * Constants.HEIGHT / 512;
            }
        }
    }

    @Override
    public void run() {

    }
}
