package codes.nehe.lesson04;

import codes.nehe.util.ProgrammWindow;

import static org.lwjgl.opengl.GL11.*;

public class RotateFigures implements ProgrammWindow {

    float rtri;           // Угол для треугольник
    float rquad;          // Угол для четырехугольника

    @Override
    public void drawGLScene() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);     // Очистка экрана
        //      и буфера глубины
        glLoadIdentity();                                       // Сброс просмотра
        glTranslatef(-1.5f, 0.0f, -6.0f);                         // Сдвиг в глубь экрана и влево

        glRotatef(rtri, 0.0f, 1.0f, 0.0f);
        glBegin(GL_TRIANGLES);                  // Начало рисования треугольника
        glColor3f(1.0f, 0.0f, 0.0f);      // Верхняя точка - красная
        glVertex3f(0.0f, 1.0f, 0.0f);  // Первая точка
        glColor3f(0.0f, 1.0f, 0.0f);      // Левая точка - зеленная
        glVertex3f(-1.0f, -1.0f, 0.0f);  // Вторая
        glColor3f(0.0f, 0.0f, 1.0f);      // Правая - синия
        glVertex3f(1.0f, -1.0f, 0.0f);  // Третья
        glEnd();

        glLoadIdentity();
        glTranslatef(1.5f, 0.0f, -6.0f);          // Сдвиг вправо на 1.5
        glRotatef(rquad, 1.0f, 0.0f, 0.0f);        // Вращение по оси X

        glColor3f(0.5f, 0.5f, 1.0f);              // Синий цвет
        glBegin(GL_QUADS);                      // Начнем
        glVertex3f(-1.0f, 1.0f, 0.0f);  // Верх лево
        glVertex3f(1.0f, 1.0f, 0.0f);  // Верх право
        glVertex3f(1.0f, -1.0f, 0.0f);  // Низ право
        glVertex3f(-1.0f, -1.0f, 0.0f);  // Низ лево
        glEnd();

        rtri += 0.2f;             // Увеличение переменной вращения для треугольника
        rquad -= 0.15f;           // Уменьшение переменной вращения для квадрата
    }

    public static void main(String[] args) {
        new RotateFigures().run("Rotate");
    }
}
