package codes.oskarveerhoek.episode16;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class SideScrollerDemo {

    public SideScrollerDemo() {

        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Side Scroller");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 640, 480, 0, -1, 1);

        glMatrixMode(GL_MODELVIEW);

        float translateX = 0;

        while (!Display.isCloseRequested()) {

            glClear(GL_COLOR_BUFFER_BIT);

            glPushMatrix();
            glTranslatef(translateX, 0, 0);

            if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && Mouse.getX() > 0 && Mouse.getX() < 639) {
                translateX += Mouse.getDX();
            }

            float mousex = Mouse.getX() - translateX;
            float mousey = 480 - Mouse.getY() - 1;

            System.out.println("Mouse: x " + mousex + ", y " + mousey);

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
            glPopMatrix();

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
        System.exit(0);
    }

    public static void main(String[] args) {
        new SideScrollerDemo();
    }
}
