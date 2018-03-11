import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePlay extends JPanel implements KeyListener, ActionListener{
    //loading the images -> using ImageIcon
    private ImageIcon tileImage;

    //represent snake position by array[][]
    private int[] snakeXposition = new int[750];
    private int[] snakeYposition = new int[750];

    private boolean left = false;
    private boolean up = false;
    private boolean right = false;
    private boolean down = false;

    //Image for snake face
    private ImageIcon rightmouth;
    private ImageIcon upmouth;
    private ImageIcon leftmouth;
    private ImageIcon downmouth;
    private ImageIcon snakebody;

    private Timer timer;
    private int delay = 100;
    private int SnakeLength = 3;

    private int move = 0;
    private int score;

    // Fruit Variable
    private int[] FruitXposition = { 25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350,
            375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850 };
    private int[] FruitYposition ={75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350,
            375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};

    private ImageIcon fruitImage;
    private Random randomVariable = new Random();
    // have 34 element in array
    private int xDice = randomVariable.nextInt(34);//not exceeding 34 as we have 34 elements in array
    private int YDice = randomVariable.nextInt(23);


    //constructor
    public GamePlay(){
        //adding key listener by default
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

     public void paint(Graphics g){
        //initial snake
        if(move == 0){
            snakeXposition[2] = 50;
            snakeXposition[1] = 75;
            snakeXposition[0] = 100;

            snakeYposition[0] = 100;
            snakeYposition[1] = 100;
            snakeYposition[2] = 100;
        }
        //draw tile image border
         g.setColor(Color.white);
         g.drawRect(24,10, 851, 55);

         tileImage = new ImageIcon(GamePlay.class.getClassLoader().getResource("Image/snaketitle.jpg"));
         tileImage.paintIcon(this, g, 25, 11 );

         //draw border
         g.setColor(Color.white);
         g.drawRect(24,74, 851, 577);
         //draw background for gameplay
         g.setColor(Color.black);
         g.fillRect(25,75,850,575);

         //draw score
         g.setColor(Color.cyan);
         g.setFont(new Font("arial", Font.BOLD, 20));
         g.drawString("Score: " + score, 780, 30 );

         //draw snake default position without moving
         rightmouth = new ImageIcon(GamePlay.class.getClassLoader().getResource("Image/rightmouth.png"));
         rightmouth.paintIcon(this, g, snakeXposition[0], snakeYposition[0]);

         for(int a = 0; a < SnakeLength; a ++){
             // snake head define
             if(a == 0 && right){
                 rightmouth = new ImageIcon(GamePlay.class.getClassLoader().getResource("Image/rightmouth.png"));
                 rightmouth.paintIcon(this, g, snakeXposition[a], snakeYposition[a]);
             }
             if(a == 0 && left){
                 leftmouth = new ImageIcon(GamePlay.class.getClassLoader().getResource("Image/leftmouth.png"));
                 leftmouth.paintIcon(this, g, snakeXposition[a], snakeYposition[a]);
             }
             if(a == 0 && up){
                 upmouth = new ImageIcon(GamePlay.class.getClassLoader().getResource("Image/upmouth.png"));
                 upmouth.paintIcon(this, g, snakeXposition[a], snakeYposition[a]);
             }
             if(a == 0 && down){
                 downmouth = new ImageIcon(GamePlay.class.getClassLoader().getResource("Image/downmouth.png"));
                 downmouth.paintIcon(this, g, snakeXposition[0], snakeYposition[0]);
             }
             //snake body
             if(a!= 0 ){
                snakebody =  new ImageIcon(GamePlay.class.getClassLoader().getResource("Image/snakeimage.png"));
                 snakebody.paintIcon(this, g, snakeXposition[a], snakeYposition[a]);
             }


         }
         //fruit image
         fruitImage = new ImageIcon(GamePlay.class.getClassLoader().getResource("Image/enemy.png"));
         fruitImage.paintIcon(this, g, FruitXposition[xDice], FruitYposition[YDice]);
         //snake eating fruit
        if(FruitXposition[xDice] == snakeXposition[0] && FruitYposition[YDice] == snakeYposition[0] ){
            SnakeLength ++;
            score ++;
            xDice = randomVariable.nextInt(34);
            YDice = randomVariable.nextInt(23);

            //after eating change the position of fruit
            fruitImage.paintIcon(this, g, FruitXposition[xDice], FruitYposition[YDice]);
        }


        for(int i = 1; i < SnakeLength; i++){
            if(snakeXposition[0] == snakeXposition[i] && snakeYposition[0] == snakeYposition[i]){
                timer.stop();

                g.setColor(Color.WHITE);
                g.setFont(new Font("arial", Font.BOLD, 50));
                g.drawString("GAME OVER", 300, 300);

                g.setFont(new Font("arial", Font.BOLD, 20));
                g.drawString("Space to restart", 350, 340);



            }
        }
         g.dispose();
         repaint();
     }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
         //when press space to restart game
        if(e.getKeyCode() == KeyEvent.VK_SPACE){

            //reset everything
            up = false;
            down = false;
            left = false;
            right =false;
            SnakeLength = 3;
            move = 0; // move -> initial state handler
            score = 0;
            timer.start();
            repaint();

        }
         //when press right button
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
             move ++;

            if(!left){
                right = true;
             }else {
                right = false;
                left = true;
            }
            up = false;
            down = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_UP){
            //then how can we can up key to perform
            move ++;
            if(!down){
                up = true;
            }else{
                up = false;
                down = true;
            }
            right = false;
            left = false;

        }

        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            move ++;
            if(!up){
                down = true;
            }else{
                down = false;
                up = true;
            }
            left = false;
            right = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            move ++;
            if(!right){
                left = true;
            } else {
                left = false;
                right = true;
            }
            up = false;
            down = false;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    // this method automatically called then the timer is started!!
    public void actionPerformed(ActionEvent e) {
     //start timer again -> create continuation movement
        timer.start();

     // to check which key is pressed
     if(right){
         // movement of snake body
         // Y coordination of snake position
         for(int i = SnakeLength-1; i >= 0 ; i-- ){

             snakeYposition[i+1] = snakeYposition[i];
         }

         for(int i = SnakeLength -1; i >= 0 ; i-- ){
             // not the snake head
             if(i != 0){
                 snakeXposition [i] = snakeXposition[i-1];
             }
             //head
             else {
                 snakeXposition[i] = snakeXposition[i] + 25;

                 if( snakeXposition[i] > 850 ){
                     snakeXposition[i] = 25;
                  }
             }
         }

     }

     if(left){
         for(int i = SnakeLength - 1; i >= 0 ; i-- ){
             snakeYposition[i+1] = snakeYposition[i];
         }
         for(int i = SnakeLength - 1; i >= 0 ; i-- ){
             if(i != 0){
                 snakeXposition [i] = snakeXposition[i-1];
             } else {
                 snakeXposition[i] = snakeXposition[i] -25;
                 if(snakeXposition[i] < 25){
                     snakeXposition[i] = 850;
                 }
             }
         }
     }

     if(up){
       for(int i = SnakeLength - 1; i >= 0; i--)
       {
           snakeXposition[i+1] = snakeXposition[i];
       }
       for(int i = SnakeLength - 1; i >= 0 ; i--){
           if(i != 0){
               snakeYposition [i] = snakeYposition[i-1];
           }
           //head
           else {
               snakeYposition[i] = snakeYposition[i] - 25;
               if(snakeYposition[i] < 75){
                  snakeYposition[i] = 625;
               }
           }
       }
     }

    if(down){
        for(int i = SnakeLength - 1; i >= 0; i--)
        {
            snakeXposition[i+1] = snakeXposition[i];
        }
        for(int i = SnakeLength - 1; i >= 0 ; i-- ){
            if(i != 0){
                snakeYposition [i] = snakeYposition[i-1];
            }else {
                snakeYposition[i] = snakeYposition[i] + 25;
                if(snakeYposition[i] > 625){
                  snakeYposition[i] =75;
                }
            }
        }
    }
        repaint();
    }

}
