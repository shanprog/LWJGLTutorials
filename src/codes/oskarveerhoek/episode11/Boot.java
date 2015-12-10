package codes.oskarveerhoek.episode11;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.io.File;

import static org.lwjgl.opengl.GL11.*;

public class Boot {

    private BlockGrid grid;
    private BlockType selection = BlockType.STONE;

    private int selectorX = 0, selectorY = 0;

    private boolean mouseEnabled = true;

    public Boot() {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Minecraft 2D");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        // Initialixation code OpenGL
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 640, 480, 0, -1, 1);
        glMatrixMode(GL_MODELVIEW);

        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);

        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        grid = new BlockGrid();

        while (!Display.isCloseRequested()) {

            // Render

            glClear(GL_COLOR_BUFFER_BIT);

            input();
            grid.draw();
            drawSelectionBox();

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
    }

    private void drawSelectionBox() {

        int x = selectorX * World.BLOCK_SIZE;
        int y = selectorY * World.BLOCK_SIZE;
        int x2 = x + World.BLOCK_SIZE;
        int y2 = y + World.BLOCK_SIZE;

        if (grid.getAt(selectorX, selectorY).getType() != BlockType.AIR || selection == BlockType.AIR) {
            glBindTexture(GL_TEXTURE_2D, 0);
            glColor4f(1f, 1f, 1f, 0.5f);
            glBegin(GL_QUADS);
            glVertex2i(x, y);
            glVertex2i(x2, y);
            glVertex2i(x2, y2);
            glVertex2i(x, y2);
            glEnd();
            glColor4f(1f, 1f, 1f, 1f);
        } else {
            glColor4f(1f, 1f, 1f, 0.5f);
            new Block(selection, selectorX * World.BLOCK_SIZE, selectorY * World.BLOCK_SIZE).draw();
            glColor4f(1f, 1f, 1f, 1f);
        }
    }

    private void input() {
        if (mouseEnabled || Mouse.isButtonDown(0)) {
            mouseEnabled = true;
            int mouseX = Mouse.getX();
            int mouseY = 480 - Mouse.getY() - 1;
            boolean mouseClicked = Mouse.isButtonDown(0);

            selectorX = Math.round(mouseX / World.BLOCK_SIZE);
            selectorY = Math.round(mouseY / World.BLOCK_SIZE);
            if (mouseClicked) {
                grid.setAt(selectorX, selectorY, selection);
            }
        }


        while (Keyboard.next()) {

            if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
                mouseEnabled = false;
                if (!(selectorX + 1 > World.BLOCKS_WIDTH - 2)) {
                    selectorX++;
                }
            }

            if (Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()) {
                mouseEnabled = false;
                if (!(selectorX - 1 < 0)) {
                    selectorX--;
                }
            }

            if (Keyboard.getEventKey() == Keyboard.KEY_DOWN && Keyboard.getEventKeyState()) {
                mouseEnabled = false;
                if (!(selectorY + 1 > World.BLOCKS_WIDTH - 2)) {
                    selectorY++;
                }
            }


            if (Keyboard.getEventKey() == Keyboard.KEY_UP && Keyboard.getEventKeyState()) {
                mouseEnabled = false;
                if (!(selectorY - 1 < 0)) {
                    selectorY--;
                }
            }

            if (Keyboard.getEventKey() == Keyboard.KEY_S) {
                grid.save(new File("save.xml"));
            }

            if (Keyboard.getEventKey() == Keyboard.KEY_L) {
                grid.load(new File("save.xml"));
            }

            if (Keyboard.getEventKey() == Keyboard.KEY_1) {
                selection = BlockType.STONE;
            }

            if (Keyboard.getEventKey() == Keyboard.KEY_2) {
                selection = BlockType.DIRT;
            }

            if (Keyboard.getEventKey() == Keyboard.KEY_3) {
                selection = BlockType.GRASS;
            }

            if (Keyboard.getEventKey() == Keyboard.KEY_4) {
                selection = BlockType.AIR;
            }

            if (Keyboard.getEventKey() == Keyboard.KEY_C) {
                grid.clear();
            }

            if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
                Display.destroy();
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        new Boot();
    }
}
