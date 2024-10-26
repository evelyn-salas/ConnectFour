import java.util.*;

// Evelyn Salas
// C1: Abstract Strategy Games
//
// A class to represent a game of connect four that implements the
// AbstractStrategyGame interface.

public class ConnectFour extends AbstractStrategyGame {
  private char[][] board;
  private boolean isXTurn;

  // Constructs new game
  public ConnectFour() {
    board = new char[][] { { '-', '-', '-', '-', '-', '-', '-' },
        { '-', '-', '-', '-', '-', '-', '-' },
        { '-', '-', '-', '-', '-', '-', '-' },
        { '-', '-', '-', '-', '-', '-', '-' },
        { '-', '-', '-', '-', '-', '-', '-' },
        { '-', '-', '-', '-', '-', '-', '-' } };
    isXTurn = true;
  }

  // Checks if the game is over
  public boolean isGameOver() {
    return getWinner() >= 0;
  }

  public int getWinner() {
    // Checks rows
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[0].length - 3; col++) {

        if (board[row][col] == board[row][col + 1]
            && board[row][col] == board[row][col + 2]
            && board[row][col] == board[row][col + 3]
            && board[row][col] != '-') {
          return board[row][col] == 'X' ? 1 : 2;
        }
      }
    }

    // Checks collums
    for (int row = 0; row < board.length - 3; row++) {
      for (int col = 0; col < board[0].length; col++) {
        if (board[row][col] == board[row + 1][col]
            && board[row][col] == board[row + 2][col]
            && board[row][col] == board[row + 3][col]
            && board[row][col] != '-') {
          return board[row][col] == 'X' ? 1 : 2;
        }
      }
    }

    // Checks left to right diagonal
    for (int row = 0; row < board.length - 3; row++) {
      for (int col = 0; col < board[0].length - 3; col++) {
        if (board[row][col] == board[row + 1][col + 1]
            && board[row][col] == board[row + 2][col + 2]
            && board[row][col] == board[row + 3][col + 3]
            && board[row][col] != '-') {
          return board[row][col] == 'X' ? 1 : 2;
        }
      }
    }

    // Checks right to left diagonal
    for (int row = 3; row < board.length; row++) {
      for (int col = 0; col < board[0].length - 3; col++) {
        if (board[row][col] == board[row - 1][col + 1]
            && board[row][col] == board[row - 2][col + 2]
            && board[row][col] == board[row - 3][col + 3]
            && board[row][col] != '-') {
          return board[row][col] == 'X' ? 1 : 2;
        }
      }
    }

    // Checks for ties
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[row].length; col++) {
        if (board[row][col] == '-') {
          return -1;
        }
      }
    }

    // it's a tie!
    return 0;

  }// getwinner end

  public int getNextPlayer() {
    if (isGameOver()) {
      return -1;
    }
    return isXTurn ? 1 : 2;
  }

  public void makeMove(Scanner input) {
    char currPlayer = isXTurn ? 'X' : 'O';
    int row = 0;
    System.out.print("Column? ");
    int col = input.nextInt();
    for (row = 5; row >= 0; row--) {
      makeMove(row, col, currPlayer);
    }

    isXTurn = !isXTurn;
  }

  private void makeMove(int row, int col, char player) {
    while (board[row][col] != '-') {
      if (row < 0 || row >= board.length ||
          col < 0 || col >= board[0].length) {
        throw new IllegalArgumentException("Invalid board position: " + row + "," +
            col);
      }

      if (board[row][col] != '-') {
        throw new IllegalArgumentException("Space already occupied: " + row + "," +
            col);
      }

      board[row][col] = player;
    }
  }

  public String instructions() {
    String result = "";
    result += "Player 1 is X and goes first. Choose where to play by entering a\n";
    result += "column number, where 0 is the left most collumn and 6 is the right most colunm.\n";
    result += "Spaces show as a - are empty. The game ends when one player connects four spaces\n";
    result += "in a row, in which case that player wins, or when the board is full, in which\n";
    result += "case the game ends in a tie.";
    return result;
  }

  public String toString() {
    String result = "";
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board.length; col++) {
        result += board[row][col] + " ";
      }
      result += "\n";
    }
    return result;
  }
}

/// posible horizontal wins
////// {'o', 'o', 'o','o', '-', '-', '-'}}
////// {'-', 'o', 'o','o', 'o', '-', '-'}}
////// {'-', '-', 'o','o', 'o', 'o', '-'}}
////// {'-', '-', '-','o', 'o', 'o', 'o'}}

/*
 * Possible diagonal wins, l to r
 * { '-', '-', '-', '-', '-', '-', '-' },
 * { '-', '-', '-', '-', '-', '-', '-' },
 * { '-', '-', '-', 'X', '-', '-', '-' },
 * { '-', '-', 'X', '-', '-', '-', '-' },
 * { '-', 'X', '-', '-', '-', '-', '-' },
 * { 'X', '-', '-', '-', '-', '-', '-' } };
 *
 * { '-', '-', '-', '-', '-', '-', '-' },
 * { '-', '-', '-', 'X', '-', '-', '-' },
 * { '-', '-', 'X', '-', '-', '-', '-' },
 * { '-', 'X', '-', '-', '-', '-', '-' },
 * { 'X', '-', '-', '-', '-', '-', '-' },
 * { '-', '-', '-', '-', '-', '-', '-' } };
 *
 * { '-', '-', '-', 'X', '-', '-', '-' },
 * { '-', '-', 'X', '-', '-', '-', '-' },
 * { '-', 'X', '-', '-', '-', '-', '-' },
 * { 'X', '-', '-', '-', '-', '-', '-' },
 * { '-', '-', '-', '-', '-', '-', '-' },
 * { '-', '-', '-', '-', '-', '-', '-' } };
 *
 * { '-', '-', '-', '-', '-', '-', '-' },
 * { '-', '-', '-', '-', '-', '-', '-' },
 * { '-', '-', '-', '-', 'X', '-', '-' },
 * { '-', '-', '-', 'X', '-', '-', '-' },
 * { '-', '-', 'X', '-', '-', '-', '-' },
 * { '-', 'X', '-', '-', '-', '-', '-' } };
 *
 * { '-', '-', '-', '-', '-', '-', '-' },
 * { '-', '-', '-', '-', '-', '-', '-' },
 * { '-', '-', '-', '-', '-', '-', '-' },
 * { '-', '-', '-', '-', '-', '-', '-' },
 * { '-', '-', '-', '-', '-', '-', '-' },
 * { '-', '-', '-', '-', '-', '-', '-' } };
 *
 * { '-', '-', '-', '-', '-', '-', '-' },
 * { '-', '-', '-', '-', '-', '-', '-' },
 * { '-', '-', '-', '-', '-', '-', '-' },
 * { '-', '-', '-', '-', '-', '-', '-' },
 * { '-', '-', '-', '-', '-', '-', '-' },
 * { '-', '-', '-', '-', '-', '-', '-' } };
 */