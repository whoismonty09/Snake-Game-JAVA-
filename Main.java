import java.util.*;

class SnakeGame {
    int width = 20, height = 10;
    LinkedList<int[]> snake = new LinkedList<>();
    int foodX, foodY;
    char direction = 'R';
    boolean gameOver = false;
    int score = 0;
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    SnakeGame() {
        snake.add(new int[]{5, 5});
        generateFood();
    }

    void generateFood() {
        foodX = rand.nextInt(height);
        foodY = rand.nextInt(width);
    }

    void draw() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                boolean printed = false;

                for (int[] s : snake) {
                    if (s[0] == i && s[1] == j) {
                        System.out.print("S");
                        printed = true;
                        break;
                    }
                }

                if (!printed) {
                    if (i == foodX && j == foodY) System.out.print("F");
                    else System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println("Score: " + score);
    }

    void move() {
        int[] head = snake.getFirst();
        int x = head[0];
        int y = head[1];

        if (direction == 'W') x--;
        if (direction == 'S') x++;
        if (direction == 'A') y--;
        if (direction == 'D') y++;

        if (x < 0 || x >= height || y < 0 || y >= width) {
            gameOver = true;
            return;
        }

        for (int[] s : snake) {
            if (s[0] == x && s[1] == y) {
                gameOver = true;
                return;
            }
        }

        snake.addFirst(new int[]{x, y});

        if (x == foodX && y == foodY) {
            score++;
            generateFood();
        } else {
            snake.removeLast();
        }
    }

    void play() throws Exception {
        while (!gameOver) {
            draw();
            System.out.print("Move (W/A/S/D): ");
            if (sc.hasNext()) direction = sc.next().toUpperCase().charAt(0);
            move();
            Thread.sleep(300);
            System.out.println();
        }
        System.out.println("Game Over! Final Score: " + score);
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Snake Game developed by Monty");
        SnakeGame game = new SnakeGame();
        game.play();
    }
}
