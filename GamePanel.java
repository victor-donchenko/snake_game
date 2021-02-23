import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements ActionListener {
    private Dimension panelDimensions;
    private Timer timer;
    private boolean gameIsRunning = false;
    private int allCoordinates = (Constants.SCREEN_WIDTH*Constants.SCREEN_HEIGHT)/Constants.SEGMENT_SIZE;
    private int playerX[] = new int[allCoordinates], playerY[] = new int[allCoordinates];
    private int numSegments = 5;
    private int score = 0, foodX, foodY;
    private String playerDirection = "EAST";

    //Asset stuff 
    private BufferedImage background = ImgLoader.resizeBackground(
        ImgLoader.loadImg("images/vector-brown-soil-texture-background.jpg"),
        Constants.SCREEN_WIDTH,
        Constants.SCREEN_HEIGHT
    );

    public GamePanel(){
        super();
        panelDimensions = new Dimension(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
        this.setPreferredSize(panelDimensions);
        this.setMinimumSize(panelDimensions);
        this.setMaximumSize(panelDimensions);
        this.setFocusable(true);
        this.addKeyListener(new mKeyAdapter());
        start();
    }
    private void start(){
        timer = new Timer(Constants.UPDATE_INTERVAL, this);
        timer.start();
        gameIsRunning = true;

        resetFood();
    }
    private void endGame(Graphics graphics){
        Painter.paintGameOver(graphics);
        // if(!gameIsRunning){
        //     timer.stop();
        // }
    }
    private void move(){
        //Shift segments
        for(int i = 0;i<numSegments;i++){
            playerX[i] = playerX[i-1];
            playerY[i] = playerY[i-1];
        }
        switch(playerDirection){
            case "NORTH":
                playerY[0] = playerY[0] - Constants.SEGMENT_SIZE;
                break;
            case "SOUTH":
                playerY[0] = playerY[0] + Constants.SEGMENT_SIZE;
                break;
            case "WEST":
                playerX[0] = playerX[0] - Constants.SEGMENT_SIZE;
                break;
            case "EAST":
                playerX[0] = playerX[0] + Constants.SEGMENT_SIZE;
                break;
        }
    }
    private void resetFood(){
        //Multiplied by segment size to convert from tile coords to actual screen coords
        foodX = (int)( Math.random()*(Constants.SCREEN_WIDTH/Constants.SEGMENT_SIZE)+1 )*Constants.SEGMENT_SIZE;
        foodY = (int)( Math.random()*(Constants.SCREEN_HEIGHT/Constants.SEGMENT_SIZE)+1 )*Constants.SEGMENT_SIZE;

    }
    private void detectCollisionFood(){
        if((playerX[0] == foodX) && (playerY[0]==foodY)){
            numSegments++;
            score++;
            resetFood();
        }
    }
    private void detectCollisionWallAndSelf(){
        //Collision with left wall
        if(playerX[0]<0){
            gameIsRunning = false;
        }
        //Collision with right wall
        if(playerX[0] > Constants.SCREEN_WIDTH){
            gameIsRunning = false;
        }
        //Collision with top and bottom walls
        if(playerY[0] <0){
            gameIsRunning = false;
        }
        if(playerY[0] > Constants.SCREEN_HEIGHT){
            gameIsRunning = false;
        }
        //Collision with self
        for(int i = numSegments;i>0;i--){
            if((playerX[0] == playerX[i]) && (playerY[0] == playerY[i])){
                gameIsRunning = false;
            }
        }
        if(!gameIsRunning){timer.stop();}
    }
    private void render(Graphics graphics){
        if(gameIsRunning){
            //Clear screen
            //graphics.clearRect(0, 0, 800, 600);

            //Draw background. Yes I am lazy, I'll scale it later.
            // if(!graphics.drawImage(background,0,0,null)){
            //     graphics.drawImage(background,0,0,null);
            // }
            
            //Food
            Painter.paintFood(graphics, foodX, foodY);
            //segments
            Painter.paintSegments(graphics, numSegments, playerX, playerY);
            //score
            Painter.paintScore(graphics, score);
        } else {
            endGame(graphics);
        }
    }
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        render(graphics);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(gameIsRunning){
            move();
            detectCollisionFood();
            detectCollisionWallAndSelf();
        }
        repaint();
    }
    public class mKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent event) {
            //super.keyPressed(event);

            switch(event.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(playerDirection != "EAST" ){
                        playerDirection = "WEST";
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(playerDirection != "WEST"){
                        playerDirection = "EAST";
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(playerDirection != "SOUTH"){
                        playerDirection = "NORTH";
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(playerDirection != "NORTH"){
                        playerDirection = "SOUTH";
                    }
                    break;
            }
        }
    }
}
