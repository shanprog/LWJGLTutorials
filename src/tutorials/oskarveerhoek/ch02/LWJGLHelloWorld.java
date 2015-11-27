package tutorials.oskarveerhoek.ch02;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class LWJGLHelloWorld {

    public LWJGLHelloWorld() {

        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Hello World");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        while (!Display.isCloseRequested()) {
            Display.update();
            Display.sync(60);
        }

        Display.destroy();

    }

    public static void main(String[] args) {
        new LWJGLHelloWorld();
    }
}
