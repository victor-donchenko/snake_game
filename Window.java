import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Window {
    private int width,height;
    private JFrame frame;
    private String title;
    private GamePanel gamePanel = new GamePanel();
    private TitlePanel titlePanel = new TitlePanel();
    public static JPanel CURRENT_PANEL;
    

    public Window(int width, int height, String title){
        this.width = width;
        this.height = height;
        this.title = title;
    }
    public void create(){
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(width,height);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(gamePanel); //planning to possibly add current panel soon
        frame.pack();
    }
    public void switchCurrentPanel(){
        return;
    }
}