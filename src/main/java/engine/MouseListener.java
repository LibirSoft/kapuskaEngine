package engine;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener {
    private static MouseListener instance;
    private double scrollX, scrollY;
    private double posX, posY, lastX, lastY;
    private boolean mouseButtonPressed[] = new boolean[3];
    private boolean isDragging;

    private MouseListener() {
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.posX = 0.0;
        this.posY = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }

    public static MouseListener getInstance() {
        if (MouseListener.instance == null) {
            MouseListener.instance = new MouseListener();
        }
        return instance;
    }

    public static void mousePosCallback(long window, double posx, double posy) {
        getInstance().lastX = getInstance().posX;
        getInstance().lastY = getInstance().posY;
        getInstance().posX = posx;
        getInstance().posY = posy;

        // if any of these pressed it means dragging
        getInstance().isDragging = getInstance().mouseButtonPressed[0] || getInstance().mouseButtonPressed[1] || getInstance().mouseButtonPressed[2];
    }

    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        if (action == GLFW_PRESS) {

            if (button < getInstance().mouseButtonPressed.length) {

                getInstance().mouseButtonPressed[button] = true;
            }
        } else if (action == GLFW_RELEASE) {

            if (button < getInstance().mouseButtonPressed.length) {

                getInstance().mouseButtonPressed[button] = false;
                getInstance().isDragging = false;
            }
        }
    }

    public static void mouseScrollCallback(long window, double offsetX, double offsetY) {
        getInstance().scrollX = offsetX;
        getInstance().scrollY = offsetY;
    }

    public static void endFrame() {
        getInstance().scrollX = 0;
        getInstance().scrollY = 0;

        getInstance().lastX = getInstance().posX;
        getInstance().lastY = getInstance().posY;
    }

    public static float getX() {
        return (float) getInstance().posX;
    }

    public static float getY() {
        return (float) getInstance().posY;
    }

    public static float getDx() {
        return (float) (getInstance().lastX - getInstance().posX);
    }

    public static float getDy() {
        return (float) (getInstance().lastY - getInstance().posY);
    }

    public static float getScrollX() {
        return (float) getInstance().scrollX;
    }

    public boolean isDragging() {
        return getInstance().isDragging;
    }

    public static float getScrollY() {
        return (float) getInstance().scrollY;
    }

    public boolean getMouseButtonDown(int button) {
        if (button < getInstance().mouseButtonPressed.length) {

            return getInstance().mouseButtonPressed[button];
        } else {
            return false;
        }
    }
}
