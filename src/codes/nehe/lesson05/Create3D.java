package codes.nehe.lesson05;

import codes.nehe.util.ProgrammWindow;

import static org.lwjgl.opengl.GL11.*;

public class Create3D implements ProgrammWindow {

    float rtri = 0;           // Угол для треугольник
    float rquad = 0;          // Угол для четырехугольника

    @Override
    public void drawGLScene() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);// Очистка экрана и буфера глубины
        glLoadIdentity();                               // Сброс просмотра

        glTranslatef(-1.5f, 0.0f, -6.0f);                 // Сдвиг влево и вглубь экрана
        glRotatef(rtri, 0.0f, 1.0f, 0.0f);                 // Вращение пирамиды по оси Y

        glBegin(GL_TRIANGLES);                          // Начало рисования пирамиды
        glColor3f(1.0f, 0.0f, 0.0f);                      // Красный
        glVertex3f(0.0f, 1.0f, 0.0f);                  // Верх треугольника (Передняя)
        glColor3f(0.0f, 1.0f, 0.0f);                      // Зеленный
        glVertex3f(-1.0f, -1.0f, 1.0f);                  // Левая точка
        glColor3f(0.0f, 0.0f, 1.0f);                      // Синий
        glVertex3f(1.0f, -1.0f, 1.0f);                  // Правая точка

        glColor3f(1.0f, 0.0f, 0.0f);                      // Красная
        glVertex3f(0.0f, 1.0f, 0.0f);                  // Верх треугольника (Правая)
        glColor3f(0.0f, 0.0f, 1.0f);                      // Синия
        glVertex3f(1.0f, -1.0f, 1.0f);                  // Лево треугольника (Правая)
        glColor3f(0.0f, 1.0f, 0.0f);                      // Зеленная
        glVertex3f(1.0f, -1.0f, -1.0f);                 // Право треугольника (Правая)

        glColor3f(1.0f, 0.0f, 0.0f);                      // Красный
        glVertex3f(0.0f, 1.0f, 0.0f);                  // Низ треугольника (Сзади)
        glColor3f(0.0f, 1.0f, 0.0f);                      // Зеленный
        glVertex3f(1.0f, -1.0f, -1.0f);                 // Лево треугольника (Сзади)
        glColor3f(0.0f, 0.0f, 1.0f);                      // Синий
        glVertex3f(-1.0f, -1.0f, -1.0f);                 // Право треугольника (Сзади)

        glColor3f(1.0f, 0.0f, 0.0f);                      // Красный
        glVertex3f(0.0f, 1.0f, 0.0f);                  // Верх треугольника (Лево)
        glColor3f(0.0f, 0.0f, 1.0f);                      // Синий
        glVertex3f(-1.0f, -1.0f, -1.0f);                  // Лево треугольника (Лево)
        glColor3f(0.0f, 1.0f, 0.0f);                      // Зеленный
        glVertex3f(-1.0f, -1.0f, 1.0f);                  // Право треугольника (Лево)
        glEnd();


        glLoadIdentity();
        glTranslatef(1.5f, 0.0f, -7.0f);          // Сдвинуть вправо и вглубь экрана
        glRotatef(rquad, 1.0f, 1.0f, 1.0f);        // Вращение куба по X, Y & Z
        glBegin(GL_QUADS);                      // Рисуем куб
        glColor3f(0.0f, 1.0f, 0.0f);              // Синий
        glVertex3f(1.0f, 1.0f, -1.0f);          // Право верх квадрата (Верх)
        glVertex3f(-1.0f, 1.0f, -1.0f);          // Лево верх
        glVertex3f(-1.0f, 1.0f, 1.0f);          // Лево низ
        glVertex3f(1.0f, 1.0f, 1.0f);          // Право низ
        glColor3f(1.0f, 0.5f, 0.0f);              // Оранжевый
        glVertex3f(1.0f, -1.0f, 1.0f);          // Верх право квадрата (Низ)
        glVertex3f(-1.0f, -1.0f, 1.0f);          // Верх лево
        glVertex3f(-1.0f, -1.0f, -1.0f);          // Низ лево
        glVertex3f(1.0f, -1.0f, -1.0f);          // Низ право
        glColor3f(1.0f, 0.0f, 0.0f);              // Красный
        glVertex3f(1.0f, 1.0f, 1.0f);          // Верх право квадрата (Перед)
        glVertex3f(-1.0f, 1.0f, 1.0f);          // Верх лево
        glVertex3f(-1.0f, -1.0f, 1.0f);          // Низ лево
        glVertex3f(1.0f, -1.0f, 1.0f);          // Низ право
        glColor3f(1.0f, 1.0f, 0.0f);              // Желтый
        glVertex3f(1.0f, -1.0f, -1.0f);          // Верх право квадрата (Зад)
        glVertex3f(-1.0f, -1.0f, -1.0f);          // Верх лево
        glVertex3f(-1.0f, 1.0f, -1.0f);          // Низ лево
        glVertex3f(1.0f, 1.0f, -1.0f);          // Низ право
        glColor3f(0.0f, 0.0f, 1.0f);              // Синий
        glVertex3f(-1.0f, 1.0f, 1.0f);          // Верх право квадрата (Лево)
        glVertex3f(-1.0f, 1.0f, -1.0f);          // Верх лево
        glVertex3f(-1.0f, -1.0f, -1.0f);          // Низ лево
        glVertex3f(-1.0f, -1.0f, 1.0f);          // Низ право
        glColor3f(1.0f, 0.0f, 1.0f);              // Фиолетовый
        glVertex3f(1.0f, 1.0f, -1.0f);          // Верх право квадрата (Право)
        glVertex3f(1.0f, 1.0f, 1.0f);          // Верх лево
        glVertex3f(1.0f, -1.0f, 1.0f);          // Низ лево
        glVertex3f(1.0f, -1.0f, -1.0f);          // Низ право
        glEnd();                                // Закончили квадраты

        rtri += 0.2f;             // Увеличим переменную вращения для треугольника
        rquad -= 0.15f;           // Уменьшим переменную вращения для квадрата

    }

    public static void main(String[] args) {
        new Create3D().run("Create 3D Figure");
    }
}
