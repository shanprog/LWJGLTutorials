package codes.oskarveerhoek.episode14;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class CoordinateSystems {
    public static void main(String[] args) {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Coordinate Systems");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }

        // Initialization code OpenGL
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
//        glOrtho(0, 640, 480, 0, 1, -1);
        glOrtho(-1, 1, -1, 1, -1, 1);
        glMatrixMode(GL_MODELVIEW);

        while (!Display.isCloseRequested()) {
            // Render

            glClear(GL_COLOR_BUFFER_BIT);

            glBegin(GL_QUADS);
            glColor3f(1, 0, 0);
            glVertex2f(-1, -1);
            glColor3f(0, 1, 0);
            glVertex2f(1, -1);
            glColor3f(0, 0, 1);
            glVertex2f(1, 1);
            glColor3f(1, 1, 1);
            glVertex2f(-1, 1);
            glEnd();

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
        System.exit(0);
    }
}
