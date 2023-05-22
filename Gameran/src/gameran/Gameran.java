/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameran;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Acer NITRO 5
 */
public class Gameran extends JFrame{

    /**
     * @param args the command line arguments
     */
    
    GameScreen game;
    public static ArrayList<User> user;//tao bieen load du lei file 
        
    public Gameran(){// tao khung JFram
        this.setTitle("Rắn Săn Mồi");//JFrame("Rắn Săn Mồi");
        setSize(1300,880);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//dong chuongtrinh
        user = new ArrayList<User>();
        ReadData();
        game = new GameScreen();
        add(game);
        this.addKeyListener(new hand());
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent we) {
                try {
                    //                super.windowClosing(we); //To change body of generated methods, choose Tools | Templates.
                    WriteData();
                } catch (IOException ex) {
                    Logger.getLogger(Gameran.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        
        setVisible(true);
        

    }
//    
//    public static void main(String[] args) {
////        Gameran f = new Gameran();
//    }
    
    private class hand implements KeyListener{

        @Override
        public void keyTyped(KeyEvent ke) {
           
        }

        @Override
        public void keyPressed(KeyEvent e) {
            
            if(e.getKeyCode() == KeyEvent.VK_SPACE){
                GameScreen.isplaying=!GameScreen.isplaying;}
//                GameScreen.islose =! GameScreen.islose;
                if(GameScreen.islose){
                    GameScreen.islose = false; 
                    game.ran.resetGame();
                }
                    
            
            
            if(e.getKeyCode() == KeyEvent.VK_UP){
                game.ran.setVector(ConRan.GO_UP);
            }
            
            if(e.getKeyCode() == KeyEvent.VK_DOWN){
                game.ran.setVector(ConRan.GO_DOWN);
            }
            
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                game.ran.setVector(ConRan.GO_LEFT);
            }
            
            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                game.ran.setVector(ConRan.GO_RIGHT);
            }
            
        }

        @Override
        public void keyReleased(KeyEvent ke) {
        }
    }
    
    public static void WriteData() throws IOException{
        BufferedWriter bw = null;
        try {
            FileWriter fw = new FileWriter("data/data.txt");
            bw = new BufferedWriter(fw);
            
            for(User u :user){
                bw.write(u.getName()+" "+u.getLevel() + " " + u.getDiem() );
                bw.newLine();
            }
        } catch (IOException ex) {
//            Logger.getLogger(Gameran.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                 bw.close();
             } catch (IOException ex) {
//                 Logger.getLogger(RanAnMoiJava.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        
    }
    
    public static void ReadData(){
        try {  
            FileReader fr = new FileReader("data/data.txt");
            BufferedReader br = new BufferedReader(fr);
//            String line = "";
//            while(true){
//                if(line == null){
//                    break;
//                }
//                line = br.readLine();
//                
//            }
            String line = null;
            while((line = br.readLine()) != null ){//lay nguyen line 
                String[] str = line.split(" ");
                user.add(new User(str[0],str[1],str[2]));
            }
            
            br.close();
//            fr.close();
        } catch (IOException ex) {
//            Logger.getLogger(Gameran.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
