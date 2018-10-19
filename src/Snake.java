
public class Snake {

   // Guarda as juntas / localização do corpo
   private final int[] x = new int[Board.getAllDots()];
   private final int[] y = new int[Board.getAllDots()];

   // Guarda a direção da cobra
   private boolean movingLeft = false;
   private boolean movingRight = false;
   private boolean movingUp = false;
   private boolean movingDown = false;

   private int joints = 0; // Guarda # de pontos / juntas

   public int getSnakeX(int index) {

      return x[index];

   }

   public int getSnakeY(int index) {

      return y[index];

   }

   public void setSnakeX(int i) {

      x[0] = i;

   }

   public void setSnakeY(int i) {

      y[0] = i;

   }

   public boolean isMovingLeft() {

      return movingLeft;

   }

   public void setMovingLeft(boolean movingLeft) {

      this.movingLeft = movingLeft;

   }

   public boolean isMovingRight() {

      return movingRight;

   }

   public void setMovingRight(boolean movingRight) {

      this.movingRight = movingRight;

   }

   public boolean isMovingUp() {

      return movingUp;

   }

   public void setMovingUp(boolean movingUp) {

      this.movingUp = movingUp;

   }

   public boolean isMovingDown() {

      return movingDown;

   }

   public void setMovingDown(boolean movingDown) {

      this.movingDown = movingDown;

   }

   public int getJoints() {

      return joints;

   }

   public void setJoints(int j) {

      joints = j;

   }

   public void move() {

      for (int i = joints; i > 0; i--) {

         // Move as articulações da serpente
         // a articulação vai de uma vez
         x[i] = x[(i - 1)];
         y[i] = y[(i - 1)];

      }

      // Move a cobra para a esquerda
      if (movingLeft) {

         x[0] -= Board.getDotSize();

      }
      // para a direita
      if (movingRight) {

         x[0] += Board.getDotSize();

      }
      // para baixo
      if (movingDown) {

         y[0] += Board.getDotSize();

      }
      // e para cima
      if (movingUp) {

         y[0] -= Board.getDotSize();

      }

      // o ponto representa o tamanho da articulação, então um pixel pixel de DOTSIZE
      // é adicionadona cobra naquela direção.
   }

}
