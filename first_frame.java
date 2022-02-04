package brickBreaker;

import javax.swing.JFrame;
public class first_frame {
    public static void main(String[] args)
    {
        JFrame obj= new JFrame();
        GamePlay gp=new GamePlay();

     //   obj.setBounds(10,10,700,600);
        obj.setTitle(" BREAKOUT BALL");
        obj.setSize(700,600);
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gp);


    }
}
