
public class Sudoku {
	private final int SIZE = 9;
	private String[][] board;


	/**
	 * Default constructor -- construct an empty Sudoku board / puzzle.
	 * And each board position is set to an inital String " X ".
	 */
	public Sudoku () {
		board = new String[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				board[i][j] = "x";
			}
		}
	} //end Sodoku()


	/**
	 * Input an initial starting board for Sudoku. Consider using one online, such as
	 * https://www.sudokuoftheday.com/
	 * @param board
	 * @throws Exception
	 */
	public void inputBoard(char [][] board) throws Exception {

	} //end inputBoard()


	/**
	 * Checks if the place on Sodoku board is safe (row, col)
	 */
	public static boolean isSafe(int[][] board, int row, int col, int num) {
		int boardLen = board.length;

		//check if num is already in row
		for (int c = 0; c < boardLen; c++) {
			if (board[row][c] == num) {
				return false;
			}
		}

		//check if num is already in col
		for (int r = 0; r < boardLen; r++) {
			if (board[r][col] == num) {
				return false;
			}
		}

		//check if num is already in box
		int sqrt = (int)Math.sqrt(boardLen); //3
		int boxRowStart = row - (row % sqrt);
		int boxColStart = col - (col % sqrt);
		for (int r = boxRowStart; r < boxRowStart + sqrt; r++) {
			for (int c = boxColStart; c < boxColStart + sqrt; c++) {
				if (board[r][c] == num) {
					return false;
				}
			}
		}

		//checks are all invalid, safe to place num
		return true;
	} //end isSafe()


	/**
	 * Solves the Sudoku puzzle from the starting position, if possible.
	 * @return true if the puzzle is solved; false if it cannot be solved.
	 * Utilizes backtracking
	 */
	public boolean solveSodoku(int[][] board) {
		//backtracking
			//find empty box
			//try all numbers
			//validate row, col, 3x3 box
			//repeat


		return false;
	} //end solveSodoku()


	/**
	 * Prints the Sudoku board to the console.
	 * A String representation of the board, showing the contents of each space
	 */
	public void printBoard() {
		int sqrt = (int)Math.sqrt(SIZE); //3
		System.out.print("  -----------------------\n"); //top row

		//starts top left
		for (int row = 0; row < SIZE; row++) {
			System.out.print(" | "); //board edge (left)

			for (int col = 0; col < SIZE; col++) {
				System.out.print(board[row][col] + " ");
				if ((col + 1) % sqrt == 0) {
					System.out.print("| "); //dividing vert. lines + board edge (right)
				}
			}
			System.out.print("\n");
			if ((row + 1) % sqrt == 0) {
				System.out.print("  -----------------------\n"); //dividing horz. lines + bottom row
			}
		}
	} //end printBoard()
	
	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku();
//		sudoku.inputBoard(char [][] board);
//		sudoku.solveSodoku();
		sudoku.printBoard();
	}
	
}
