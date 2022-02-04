package BrickBreakout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.util.Timer;
import javax.swing.Timer;
public  class GamePlay extends JPanel implements KeyListener, ActionListener {

    private boolean play =false;
    private int score =0;

    private int totalBricks=21;

    private Timer timer;
    private int delay =8;

    private int playerX=350;

    private int ballposX=120;
    private int ballposY=350;
    private int ballXdir=-1;
    private int ballYdir=-2;
    private MapGenerator map;


    public GamePlay()
    {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        timer = new Timer(delay,this);
        timer.start();

        map=new MapGenerator(3,7);   //rows and columns
    }


    public void paint(Graphics g)
    {
        //background
        g.setColor(Color.BLACK);
        g.fillRect(1,1,692,592);

        //borders
        g.setColor(Color.YELLOW);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,592);

        // the paddle
        g.setColor(Color.green);
        g.fillRect(playerX ,550,100,8);

        // bricks

        map.draw((Graphics2D) g);


        //the ball
        g.setColor(Color.red);
        g.fillOval(ballposX,ballposY,20,20);

    }

    //declaration


    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode()==KeyEvent.VK_LEFT)   //left button
        {

            if(playerX<=0)   //prevent the paddle
                playerX =0;
            else

                moveLeft();   //declare this function
        }

        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
        {
            if (playerX>=600)    // to prevent the paddle for going out of the boundary
                playerX=600;
            else
                moveRight();
        }

        repaint();    // change the paddle position on clicking right and left

    }


    public void actionPerformed(ActionEvent e)
    {
// ball moving

        if(play){

            if(ballposX<=0)   //for left boundary
            {
                ballXdir=-ballXdir;
            }

            if(ballposX>=670)    //for right boundary
            {
                ballXdir=-ballXdir;
            }


            if(ballposY<=0)   //for top boundary
            {
                ballYdir=-ballYdir;
            }

            Rectangle ballRect = new Rectangle( ballposX,ballposY,20,20);
            Rectangle paddleRect =new Rectangle(playerX,550,100,8);

            if(ballRect.intersects(paddleRect))
            {

                ballYdir=-ballYdir;
            }

            // brick check

            A:for(int i=0;i<map.map.length;i++)
            {
                for(int j=0;j<map.map[0].length;j++)
                {
                    if(map.map[i][j]>0)
                    {
                        int width=map.brickWidth;
                        int height =map.brickHeight;
                        int brickXpos=80+j*width;
                        int brickYpos =50+i*height;


                        Rectangle brickRect=new Rectangle(brickXpos,brickYpos,width,height);

                        if(ballRect.intersects(brickRect))
                        {
                            map.setBrick(0,i,j);
                            totalBricks--;

                            if(ballposX+19<=brickXpos || ballposX+1>=brickXpos+width)
                            {
                                ballXdir=-ballXdir;

                            }
                            else
                            {
                                ballYdir=-ballYdir;
                            }

                            break A;

                        }
                    }
                }
            }




            ballposX+=ballXdir;
            ballposY+=ballYdir;

        }

        repaint();

    }

    public void keyTyped(KeyEvent e)
    {

    }


    public void keyReleased(KeyEvent e)
    {

    }


    public void moveRight ()
    {
        play = true;
        playerX += 20;
    }
    public void moveLeft ()
    {
        play = true;
        playerX -= 20;
    }

}


