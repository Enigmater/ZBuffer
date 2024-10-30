package runner;

import logic.ZBuffer;
import windows.MainWindow;

public class Main {
    public static void main(String[] args) {
        ZBuffer zBuffer = new ZBuffer();
        new Thread(new MainWindow(zBuffer)).start();
    }
}
