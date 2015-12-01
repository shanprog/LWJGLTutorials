package codes.oskarveerhoek.episode05;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.TextLoader;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

public class TextureDemo {

    private Texture wood;

    public TextureDemo() {

        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Texture Demo");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        wood = loadTexture("wood");

        // Initialixation code OpenGL
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 640, 480, 0, -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);

        while (!Display.isCloseRequested()) {

            // Render

            glClear(GL_COLOR_BUFFER_BIT);


            wood.bind();
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0, 0);
                glVertex2i(200, 200);
                glTexCoord2f(0.5f, 0);
                glVertex2i(400, 200);
                glTexCoord2f(0.5f, 0.5f);
                glVertex2i(400, 400);
                glTexCoord2f(0, 0.5f);
                glVertex2i(200, 400);
            }
            glEnd();

//            glBegin(GL_TRIANGLES);
//
//            glTexCoord2f(1, 0);
//            glVertex2i(450, 10);
//            glTexCoord2f(0, 0);
//            glVertex2i(10, 10);
//            glTexCoord2f(0, 1);
//            glVertex2i(10, 450);
//
//            glTexCoord2f(0, 1);
//            glVertex2i(10, 450);
//            glTexCoord2f(1, 1);
//            glVertex2i(450, 450);
//            glTexCoord2f(1, 0);
//            glVertex2i(450, 10);
//
//            glEnd();

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
        wood.release();

    }

    private Texture loadTexture(String key) {

        try {
            return TextureLoader.getTexture("JPG", new FileInputStream(new File("res/" + key + ".jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        new TextureDemo();
    }

}
