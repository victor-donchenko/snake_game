import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
public final class Painter {
    //Purely for abstraction
    public static void paintSegments(Graphics graphics, int numSegments, int[] x, int[] y){
        for(int i = 0;i<numSegments;i++){
            if(i==0){
                graphics.setColor(Color.GRAY);
                graphics.fillRect(x[i], y[i], Constants.SEGMENT_SIZE, Constants.SEGMENT_SIZE);
            } else {
                graphics.setColor(Color.CYAN);
                graphics.fillRect(x[i], y[i], Constants.SEGMENT_SIZE, Constants.SEGMENT_SIZE);
            }
        }
    }
    public static void paintFood(Graphics graphics, int foodX, int foodY){
        graphics.setColor(Color.RED);
        graphics.fillOval(foodX, foodY, Constants.SEGMENT_SIZE, Constants.SEGMENT_SIZE);
    }
    public static void paintScore(Graphics graphics, int score){
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Bad Script",Font.BOLD, Constants.SCORE_FONT_SIZE));
        graphics.drawString("Score: " + score,700,40);
    }
    public static void paintGameOver(Graphics graphics){
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Bad Script",Font.BOLD, Constants.GAME_OVER_FONT_SIZE));
        graphics.drawString("You died",400,300);
    }
}
