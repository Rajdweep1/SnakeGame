/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snakiboi;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author rajdw
 */

public class board extends JPanel implements ActionListener{
    private int dots;
    private Image apple, head, dot;
    private final int all_dots = 900, dot_size = 10;
    
    private Timer timer;
    
    private int x[] = new int[all_dots];
    private int y[] = new int[all_dots];
    
    private int random_pos = 29;
    private int apple_x, apple_y;
    
    private boolean left = false, right = true, up = false, down = false, inGame = true;
    board(){
        addKeyListener(new TAdapter());
        setPreferredSize(new Dimension(800,600));
        setBackground(Color.BLACK);
        setFocusable(true);
        loadImages();
        initGame();
    }
    public void loadImages(){
        ImageIcon p1 = new ImageIcon( ClassLoader.getSystemResource("snakiboi/icons/apple.png"));
        apple = p1.getImage();
        ImageIcon p2 = new ImageIcon( ClassLoader.getSystemResource("snakiboi/icons/head.png"));
        head = p2.getImage();
        ImageIcon p3 = new ImageIcon( ClassLoader.getSystemResource("snakiboi/icons/dot.png"));
        dot = p3.getImage();
    }
    public void initGame(){
        dots = 3;
        for(int i = 0; i < dots; i++){
            y[i] = 50;
            x[i] = 50 - i*dot_size;
        }
        locateApple();
        timer = new Timer(140,this);
        timer.start();
    }
    
    public void locateApple(){
        int r = (int)(Math.random()*random_pos);
        apple_x = r*dot_size;
        r = (int)(Math.random()*random_pos);
        apple_y = r*dot_size;
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    
    public void draw(Graphics g){
        if(inGame){
            g.drawImage(apple,apple_x,apple_y,this);
            for(int i = 0; i < dots; i++){
                if(i == 0)g.drawImage(head, x[i], y[i], this);
                else g.drawImage(dot, x[i], y[i], this);
            }
            Toolkit.getDefaultToolkit().sync();
        }
        else{
            gameOver(g);
        }
    }
    
    public void gameOver(Graphics g){
        String s = "Game Over!";
        //String s = "dher bara";
        Font font = new Font("cursive", Font.BOLD, 20);
        FontMetrics metrices = getFontMetrics(font);
        
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(s, (800 - metrices.stringWidth(s))/2, 600/2);
    }
    
    public void move(){
        for(int i = dots; i > 0; i--){
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        
        if(left) x[0] -= dot_size;
        
        if(right) x[0] += dot_size;
        
        if(up) y[0] -= dot_size;
        
        if(down) y[0] += dot_size;
        
    }
    
    public void checkEaten(){
        if(x[0] == apple_x && y[0] == apple_y){
            dots++;
            locateApple();
        }
    }
    
    public void checkCollition(){
        for(int i = dots; i > 0; i --){
            if(i > 4 && (x[0] == x[i] && y[0] == y[i])){
                inGame = false;
            }
        }
        if(x[0] < 0) inGame = false;
        
        if(y[0] < 0) inGame = false;
        
        if(x[0] > 800) inGame = false;
        
        if(y[0] > 600) inGame = false;
        if(!inGame){
            timer.stop();
            
        }
    }
    
    public void actionPerformed(ActionEvent ae){
        if(inGame){
             move();
        
            checkEaten();
        
            checkCollition();
        
            repaint();
        }
       
    }
    
    public class TAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_A && !right){
                left = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_D && !left){
                right = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_W && !down){
                left = false;
                right = false;
                up = true;
            }
            if(key == KeyEvent.VK_S && !up){
                left = false;
                right = false;
                down = true;
            }
        }
    }
}
