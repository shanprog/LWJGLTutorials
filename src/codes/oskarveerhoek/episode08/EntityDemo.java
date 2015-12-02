package codes.oskarveerhoek.episode08;

import codes.oskarveerhoek.entities.AbstractEntity;
import codes.oskarveerhoek.entities.AbstractMoveableEntity;
import codes.oskarveerhoek.entities.Entity;
import codes.oskarveerhoek.entities.MoveableEntity;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class EntityDemo {

    private long lastFrame;

    private long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    private int getDelta() {
        long currentTime = getTime();
        int delta = (int) (currentTime - lastFrame);
        lastFrame = getTime();
        return delta;
    }

    private static class Box extends AbstractMoveableEntity {
        public Box(double x, double y, double width, double height) {
            super(x, y, width, height);
        }

        @Override
        public void draw() {
            glRectd(x, y, x + width, y + height);
        }
    }

    private static class Point extends AbstractEntity {

        public Point(double x, double y) {
            super(x, y, 1, 1);
        }

        @Override
        public void draw() {
            glBegin(GL_POINTS);
            glVertex2d(x, y);
            glEnd();
        }

        @Override
        public void update(int delta) {

        }
    }

    public EntityDemo() {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Entity Demo");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        MoveableEntity box = new Box(100, 100, 50, 50);
        Entity point = new Point(10, 10);

        // Initialixation code OpenGL
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 640, 480, 0, -1, 1);
        glMatrixMode(GL_MODELVIEW);

        lastFrame = getTime();

        while (!Display.isCloseRequested()) {


            point.setLocation(Mouse.getX(), 480 - Mouse.getY() - 1);

            // Render

            glClear(GL_COLOR_BUFFER_BIT);

            int delta = getDelta();
            box.update(delta);
            point.update(delta);


            if (box.intersects(point)) {
                box.setDX(0.2);
            }

            box.draw();
            point.draw();

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
    }

    public static void main(String[] args) {
        new EntityDemo();
    }
}
