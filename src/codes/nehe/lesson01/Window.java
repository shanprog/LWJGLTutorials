package codes.nehe.lesson01;

import codes.nehe.util.ProgrammWindow;

public class Window implements ProgrammWindow {

    @Override
    public void drawGLScene() {

        System.out.println("Test");
    }

    public static void main(String[] args) {
        new Window().run("Test");
    }
}
