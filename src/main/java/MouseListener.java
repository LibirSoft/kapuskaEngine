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

    public static MouseListener get() {
        if (MouseListener.instance == null) {
            MouseListener.instance = new MouseListener();
        }
        return instance;
    }

    public static void mousePosCallback(long window, double posx, double posy) {
        get().lastX = get().posX;
        get().lastY = get().posY;
        get().posX = posx;
        get().posY = posy;
    }

    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        if (action == GLFW_PRESS) {

            if (button < get().mouseButtonPressed.length) {

                get().mouseButtonPressed[button] = true;
            }
        } else if (action == GLFW_RELEASE) {

            if (button < get().mouseButtonPressed.length) {

                get().mouseButtonPressed[button] = false;
                get().isDragging = false;
            }
        }
    }

    public static void mouseScrollCallback(long window, double offsetX, double offsetY) {
        get().scrollX = offsetX;
        get().scrollY = offsetY;
    }

    public static void endFrame() {
        get().scrollX = 0;
        get().scrollY = 0;

        get().lastX = get().posX;
        get().lastY = get().posY;
    }

}
