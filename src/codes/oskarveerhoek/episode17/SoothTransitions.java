package codes.oskarveerhoek.episode17;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class SoothTransitions {

    private static enum State {
        INTRO, FADING, MAIN
    }

    private State state = State.INTRO;

    public SoothTransitions() {

        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Smooth Transitions");

            Display.setVSyncEnabled(true);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(1);
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(1, 1, 1, 1, 1, -1);

        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        float fade = 0;

        while (!Display.isCloseRequested()) {

            glClear(GL_COLOR_BUFFER_BIT);


            switch (state) {
                case FADING:
                    if (fade < 90) {
                        fade += 1.5f;
                    } else {
                        fade = 0;
                        glColor3f(0.5f, 0.5f, 1f);
                        glRectf(-0.5f, -0.5f, 0.5f, 0.5f);
                        state = State.MAIN;
                        System.out.println("State chainged: " + state);
                        break;
                    }

                    glColor4f(0.5f, 0.5f, 1f, (float) Math.sin(Math.toRadians(fade)));
                    glRectf(-0.5f, -0.5f, 0.5f, 0.5f);
                    break;
                case INTRO:
                    break;
                case MAIN:
                    glColor3f(0.5f, 0.5f, 1f);
                    glRectf(-0.5f, -0.5f, 0.5f, 0.5f);
                    break;
            }


            while (Keyboard.next()) {
                // If we've pressed enter, enter the switch statement
                if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
                    // Sets the state to:
                    // fading -> main
                    // main   -> intro
                    // intro  -> fading
                    switch (state) {
                        case FADING:
                            fade = 0;
                            state = State.MAIN;
                            System.out.println("State changed: " + state);
                            break;
                        case INTRO:
                            state = State.FADING;
                            System.out.println("State changed: " + state);
                            break;
                        case MAIN:
                            state = State.INTRO;
                            System.out.println("State changed: " + state);
                            break;
                    }
                }
            }

            Display.update();
            Display.sync(60);
        }



        Display.destroy();
        System.exit(0);
    }

    public static void main(String[] args) {
        new SoothTransitions();
    }
}
