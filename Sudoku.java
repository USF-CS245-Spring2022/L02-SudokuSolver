//@name: Mia Kobayashi
//@date and version: 6 Feb 2022, v.1
//CS245 Lab 2: Sudoku

public class Sudoku {
	private final int SIZE = 9;
	private static int[][] board;


	/**
	 * Default constructor -- construct an empty Sudoku board / puzzle.
	 * And each board position is set to an inital int "0".
	 */
	public Sudoku () {
		board = new int[SIZE][SIZE];
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				board[row][col] = 0;
			}
		}
	} //end Sudoku()


	/**
	 * Input an initial starting board for Sudoku. Consider using one online, such as
	 * https://www.sudokuoftheday.com/
	 * @param int 9x9 Sudoku board
	 * https://www.sudokuoftheday.com/dailypuzzles/2022-02-06/beginner/solution
	 * @throws IllegalArgumentException
	 */
	public void inputBoard(int[][] numForBoard) throws IllegalArgumentException {
		if (numForBoard.length != SIZE) { //checks if height is SIZE
			throw new IllegalArgumentException();
		}
		else {
			for (int i = 0; i < SIZE; i++) { //jagged array situation, checks if each row is SIZE
				if (numForBoard[i].length != SIZE) {
					throw new IllegalArgumentException();
				}
			}
		}

		//starter board, if no exception is thrown
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				board[row][col] = numForBoard[row][col];
			}
		}
	} //end inputBoard()


	/**
	 * Checks if the place on Sudoku board is safe (row, col, box)
	 * @return true if empty spot is valid place on board, false if not
	 */
	public static boolean isSafe(int[][] board, int row, int col, int num) {
		int boardLen = board.length;

		//check if num is already in row, change col#
		for (int c = 0; c < boardLen; c++) {
			if (board[row][c] == num) {
				return false;
			}
		}

		//check if num is already in col, change row#
		for (int r = 0; r < boardLen; r++) {
			if (board[r][col] == num) {
				return false;
			}
		}

		//check if num is already in box, checking 1 of 9 3x3 boxes
		int sqrt = (int)Math.sqrt(boardLen); //3
		int boxRowStart = row - (row % sqrt); //0, 3, or 6
		int boxColStart = col - (col % sqrt); //0, 3, or 6
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
	public boolean solveSudoku(int[][] board) {
		//backtracking
			//finds first empty box
			//try all numbers
			//validate row, col, 3x3 box
			//repeat

		//check row
		int row = -1; //initialize row, col
		int col = -1;
		boolean isEmpty = true;
		for (int r = 0; r < SIZE; r++) {
			for (int c = 0; c < SIZE; c++) {
				if (board[r][c] == 0) {
					row = r;
					col = c;

					isEmpty = false;
					break;
				}
			}
			if (isEmpty == false) { //!isEmpty
				break;
			}
		}

		if (isEmpty == true) { //isEmpty
			return true;
		}

		//backtrack !
		for (int desiredNum = 1; desiredNum <= SIZE; desiredNum++) {
			if (isSafe(board, row, col, desiredNum) == true) {
				board[row][col] = desiredNum;

				if (solveSudoku(board) == true) { //recursive call
					return true; //board[row][col] stays as desiredNum
				} //else, do nothing
				else {
					//replace it (FIXME ?)
					board[row][col] = 0; //board[row][col] goes back as default
				}
			}
		}

		return false; //cannot solve puzzle
	} //end solveSudoku()


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
		board = new int[][] {{0, 0, 0, 1, 2, 7, 0, 0, 0},
							{0, 3, 8, 0, 0, 0, 0, 9, 0},
							{0, 0, 4, 9, 0, 0, 0, 0, 6},
							{4, 2, 7, 3, 5, 0, 0, 1, 8},
							{3, 0, 0, 2, 0, 8, 0, 0, 5},
							{8, 5, 0, 0, 4, 1, 6, 3, 2},
							{5, 0, 0, 0, 0, 9, 7, 0, 0},
							{0, 9, 0, 0, 0, 0, 2, 8, 0},
							{0, 0, 0, 4, 7, 2, 0, 0, 0},};
		// solu:
		// 		{{9, 6, 5, 1, 2, 7, 8, 4, 3},
		//		{2, 3, 8, 5, 6, 4, 1, 9, 7},
		//		{1, 7, 4, 9, 8, 3, 5, 2, 6},
		//		{4, 2, 7, 3, 5, 6, 9, 1, 8},
		//		{3, 1, 6, 2, 9, 8, 4, 7, 5},
		//		{8, 5, 9, 7, 4, 1, 6, 3, 2},
		//		{5, 4, 2, 8, 3, 9, 7, 6, 1},
		//		{7, 9, 3, 6, 1, 5, 2, 8, 4},
		//		{6, 8, 1, 4, 7, 2, 3, 5, 9}};

		sudoku.inputBoard(board);
		sudoku.printBoard(); //unsolved board
		sudoku.solveSudoku(board);
		sudoku.printBoard(); //solved board
	} //end main
} //end class Sudoku
