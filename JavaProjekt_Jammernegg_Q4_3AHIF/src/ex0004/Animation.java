package ex0004;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Animation extends JFrame implements KeyListener {

    // <editor-fold desc="Variablen initialisieren/deklarieren">
    private int anzahlLeben = 3;//Anzahl der Leben
    int level = 1;//Aktuelles Level
    private int score = 0;//aktuellerScore
    public static boolean pause = false;// Pause oder nicht Pause

    private int width = 50, height = 50;//Höhe und Breite von den Bricks
    private int x; //Abstand zwischen Bricks
    private int y = 10;// Koordinate von der Höhe der Bricks

    private final ArrayList<Brick> array = new ArrayList(); //Bricks erstellen
    private Brick brick;//Referenz von Klasse Brick

    // ball
    private int ballRadius = 20;//Größe vom Ball
    private int ballX; // Startkoordinate X vom Ball
    private int ballY = 100; //Startkoordinate Y vom Ball

    int ballDeltaX = 2; // Richtung und Ballgeschwindigkeit 
    int ballDeltaY = 2; // Richtung und Ballgeschwindigkeit 

    // paddle
    private int paddleX = 200; //Koordinate X vom Paddle
    private int paddleY = 550; //Koordinate Y vom Paddle
    private int paddleHeight = 10; //Höhe vom Paddle
    private int paddleWidth;//Breite vom Paddle

    private int paddleDelta; //Geschwindigkeit Paddle

    private boolean gameOver = false;

    private Canvas canvas; //Referenz zu Canvas
    
    private int latestScore = 0; //letzten Score abrufen
    
    private String username = ""; //letzte username auslesen
    
    private static final String COMMA_DELIMITER = ";";
    private static final String NEXT_LINE = "\n";
    
//</editor-fold>

    // <editor-fold desc="Levels erstellen">
    private ArrayList<Brick> getListe(int levell) {

        if (levell == 1) {
            x = 100;
            for (int i = 1; i <= 10; i++) {

                switch (i) {
                    case 1:
                        y = canvas.getHeight() * 1 / 10;
                        break;

                    case 2:
                        y = canvas.getHeight() * 1 / 10;
                        break;

                    case 3:
                        y = canvas.getHeight() * 1 / 10;
                        break;

                    case 4:
                        y = canvas.getHeight() * 1 / 10;

                        break;

                    case 5:
                        y = canvas.getHeight() * 1 / 10;

                        break;

                    case 6:
                        y = canvas.getHeight() * 5 / 10;
                        ;
                        x = 100;
                        break;

                    case 7:
                        y = canvas.getHeight() * 5 / 10;
                        break;

                    case 8:
                        y = canvas.getHeight() * 5 / 10;
                        break;

                    case 9:
                        y = canvas.getHeight() * 5 / 10;
                        break;

                    case 10:
                        y = canvas.getHeight() * 5 / 10;
                        break;

                    default:
                        break;
                }
                brick = new Brick(x, y, width, height);
                x += canvas.getWidth() / 7;

                array.add(brick);
            }
        }
        if (levell == 2) {
            x = 10;
            y = 10;
            for (int i = 1; i <= 15; i++) {
                switch (i) {
                    case 1:
                        x = 350;
                        y = 40;
                        break;

                    case 2:
                        x = 250;
                        y = 80;
                        break;

                    case 3:
                        x = 450;
                        y = 80;
                        break;

                    case 4:
                        y = 150;
                        x = 200;
                        break;

                    case 5:
                        x = 500;
                        y = 150;
                        break;

                    case 6:
                        x = 200;
                        y = 220;
                        break;

                    case 7:
                        x = 500;
                        y = 220;
                        break;

                    case 8:
                        x = 250;
                        y = 290;
                        break;

                    case 9:
                        x = 450;
                        y = 290;
                        break;

                    case 10:
                        x = 350;
                        y = 330;
                        break;

                    case 11:
                        x = 350;
                        y = 180;
                        break;

                    case 12:
                        x = 10;
                        y = 10;
                        break;

                    case 13:
                        x = 680;
                        y = 10;
                        break;

                    case 14:
                        x = 10;
                        y = 400;
                        break;

                    case 15:
                        x = 680;
                        y = 400;
                        break;

                    default:
                        break;

                }

                brick = new Brick(x, y, 45, 45);
                x += canvas.getWidth() / 9;
                array.add(brick);
            }
        }
        if (levell == 3) {
            x = 10;
            y = 10;
            for (int i = 1; i <= 16; i++) {
                switch (i) {
                    case 1:
                        x = 350;
                        y = 10;
                        break;

                    case 2:
                        x = 310;
                        y = 65;
                        break;

                    case 3:
                        x = 270;
                        y = 120;
                        break;

                    case 4:
                        x = 230;
                        y = 175;
                        break;

                    case 5:
                        x = 190;
                        y = 230;
                        break;

                    case 6:
                        x = 230;
                        y = 285;
                        break;

                    case 7:
                        x = 270;
                        y = 340;
                        break;

                    case 8:
                        x = 310;
                        y = 395;
                        break;

                    case 9:
                        x = 350;
                        y = 450;
                        break;

                    case 10:
                        x = 390;
                        y = 395;
                        break;

                    case 11:
                        x = 430;
                        y = 340;
                        break;

                    case 12:
                        x = 470;
                        y = 285;
                        break;

                    case 13:
                        x = 510;
                        y = 230;
                        break;

                    case 14:
                        x = 470;
                        y = 175;
                        break;

                    case 15:
                        x = 430;
                        y = 110;
                        break;

                    case 16:
                        x = 390;
                        y = 65;
                        break;

                }
                brick = new Brick(x, y, 40, 40);
                x += canvas.getWidth() / 9;

                array.add(brick);
            }
        }
        return array;
    }
//</editor-fold>

    // <editor-fold desc="Draw Frame">
    public void go() {

        canvas = new Canvas();

        this.setTitle("Brick-Breaker");
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.addKeyListener(this);
        this.getContentPane().add(canvas);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(1000, 600));

        //PlaySound ps = new PlaySound();
        Runnable r = new PlaySound();
        Thread th = new Thread(r);
        th.start();
        //ps.run();

        getListe(level);
        gameLoop();
    }
    //</editor-fold>

    // <editor-fold desc="Main">
    public static void main(String[] args) {
        Animation gui = new Animation();
        gui.go();

    }
    //</editor-fold>

    // <editor-fold desc="GameLoop">
    public void gameLoop() {
        try {
            latestScore = loadLatestScore();
        } catch (IOException ex) {
            System.out.println("Can't load latestScore");
        }

        //Startkoordinate Ball
        ballX = (int) (Math.random() * canvas.getWidth() * 6 / 10 - 10);

        int levelCount = 0;
        int scoreCount = 0;

        while (!gameOver) {
            while (!pause && !gameOver) {
                if (level == 1) {
                    //Paddlegröße Level 1
                    paddleWidth = 200;
                    //Geschwindigkeit vom Paddle Level 1
                    paddleDelta = 15;
                }
                if (level == 2) {
                    //Paddlegröße Level 2
                    paddleWidth = 150;
                    //Geschwindigkeit vom Paddle Level 2
                    paddleDelta = 13;
                }
                if (level == 3) {
                    //Paddlegröße Level 3
                    paddleWidth = 100;
                    //Geschwindigkeit vom Paddle Level 3
                    paddleDelta = 10;
                    //Radius vom Ball Level 3
                    ballRadius = 15;

                }

                canvas.repaint();

                //Aktuelle x-Koordinate vom Ball
                ballX = ballX + ballDeltaX;
                //Aktuelle y-Koordinate vom Ball
                ballY = ballY + ballDeltaY;

                //Kollision mit Bricks überprüfen
                for (Brick b : array) {
                    Collision c = b.checkCollision(ballX, ballY, ballRadius);

                    if (c == Collision.LEFT) {
                        ballDeltaX *= -1;
                        levelCount++;
                        scoreCount++;
                        score += 10 * scoreCount;
                    }

                    if (c == Collision.RIGHT) {
                        ballDeltaX *= -1;
                        levelCount++;
                        scoreCount++;
                        score += 10 * scoreCount;
                    }

                    if (c == Collision.UPPER) {
                        ballDeltaY *= -1;
                        levelCount++;
                        scoreCount++;
                        score += 10 * scoreCount;
                    }
                    if (c == Collision.BOTTOM) {
                        levelCount++;
                        ballDeltaY *= -1;
                        scoreCount++;
                        score += 10 * scoreCount;
                    }

                    //Spiel gewonnen
                    if (levelCount == 20) {
                        JOptionPane.showMessageDialog(null, "Herzlichen Glückwunsch! Sie haben alle Levels bewältigt. Score: " + score);
                        System.exit(0);
                    }

                }

                //SpielEnde
                if (levelCount == array.size()) {
                    scoreCount = 0;
                    array.clear();
                    level++;
                    getListe(level);
                }

                if ((ballX + 2 * ballRadius) > canvas.getWidth() * 3 / 4 || (ballX < 0)) {
                    ballDeltaX = ballDeltaX * (-1);
                }
                if (ballY < 0) {
                    ballDeltaY = ballDeltaY * (-1);
                }

                int x = ballX + ballRadius;
                int y = ballY + 2 * ballRadius;

                if ((x > paddleX) && ((paddleX + paddleWidth) > x)
                        && (y >= paddleY) && (y < paddleY + ballDeltaY)) {
                    ballDeltaY = ballDeltaY * (-1);
                    scoreCount = 0;
                }

                try {
                    if (level == 1) {
                        Thread.sleep(10);
                    }
                    if (level == 2) {
                        Thread.sleep((long) (8));
                    }
                    if (level == 3) {
                        Thread.sleep(6);
                    }

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                if (y > paddleY + paddleHeight) {
                    anzahlLeben--;

                    if (anzahlLeben > 0) {
                        ballX = 10 + (int) (Math.random() * (canvas.getWidth() - 30) * 6 / 10 - 10);
                        ballY = 200;
                        ballDeltaX *= -1;
                        ballDeltaY *= -1;
                        canvas.repaint();
                    } else {
                        gameOver = true;
                        try {
                            saveData();
                        } catch (IOException ex) {
                            System.out.println("Speichern fehlgeschlagen");
                        }
                        canvas.repaint();
                    }
                }

                if (goL && paddleX >= 20) {
                    paddleX = paddleX - paddleDelta;
                }
                if (goR && paddleX + paddleWidth <= canvas.getWidth() * 3 / 4 - 20) {
                    paddleX = paddleX + paddleDelta;
                }

            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Animation.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    //</editor-fold>

    // <editor-fold desc="Steuerung">
    @Override
    public void keyTyped(KeyEvent e) {
    }

    boolean goL = false, goR = false;

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            goL = true;
//            if (paddleX >= 20) {
//                paddleX = paddleX - paddleDelta;
//            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            goR = true;
//            if (paddleX + paddleWidth <= canvas.getWidth() * 3 / 4 - 20) {
//                paddleX = paddleX + paddleDelta;
//            }

        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            pause = !pause;
            if (pause) {
                JOptionPane.showMessageDialog(null, "Spiel Pausiert! \nZum Fortfahren 'OK' drücken");
                pause = !pause;
            }
        }
        canvas.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            goL = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            goR = false;

        }

    }
//</editor-fold>

    private void saveData() throws IOException {
        FileWriter writer = new FileWriter("Users.csv");
        try {
            Random rnd = new Random();
            int id = rnd.nextInt(10 - 1 + 1) - 1;
            String username = "Benutzer";
            
            writer.append("ID");
            writer.append(COMMA_DELIMITER);
            writer.append("name");
            writer.append(COMMA_DELIMITER);
            writer.append("highscore");
            writer.append(NEXT_LINE);
            
            writer.append(id + "");
            writer.append(COMMA_DELIMITER);
            writer.append(username);
            writer.append(COMMA_DELIMITER);
            writer.append(score+"");
            writer.append(NEXT_LINE);
            
            writer.flush();
            writer.close();


        } catch (Exception e) {
            writer.flush();
            writer.close();
        }

    }

    private int loadLatestScore() throws FileNotFoundException, IOException {
         String[] wert = new String[100];
         try (BufferedReader br = new BufferedReader(new FileReader("Users.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                wert = line.split(";");
            }
        }
        username = wert[1]+wert[0];
        return Integer.parseInt(wert[2]);
    }

    // <editor-fold desc="Zeichnen">
    class Canvas extends JPanel {

        @Override
        protected void paintComponent(Graphics g0) {
            super.paintComponent(g0);

            //Hintergrundbild einfügen
            BufferedImage bild = null;
            try {
                bild = ImageIO.read(new File("bild.png"));
            } catch (IOException ex) {
                //   Logger.getLogger(ex0004.Canvas.class.getName()).log(Level.SEVERE, null, ex);
            }
            Graphics2D g = (Graphics2D) g0;
            setBackground(Color.BLACK);
            g.drawImage(bild, 0, 0, canvas.getWidth() * 8 / 10, canvas.getHeight(), this);

            //Breite Spielfläche
            int w = canvas.getWidth() * 3 / 4 - 10;
            //Spielfläche von Info abtrennen
            g.drawLine(w, 0, w, canvas.getHeight());

            //Info einfärben
            g.setColor(Color.black);
            g.fillRect(canvas.getWidth() * 3 / 4 - 5, 0, canvas.getWidth(), canvas.getHeight());

            //zeichnen des Paddles
            paddleY = this.getHeight() - 3 * paddleHeight;
            g.setColor(Color.cyan);
            g.fillRect(paddleX, paddleY, paddleWidth, paddleHeight);

            //Bricks zeichnen
            for (Brick b : array) {
                b.draw((Graphics2D) g);
            }

            if (gameOver) {
                JOptionPane.showMessageDialog(null, "Game Over! \nIhr Score: " + score);
                System.exit(0);
            } else {
                //Schriftart und Größe setzen
                g.setFont(new Font("newCourier", Font.PLAIN, 20));
                g.setColor(Color.cyan);
                g.drawString("Leben: " + anzahlLeben, canvas.getWidth() * 8 / 10, canvas.getHeight() * 1 / 10);
                g.drawString("Level: " + level, canvas.getWidth() * 8 / 10, canvas.getHeight() * 2 / 10);

                //Dicke von der Abtrennungslinie setzen
                BasicStroke bs = new BasicStroke(5);
                g.setStroke(bs);

                //Abtrennungslinie zeichnen
                g.drawLine(canvas.getWidth() * 15 / 20 - 3, canvas.getHeight() * 3 / 10, canvas.getWidth(), canvas.getHeight() * 3 / 10);
                g.drawLine(canvas.getWidth() * 15 / 20 - 3, canvas.getHeight() * 8 / 10, canvas.getWidth(), canvas.getHeight() * 8 / 10);
                bs = new BasicStroke(1);
                g.setStroke(bs);

                //zeichnen des Balls
                g.setColor(Color.black);
                g.fillOval(ballX, ballY, 2 * ballRadius, 2 * ballRadius);
                g.setColor(Color.cyan);
                g.drawOval(ballX, ballY, 2 * ballRadius, 2 * ballRadius);

                //Score 
                g.drawString("Score: " + score, canvas.getWidth() * 8 / 10 - 20, canvas.getHeight() - 80);
                g.drawString("Latest Score: " + latestScore, canvas.getWidth() * 8 / 10 - 20, canvas.getHeight() - 50);
                g.drawString("Latest User: " + username , canvas.getWidth() * 8 / 10 - 20, canvas.getHeight() - 20);
               
                g.setFont(new Font("newCourier", Font.PLAIN, 10));

                g.drawString("Drücken Sie ESC für Pause!", 10, canvas.getHeight() - 550);

            }

            if (level >= 2) {
                //Infos über das jeweilige Level
                g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                g.setColor(Color.cyan);
                g.drawString("langsameres Paddle", canvas.getWidth() * 15 / 20 + 20, canvas.getHeight() * 5 / 10 - 40);
                g.drawString("Kleineres Paddle", canvas.getWidth() * 15 / 20 + 20, canvas.getHeight() * 6 / 10 - 40);
                g.drawString("Größere Geschwindigkeit", canvas.getWidth() * 15 / 20 + 20, canvas.getHeight() * 7 / 10 - 40);

            }
        }
    }
    //</editor-fold>
}
