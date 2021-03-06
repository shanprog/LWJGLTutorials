package codes.oskarveerhoek.episode03;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class SimpleOGLRenderer {

    public SimpleOGLRenderer() {

        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Hello World");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        // Initialixation code OpenGL
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 640, 480, 0, -1, 1);
        glMatrixMode(GL_MODELVIEW);


        while (!Display.isCloseRequested()) {

            // Render

            glClear(GL_COLOR_BUFFER_BIT);

            glBegin(GL_QUADS);
            {
                glVertex2i(400, 400);
                glVertex2i(450, 400);
                glVertex2i(450, 450);
                glVertex2i(400, 450);
            }
            glEnd();

            glBegin(GL_LINES);
            {
                glVertex2i(100, 100);
                glVertex2i(200, 200);
            }
            glEnd();

            Display.update();
            Display.sync(60);
        }

        Display.destroy();

    }

    public static void main(String[] args) {
        new SimpleOGLRenderer();
    }

}
