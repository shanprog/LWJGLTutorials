package codes.nehe.lesson02;

import codes.nehe.util.ProgrammWindow;

import static org.lwjgl.opengl.GL11.*;

public class SimpleDraw implements ProgrammWindow {

    @Override
    public void drawGLScene() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);     // Очистка экрана
        // и буфера глубины
        glLoadIdentity();// Сброс просмотра


        glTranslatef(-1.5f, 0.0f, -6.0f);
        glBegin(GL_TRIANGLES);
        glVertex3f(0.0f, 1.0f, 0.0f);  // Вверх
        glVertex3f(-1.0f, -1.0f, 0.0f);  // Слева снизу
        glVertex3f(1.0f, -1.0f, 0.0f);  // Справа снизу
        glEnd();


        glTranslatef(3.0f, 0.0f, 0.0f);
        glBegin(GL_QUADS);
        glVertex3f(-1.0f, 1.0f, 0.0f);  // Слева вверху
        glVertex3f(1.0f, 1.0f, 0.0f);  // Справа вверху
        glVertex3f(1.0f, -1.0f, 0.0f);  // Справа внизу
        glVertex3f(-1.0f, -1.0f, 0.0f);  // Слева внизу
        glEnd();
    }

    public static void main(String[] args) {
        new SimpleDraw().run("Simple Draw");
    }
}
