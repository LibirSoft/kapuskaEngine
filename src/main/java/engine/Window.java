package engine;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    int widt, height;
    String title;
    public long glfwWindow;

    public static Window window = null;


    private Window() {

        this.widt = 1920;
        this.height = 1080;
        this.title = "KapuskaEngine";


    }

    public static Window get() {

        if (Window.window == null) {
            Window.window = new Window();
        }

        return Window.window;
    }

    public void run() {
        System.out.println("KapuskaEngine Running");

        init();
        loop();


        freeMemoryGlFW();

        terminateGLFW();

    }

    private void terminateGLFW() {
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void freeMemoryGlFW() {
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);
    }

    private void loop() {

        while (!glfwWindowShouldClose(glfwWindow)) {
            // Event pool
            glfwPollEvents();

            glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(glfwWindow);
        }

    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initalize GLFW");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        glfwWindow = glfwCreateWindow(this.widt, this.height, this.title, NULL, NULL);

        if (glfwWindow == NULL) {
            throw new IllegalStateException("Field to create the GLFW Window");
        }


        ınputsInit();


        glfwMakeContextCurrent(glfwWindow);

        glfwSwapInterval(1);

        glfwShowWindow(glfwWindow);

        GL.createCapabilities();

    }

    private void ınputsInit() {
        glfwSetCursorPosCallback(glfwWindow,MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow,MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow,MouseListener::mouseScrollCallback);
        glfwSetKeyCallback(glfwWindow, KeyListener::keyCallback);
    }

}
