/*
* My java game Xonix who was very populare in 1980
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class GameXonix extends JFrame {
    final String TITLE_OF_PROGRAM = "Xomix";
    final int POINT_SIZE = 10;
    final int FIELD_WIDTH = 640 / POINT_SIZE;
    final int FIELD_HEIGHT = 460 / POINT_SIZE;
    final int FIELD_DX = 6;
    final int FIELD_DY = 28 + 28;
    final int START_LOCATION = 200;
    final int LEFT = 37; // key codes
    final int UP = 38;
    final int RIGHT = 39;
    final int DOWN = 40;
    final int SHOW_DELAY = 60; // delay for animation
    final int COLOR_TEMP = 1; // for temporary filling
    final int COLOR_WATER = 0;
    final int COLOR_LAND = 0x00a8a8;
    final int COLOR_TRACK = 0x901290;
    final int PERCENT_OF_WATER_CAPTURE = 75;
    final String FORMAT_STRING = "Score: %d %20s %d %20s %2.0f%%";
    final Font font = new Font("", Font.BOLD, 21);

    Random random = new Random();
    Canvas canvas = new Canvas();
    JLabel board = new JLabel();
    Field field = new Field();
    Xonix xonix = new Xonix();
    Balls balls = new Balls();
    Cube cube = new Cube();
    GameOver gameover = new GameOver();

    public static void main(String[] args) {
        new GameXonix().go();
        System.out.println("Hello World!");
    }

    GameXonix() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, FIELD_WIDTH * POINT_SIZE + FIELD_DX, FIELD_HEIGHT * POINT_SIZE + FIELD_DY);
        setResizable(false);
        board.setFont(font);
        board.setOpaque(true);
        board.setBackground(Color.BLACK);
        board.setForeground(Color.WHITE);
        board.setHorizontalAlignment(JLabel.CENTER);
        add(BorderLayout.CENTER, canvas);
        add(BorderLayout.SOUTH, board);
        addKeyListener(new KeyAdapter() {
                           @Override
                           public void keyPressed(KeyEvent e) {
                               if (e.getKeyCode() >= LEFT && e.getKeyCode() <= DOWN)
                                   xonix.setDirection(e.getKeyCode());
                           }
                       }

        );
        setVisible(true);
    }

    void go() {

    }

    class Field {
        private final int WATER_AREA = (FIELD_WIDTH -4) * (FIELD_HEIGHT - 4);
        private int[][] field = new int[FIELD_WIDTH][FIELD_HEIGHT];
        private float currentWaterArea;
        private int countScore = 0;

        Field() {
            init();
        }

        void init() {
            for (int y = 0; y < FIELD_HEIGHT; y++)
                for (int x = 0; x < FIELD_WIDTH; x++)
                    field[x][y] = (x < 2 || x > FIELD_WIDTH - 3 || y < 2 || y > FIELD_HEIGHT - 3)? COLOR_LAND : COLOR_WATER;
            currentWaterArea = WATER_AREA;
        }

        int getColor(int x, int y) {
            if (x < 0 || y < 0 || x > FIELD_WIDTH - 1 || y > FIELD_HEIGHT - 1)
                return COLOR_WATER;
            return field[x][y];
        }

        void paint(Graphics g) {

        }
    }

    class Xonix {
        private int x, y, direction, countLives = 3;
        private boolean isWater, isSelfCross;

        Xonix() {
            init();
        }

        void init() {
            y = 0;
            x = FIELD_WIDTH / 2;
            direction = 0;
            isWater = false;
        }

        void setDirection(int direction) {
            this.direction = direction;
        }

        void paint(Graphics g) {

        }
        int getCountLives() { return 1; }
    }

    class Cube {

    }

    class GameOver {
        void paint(Graphics g) {

        }

    }

    class Balls {
        void paint(Graphics g) {

        }

    }

    class Ball {
        private int x, y, dx, dy;

        Ball() {
            do {
                x = random.nextInt(FIELD_WIDTH);
                y = random.nextInt(FIELD_HEIGHT);
            } while (field.getColor(x ,y) > COLOR_WATER);
            dx = random.nextBoolean()? 1 : -1;
            dy = random.nextBoolean()? 1 : -1;
        }
    }

    class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            field.paint(g);
            xonix.paint(g);
            balls.paint(g);
//            cube.paint(g);
            gameover.paint(g);
        }
    }
}