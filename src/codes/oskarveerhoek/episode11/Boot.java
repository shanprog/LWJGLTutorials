package codes.oskarveerhoek.episode11;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class Boot {
    BlockGrid grid;

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

        grid = new BlockGrid();

        grid.setAt(10, 10, BlockType.STONE);



        while (!Display.isCloseRequested()) {

            // Render

            glClear(GL_COLOR_BUFFER_BIT);

            input();
            grid.draw();

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
    }

    private void input() {
        int mouseX = Mouse.getX();
        int mouseY = 480 - Mouse.getY() - 1;
        boolean mouseClicked = Mouse.isButtonDown(0);
        if (mouseClicked) {
            int grid_x = Math.round(mouseX / World.BLOCK_SIZE);
            int grid_y = Math.round(mouseY / World.BLOCK_SIZE);
            grid.setAt(grid_x, grid_y, BlockType.STONE);
        }

    }

    public static void main(String[] args) {
        new Boot();
    }
}
