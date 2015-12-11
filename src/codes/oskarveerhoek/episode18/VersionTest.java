package codes.oskarveerhoek.episode18;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;


public class VersionTest {
    public static void main(String[] args) {
        try {
            Display.create();
            System.out.println("Your OpenGL version is: " + GL11.glGetString(GL11.GL_VERSION));
            Display.destroy();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

    }
}
