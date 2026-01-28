package org.noobtools.rpggame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.noobtools.rpggame.model.Player;

public class App extends Application {

    private static final int TILE_SIZE = 16;
    private static final int WIDTH = 40;
    private static final int HEIGHT = 30;
    private static final int GAME_WIDTH = 40;
    private static final int GAME_HEIGHT = 20;

    // Spelplan: '#' = vägg, '.' = golv
    private char[][] map = new char[HEIGHT][WIDTH];
    private Player player;

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        generateMap();
        player = new Player(1, 1);

        draw(gc);

        Scene scene = new Scene(new StackPane(canvas));
        if (player != null) {
            scene.setOnKeyPressed(event -> {
                KeyCode code = event.getCode();
                if (code == KeyCode.UP) player.moveUp(map);
                if (code == KeyCode.DOWN) player.moveDown(map);
                if (code == KeyCode.LEFT) player.moveLeft(map);
                if (code == KeyCode.RIGHT) player.moveRight(map);
                draw(gc);
            });
        }

        stage.setScene(scene);
        stage.setTitle("Roguelike Game");
        stage.show();
    }

    private void generateMap() {
        // Skapa en enkel karta med väggar runtom
        for (int y = 0; y < GAME_HEIGHT; y++) {
            for (int x = 0; x < GAME_WIDTH; x++) {
                if (y == 0 || y == GAME_HEIGHT - 1 || x == 0 || x == GAME_WIDTH - 1) {
                    map[y][x] = '#'; // vägg
                } else {
                    map[y][x] = '.'; // golv
                }
            }
        }

        // Extra väggar som exempel
        map[5][5] = '#';
        map[6][5] = '#';
        map[7][5] = '#';
    }

    private void draw(GraphicsContext gc) {
        // Bakgrund
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);

        // Rita karta
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (map[y][x] == '#') {
                    gc.setFill(Color.DARKGRAY);
                    gc.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                } else {
                    gc.setFill(Color.LIGHTGRAY);
                    gc.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }

        // Rita spelaren
        if (player != null) {
            gc.setFill(Color.BLUE);
            gc.fillRect(player.getX() * TILE_SIZE, player.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }

    }

    public static void main(String[] args) {
        launch();
    }
}
