
public class Food {

   private Snake snake = new Snake();
   private int foodXgreen; // Guarda o X da comida verde
   private int foodYgreen; // Guarda o Y da comida verde
   private int foodXblue; // Guarda o X da comida azul
   private int foodYblue; // Guarda o Y da comida azul
   private int foodXyellow; // Guarda o X da comida amarela
   private int foodYyellow; // Guarda o Y da comida amarela
   private int location;

   // Usado para determinar uma posição randômica da comida.
   private final int RANDOMPOSITION = 39;

   public void createFood() {

      // Faz o X e Y da comida serem aleatórios.
      location = (int) (Math.random() * RANDOMPOSITION);
      foodXgreen = ((location * Board.getDotSize()));
      location = (int) (Math.random() * RANDOMPOSITION);
      foodYgreen = ((location * Board.getDotSize()));

      location = (int) (Math.random() * RANDOMPOSITION);
      foodXblue = ((location * Board.getDotSize()));
      location = (int) (Math.random() * RANDOMPOSITION);
      foodYblue = ((location * Board.getDotSize()));

      location = (int) (Math.random() * RANDOMPOSITION);
      foodXyellow = ((location * Board.getDotSize()));
      location = (int) (Math.random() * RANDOMPOSITION);
      foodYyellow = ((location * Board.getDotSize()));

      if ((foodXgreen == snake.getSnakeX(0)) && (foodYgreen == snake.getSnakeY(0))) {

         createFood();

      }

   }

   public int getFoodXgreen() {

      return foodXgreen;

   }

   public int getFoodYgreen() {

      return foodYgreen;

   }

   public int getFoodXyellow() {

      return foodXyellow;

   }

   public int getFoodYyellow() {

      return foodYyellow;

   }

   public int getFoodXblue() {

      return foodXblue;

   }

   public int getFoodYblue() {

      return foodYblue;

   }
}
