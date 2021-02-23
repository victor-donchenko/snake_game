import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.border.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class TitlePanel extends JPanel implements ActionListener {
    private Dimension panelDimensions; 
    private JButton buttonStart, buttonAbout;
    private Font buttonFont, titleFont;
    private LineBorder buttonLineBorder;
    private EmptyBorder buttonPadding;
    private CompoundBorder buttonBorder;
    private JLabel title;

    public TitlePanel(){
        super();
        panelDimensions = new Dimension(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
        this.setPreferredSize(panelDimensions);
        this.setMinimumSize(panelDimensions);
        this.setMaximumSize(panelDimensions);

        buttonStart = new JButton("Play");
        buttonAbout = new JButton("About");
        buttonFont = new Font("comic sans ms", Font.BOLD, 20);

        buttonLineBorder = new LineBorder(new Color(87,65,57), Constants.BORDER_THICKNESS);
        buttonPadding = new EmptyBorder(5,12,5,12);
        buttonBorder = new CompoundBorder(buttonLineBorder,buttonPadding);
        

        titleFont = new Font("comic sans ms", Font.BOLD, 40);
        title = new JLabel("Snake");
        title.setSize(80, 160);
        title.setFont(titleFont);
        this.add(title);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        this.add(buttonStart,gbc);
        this.add(buttonAbout,gbc);
        customizeButtons();
    }

    private void customizeButtons(){
        //Only if kotlin scoping functions existed here. Maybe i should have made this a kotlin file
        buttonStart.setBackground(new Color(63,122,77));
        buttonStart.setForeground(Color.BLACK);
        buttonStart.setFocusPainted(false);
        buttonStart.setFont(buttonFont);
        buttonStart.setBorder(buttonBorder);
        buttonStart.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonStart.addActionListener(this);

        buttonAbout.setBackground(new Color(63,122,77));
        buttonAbout.setForeground(Color.BLACK);
        buttonAbout.setFocusPainted(false);
        buttonAbout.setFont(buttonFont);
        buttonAbout.setBorder(buttonBorder);
        buttonAbout.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonAbout.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == buttonStart){
            Window.CURRENT_PANEL = new GamePanel();
        }
        if(event.getSource() == buttonAbout){
            Window.CURRENT_PANEL = new AboutPanel();
        }
    }

}