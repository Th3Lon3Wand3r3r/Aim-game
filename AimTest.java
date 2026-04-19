import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class AimTest extends JPanel implements MouseListener {
   boolean gameOver = false;
   int timeLeft = 2;
   int targetX;
   int targetY;
   int score = 0;
   Timer timer;
   Random rand = new Random();

   AimTest() {
      this.addMouseListener(this);
      this.setBackground(Color.BLACK);
      this.targetX = this.rand.nextInt(1000);
      this.targetY = this.rand.nextInt(600);
      this.timer = new Timer(1000, (e) -> {
         --this.timeLeft;
         if (this.timeLeft == 0) {
            this.gameOver = true;
            this.timer.stop();
         }

         this.repaint();
      });
      this.timer.start();
   }

   public void mouseClicked(MouseEvent e) {
      if (!this.gameOver) {
         int mouseX = e.getX();
         int mouseY = e.getY();
         if (mouseX >= this.targetX && mouseX <= this.targetX + 80 && mouseY >= this.targetY && mouseY <= this.targetY + 80) {
            ++this.score;
            this.timeLeft = 2;
            this.targetX = this.rand.nextInt(1000);
            this.targetY = this.rand.nextInt(600);
         } else {
            this.gameOver = true;
            this.timer.stop();
         }

         this.repaint();
      }
   }

   public void mousePressed(MouseEvent e) {
   }

   public void mouseReleased(MouseEvent e) {
   }

   public void mouseEntered(MouseEvent e) {
   }

   public void mouseExited(MouseEvent e) {
   }

   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      if (this.gameOver) {
         g.setColor(Color.WHITE);
         g.setFont(new Font("Arial", 1, 40));
         g.drawString("GAME OVER", 500, 300);
         g.setFont(new Font("Arial", 0, 30));
         g.drawString("Score: " + this.score, 500, 350);
      } else {
         g.setColor(Color.RED);
         g.fillOval(this.targetX, this.targetY, 80, 80);
         g.setColor(Color.WHITE);
         g.fillOval(this.targetX + 10, this.targetY + 10, 60, 60);
         g.setColor(Color.RED);
         g.fillOval(this.targetX + 20, this.targetY + 20, 40, 40);
         g.setColor(Color.WHITE);
         g.fillOval(this.targetX + 30, this.targetY + 30, 20, 20);
         g.setColor(Color.WHITE);
         g.setFont(new Font("Arial", 1, 25));
         g.drawString("Timer: " + this.timeLeft, this.getWidth() - 200, 60);
         g.setFont(new Font("Arial", 1, 25));
         g.drawString("Score:" + this.score, this.getWidth() - 200, 80);
      }
   }

   public static void main(String[] args) {
      JFrame frame = new JFrame("Aim Test");
      AimTest gamePanel = new AimTest();
      frame.add(gamePanel);
      frame.setExtendedState(6);
      frame.setDefaultCloseOperation(3);
      frame.setVisible(true);
   }
}
