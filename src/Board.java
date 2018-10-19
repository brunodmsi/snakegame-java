
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener {

   int score = 0;

   private final static int DEFAULT_SPEED = 53;
   
   // Guarda altura e largura da janela
   private final static int BOARDWIDTH = 800;
   private final static int BOARDHEIGHT = 600;

   // Usado para representar o tamanho do pixel da comida & articulação da cobra
   private final static int PIXELSIZE = 14;

   // O número total de pixel que o jogo pode ter.
   // Não queremos a mais porque o jogo acabaria de um jeito prematuro.
   // E não teria como o player ganhar.
   private final static int TOTALPIXELS = (BOARDWIDTH * BOARDHEIGHT) / (PIXELSIZE * PIXELSIZE);

   // Checa se o jogo está rodando
   private boolean inGame = true;

   // Timer usado para tick times
   private Timer timer;

   // Seta a velocidade da cobra, quanto menor o #, mais rapido a cobra anda
   // que acaba fazendo
   // o jogo mais dificil.
   public int speed = DEFAULT_SPEED;

   // Instâncias da cobra e da comida
   private Snake snake = new Snake();
   private Food food = new Food();

   public Board() {

      addKeyListener(new Keys());
      setBackground(Color.BLACK);
      setFocusable(true);

      setPreferredSize(new Dimension(BOARDWIDTH, BOARDHEIGHT));

      initializeGame();

   }

   // Desenha os componentes
   @Override
   protected void paintComponent(Graphics g) {

      super.paintComponent(g);
      draw(g);

   }

   // Desenha a cobra e a comida (Chamado no repaint()).
   void draw(Graphics g) {
      // Só desenha se o jogo estiver rodando || cobra viva
      if (inGame == true) {

         g.setColor(Color.green);
         g.fillRect(food.getFoodXgreen(), food.getFoodYgreen(), PIXELSIZE, PIXELSIZE); // food

         g.setColor(Color.yellow);
         g.fillRect(food.getFoodXyellow(), food.getFoodYyellow(), PIXELSIZE, PIXELSIZE); // food

         g.setColor(Color.blue);
         g.fillRect(food.getFoodXblue(), food.getFoodYblue(), PIXELSIZE, PIXELSIZE); // food

         g.setColor(Color.white);
         g.drawString("Score: " + score, 400, 10);
         g.drawString("by: Bruno De Masi", 700, 10);

         // Desenha a cobra.
         for (int i = 0; i < snake.getJoints(); i++) {
            // Cabeça da cobra
            if (i == 0) {
               g.setColor(Color.RED);
               g.fillRect(snake.getSnakeX(i), snake.getSnakeY(i), PIXELSIZE, PIXELSIZE);
               // Corpo da cobra
            } else {

               g.fillRect(snake.getSnakeX(i), snake.getSnakeY(i), PIXELSIZE, PIXELSIZE);

            }

         }

         // Sincroniza os gráficos
         Toolkit.getDefaultToolkit().sync();

      } else {

         // Se perdemos, o jogo acaba
         endGame(g);
         score = 0;

      }

   }

   void initializeGame() {

      snake.setJoints(3); // Seta o tamanho inicial da cobra

      // Cria o corpo da cobra
      for (int i = 0; i < snake.getJoints(); i++) {

         snake.setSnakeX(BOARDWIDTH / 2);
         snake.setSnakeY(BOARDHEIGHT / 2);

      }
      // Começa com a cobra se mexendo para a direita
      snake.setMovingRight(true);

      // Gera a primeira comida
      food.createFood();
      food.createFood();
      food.createFood();

      // seta o timer para gravar a velocidade / faz o jogo rolar
      timer = new Timer(speed, this);
      timer.start();

   }

   // se a cobra estiver perto da comida..
   void checkFoodCollisions() {

      if ((proximity(snake.getSnakeX(0), food.getFoodXgreen(), 13)) && (proximity(snake.getSnakeY(0), food.getFoodYgreen(), 13))) {

         score += 10;
         speed -= 0.5;
         timer.stop();
         timer.setDelay(speed);
         timer.start();

         System.out.println("intersection");
         // Adiciona uma junta a cobra
         snake.setJoints(snake.getJoints() + 1);
         // Cria nova comida
         food.createFood();
         System.out.println(score);

      }

      if ((proximity(snake.getSnakeX(0), food.getFoodXyellow(), 13)) && (proximity(snake.getSnakeY(0), food.getFoodYyellow(), 13))) {

         score += 20;
         speed -= 1.5;
         timer.stop();
         timer.setDelay(speed);
         timer.start();

         System.out.println("intersection");
         // Adiciona uma junta a cobra
         snake.setJoints(snake.getJoints() + 1);
         // Cria nova comida
         food.createFood();
         System.out.println(score);

      }

      if ((proximity(snake.getSnakeX(0), food.getFoodXblue(), 13)) && (proximity(snake.getSnakeY(0), food.getFoodYblue(), 13))) {

         score += 30;
         speed -= 2.5;
         timer.stop();
         timer.setDelay(speed);
         timer.start();

         System.out.println("intersection");
         // Adiciona uma junta a cobra
         snake.setJoints(snake.getJoints() + 1);
         // Cria nova comida
         food.createFood();
         System.out.println(score);

      }

   }

   // Checa as colisões com a comida ou com a parede
   void checkCollisions() {

      // Se a cobra atinge ela mesma..
      for (int i = snake.getJoints(); i > 0; i--) {

         // Cobra não faz interseção se ela for maior que 5
         if ((i > 5) && (snake.getSnakeX(0) == snake.getSnakeX(i) && (snake.getSnakeY(0) == snake.getSnakeY(i)))) {
            inGame = false; // jogo acaba
         }
      }

      // Se a cobra cruzar com as bordas..
      if (snake.getSnakeY(0) >= BOARDHEIGHT) {
         inGame = false;
      }

      if (snake.getSnakeY(0) < 0) {
         inGame = false;
      }

      if (snake.getSnakeX(0) >= BOARDWIDTH) {
         inGame = false;
      }

      if (snake.getSnakeX(0) < 0) {
         inGame = false;
      }

      // Se o jogo acabou, o timer pode acabar
      if (!inGame) {
         timer.stop();
      }

   }

   void endGame(Graphics g) {

      // Cria uma mensagem dizendo que o jogo acabou
      String message = "Game over";

      // Cria uma instancia de fonte
      Font font = new Font("Times New Roman", Font.BOLD, 14);
      FontMetrics metrics = getFontMetrics(font);

      // Muda cor do texto para vermelho, e define a fonte
      g.setColor(Color.red);
      g.setFont(font);

      // Desenha a mensagem de Game Over
      g.drawString(message, (BOARDWIDTH - metrics.stringWidth(message)) / 2, BOARDHEIGHT / 2);
      g.drawString("ENTER para reiniciar", (BOARDWIDTH - metrics.stringWidth(message)) / 2, BOARDHEIGHT / 2 - 40);

      System.out.println("Game Ended");

   }

   // Corre constantemente enquanto estiver no jogo.
   @Override
   public void actionPerformed(ActionEvent e) {

      if (inGame == true) {

         checkFoodCollisions();
         checkCollisions();
         snake.move();

         System.out.println(snake.getSnakeX(0) + " " + snake.getSnakeY(0)
                 + " " + food.getFoodXgreen() + ", " + food.getFoodYgreen());
      }

      repaint();
   }

   private class Keys extends KeyAdapter {

      @Override
      public void keyPressed(KeyEvent e) {

         int key = e.getKeyCode();

         if ((key == KeyEvent.VK_LEFT) && (!snake.isMovingRight())) {
            snake.setMovingLeft(true);
            snake.setMovingUp(false);
            snake.setMovingDown(false);
         }

         if ((key == KeyEvent.VK_RIGHT) && (!snake.isMovingLeft())) {
            snake.setMovingRight(true);
            snake.setMovingUp(false);
            snake.setMovingDown(false);
         }

         if ((key == KeyEvent.VK_UP) && (!snake.isMovingDown())) {
            snake.setMovingUp(true);
            snake.setMovingRight(false);
            snake.setMovingLeft(false);
         }

         if ((key == KeyEvent.VK_DOWN) && (!snake.isMovingUp())) {
            snake.setMovingDown(true);
            snake.setMovingRight(false);
            snake.setMovingLeft(false);
         }

         if ((key == KeyEvent.VK_ENTER) && (inGame == false)) {

            inGame = true;
            snake.setMovingDown(false);
            snake.setMovingRight(false);
            snake.setMovingLeft(false);
            snake.setMovingUp(false);
            speed = DEFAULT_SPEED;

            initializeGame();
         }

      }

   }

   private boolean proximity(int a, int b, int closeness) {

      return Math.abs((long) a - b) <= closeness;

   }

   public static int getAllDots() {

      return TOTALPIXELS;

   }

   public static int getDotSize() {

      return PIXELSIZE;

   }
}
