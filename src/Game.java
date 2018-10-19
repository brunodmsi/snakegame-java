
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Game extends JFrame {

   Game() {

      add(new Board());
      setResizable(false);
      pack();

      setTitle("Snake Game");
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   }

   public static void main(String[] De_Masi) {

      // Cria um novo GUI
      EventQueue.invokeLater(new Runnable() {
         @Override
         public void run() {

            JFrame frame = new Game();
            frame.setVisible(true);

         }

      });

   }

}
