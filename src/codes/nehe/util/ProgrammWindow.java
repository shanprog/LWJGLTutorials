package codes.nehe.util;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

public interface ProgrammWindow {

    public int width = 640;
    public int height = 480;

    default void createDisplay(String title) {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle(title);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    default void initGL() {

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(45.0f, (float) width / (float) height, 0.1f, 100.0f);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();


        glShadeModel(GL_SMOOTH);

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        glClearDepth(1.0f);                                 // Разрешить очистку буфера глубины
        glEnable(GL_DEPTH_TEST);                            // Разрешить тест глубины
        glDepthFunc(GL_LEQUAL);                             // Тип теста глубины
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);  // Улучшение в вычислении перспективы
    }

    default void run(String title) {
        createDisplay(title);
        initGL();

        gameCycle();

        end();
    }

    default void end() {
        Display.destroy();
        System.exit(0);
    }

    default void gameCycle() {
        while (!Display.isCloseRequested()) {
            drawGLScene();

            Display.update();
            Display.sync(60);
        }
    }


    void drawGLScene();

}
