import java.awt.GraphicsEnvironment;
public class App {
    private String[] fontList = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    public static void main(String[] args) {
        Window window = new Window(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT,"Title");
        window.create();
        System.out.println("Successfully launched.");
    }
}
