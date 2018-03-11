import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame obj = new JFrame();

      //setup JFrame
       obj.setBounds(10, 10, 905, 700);


       obj.setResizable(false);
        obj.setBackground(Color.black);

       obj.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

       //add panel inside JFrame
        GamePlay gamePlayobj = new GamePlay();
        obj.add(gamePlayobj);
        obj.setVisible(true);

    }
}
