package constants;

import java.awt.*;

public final class Constants {

    private Constants() {
        throw new UnsupportedOperationException("This is a constants class and cannot be instantiated");
    }

    public static final Integer WIDTH = 512;
    public static final Integer HEIGHT = 512;

    public static final Integer DEFAULT_BACKCOLOR = Color.WHITE.getRGB();
}
