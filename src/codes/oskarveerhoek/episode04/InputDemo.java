package codes.oskarveerhoek.episode04;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.lwjgl.opengl.GL11.*;


public class InputDemo {

    private List<Box> shapes = new ArrayList<>(16);
    private boolean somethingIsSelected = false;
    private static long lastColourChange;

    public InputDemo() {

        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Input Demo");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        shapes.add(new Box(15, 15));
        shapes.add(new Box(150, 150));

        // Initialixation code OpenGL
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 640, 480, 0, -1, 1);
        glMatrixMode(GL_MODELVIEW);


        while (!Display.isCloseRequested()) {

            // Render

            glClear(GL_COLOR_BUFFER_BIT);


            while (Keyboard.next()) {
                if (Keyboard.getEventKey() == Keyboard.KEY_C && Keyboard.getEventKeyState()) {
                    shapes.add(new Box(Mouse.getX(), 480 - Mouse.getY()));
                }
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                Display.destroy();
                System.exit(0);
            }


            for (Box box : shapes) {

                if (Mouse.isButtonDown(0) && box.inBounds(Mouse.getX(), 480 - Mouse.getY()) && !somethingIsSelected) {
                    somethingIsSelected = true;
                    box.selected = true;
                }

                if (Mouse.isButtonDown(2) && box.inBounds(Mouse.getX(), 480 - Mouse.getY())) {
                    if ((System.currentTimeMillis() - lastColourChange) >= 200 /* milliseconds */) {
                        box.randomiseColor();
                        lastColourChange = System.currentTimeMillis();
                    }
                }

                if (Mouse.isButtonDown(1)) {
                    box.selected = false;
                    somethingIsSelected = false;
                }

                if (box.selected) {
                    box.update(Mouse.getDX(), -Mouse.getDY());
                }

                box.draw();
            }

            Display.update();
            Display.sync(60);
        }

        Display.destroy();

    }

    private static class Box {

        public int x, y;
        private float colorRed, colorGreen, colorBlue;
        public boolean selected = false;

        public Box(int x, int y) {
            this.x = x;
            this.y = y;

            randomiseColor();
        }

        boolean inBounds(int mouseX, int mouseY) {
            return mouseX > x && mouseX < x + 50 && mouseY > y && mouseY < y + 50;
        }

        void randomiseColor() {
            Random randomGenerator = new Random();
            colorRed = randomGenerator.nextFloat();
            colorBlue = randomGenerator.nextFloat();
            colorGreen = randomGenerator.nextFloat();
        }

        void update(int dx, int dy) {
            x += dx;
            y += dy;
        }

        void draw() {
            glColor3f(colorRed, colorGreen, colorBlue);

            glBegin(GL_QUADS);
            glVertex2f(x, y);
            glVertex2f(x + 50, y);
            glVertex2f(x + 50, y + 50);
            glVertex2f(x, y + 50);
            glEnd();
        }
    }

    public static void main(String[] args) {
        new InputDemo();
    }

}

