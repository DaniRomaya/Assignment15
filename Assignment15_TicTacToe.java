import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Assignment15_TicTacToe extends Application {

    private static final int SIZE = 5;
    private Button[][] buttons = new Button[SIZE][SIZE];
    private int[][] board = new int[SIZE][SIZE];
    private boolean playerX = true;

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Button button = new Button();
                button.setMinWidth(60);
                button.setMinHeight(60);
                button.setOnAction(event -> {
                    Button clickedButton = (Button) event.getSource();
                    int row = GridPane.getRowIndex(clickedButton);
                    int col = GridPane.getColumnIndex(clickedButton);
                    if (board[row][col] == 0) {
                        if (playerX) {
                            clickedButton.setText("X");
                            board[row][col] = 1;
                        } else {
                            clickedButton.setText("O");
                            board[row][col] = 2;
                        }
                        playerX = !playerX;
                        if (checkWinner(row, col)) {
                            showWinner();
                        } else if (isBoardFull()) {
                            showDraw();
                        }
                    }
                });
                buttons[i][j] = button;
                gridPane.add(button, j, i);
            }
        }

        Scene scene = new Scene(gridPane, 350, 350);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.show();
    }

    private boolean checkWinner(int row, int col) {
        int currentPlayer = board[row][col];
        // Check row
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == currentPlayer) {
                count++;
            }
        }
        if (count == SIZE) {
            return true;
        }

        // Check column
        count = 0;
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col] == currentPlayer) {
                count++;
            }
        }
        if (count == SIZE) {
            return true;
        }

        // Check diagonal
        count = 0;
        for (int i = 0; i < SIZE; i++) {
            if (board[i][i] == currentPlayer) {
                count++;
            }
        }
        if (count == SIZE) {
            return true;
        }

        // Check anti-diagonal
        count = 0;
        for (int i = 0; i < SIZE; i++) {
            if (board[i][SIZE - 1 - i] == currentPlayer) {
                count++;
            }
        }
        return count == SIZE;
    }

    private boolean isBoardFull() {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showWinner() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("Player " + (playerX ? "O" : "X") + " wins!");
        alert.showAndWait();
    }

    private void showDraw() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("It's a draw!");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



