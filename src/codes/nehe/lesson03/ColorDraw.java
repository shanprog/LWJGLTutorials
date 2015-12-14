package codes.nehe.lesson03;

import codes.nehe.util.ProgrammWindow;

import static org.lwjgl.opengl.GL11.*;

public class ColorDraw implements ProgrammWindow {

    @Override
    public void drawGLScene() {

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();

        glTranslatef(-1.5f, 0.0f, -6.0f);

        glBegin(GL_TRIANGLES);
        glColor3f(1.0f, 0.0f, 0.0f);      // Красный цвет
        glVertex3f(0.0f, 1.0f, 0.0f);
        glColor3f(0.0f, 1.0f, 0.0f);      // Зеленный цвет
        glVertex3f(-1.0f, -1.0f, 0.0f);
        glColor3f(0.0f, 0.0f, 1.0f);      // Синий цвет
        glVertex3f(1.0f, -1.0f, 0.0f);
        glEnd();
        glTranslatef(3.0f, 0.0f, 0.0f);


        glColor3f(0.5f, 0.5f, 1.0f);      // Установим синий цвет только один раз
        glBegin(GL_QUADS);
        glVertex3f(-1.0f, 1.0f, 0.0f);
        glVertex3f(1.0f, 1.0f, 0.0f);
        glVertex3f(1.0f, -1.0f, 0.0f);
        glVertex3f(-1.0f, -1.0f, 0.0f);
        glEnd();

    }

    public static void main(String[] args) {
        new ColorDraw().run("Color Draw");
    }
}
