public class Sudoku {
    public static boolean issafe(int sudoku[][], int row, int col, int digit) {
        // Check column
        for (int i = 0; i <= 8; i++) {
            if (sudoku[i][col] == digit) {
                return false;
            }
        }
        // Check row
        for (int j = 0; j <= 8; j++) {
            if (sudoku[row][j] == digit) {
                return false;
            }
        }
        // Check 3x3 grid
        int sr = (row / 3) * 3;
        int sc = (col / 3) * 3;
        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (sudoku[i][j] == digit) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean sudokusolver(int sudoku[][], int row, int col) {
        // Base case: if we reach the 9th row, solution is complete
        if (row == 9) {
            return true;
        }

        // Move to next row if we've reached the end of the column
        int nextrow = row, nextcol = col + 1;
        if (col + 1 == 9) {
            nextrow = row + 1;
            nextcol = 0;
        }

        // Skip cells that are prefilled (not 0)
        if (sudoku[row][col] != 0) {
            return sudokusolver(sudoku, nextrow, nextcol);
        }

        // Try digits from 1 to 9
        for (int digit = 1; digit <= 9; digit++) {
            if (issafe(sudoku, row, col, digit)) {
                sudoku[row][col] = digit;
                if (sudokusolver(sudoku, nextrow, nextcol)) {
                    return true;
                }
                sudoku[row][col] = 0; // Backtrack
            }
        }
        return false; // No valid digit found
    }

    public static void printsudoku(int sudoku[][]) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int sudoku[][] = { 
            {0, 0, 8, 0, 0, 0, 0, 0, 0},
            {4, 9, 0, 1, 5, 7, 0, 0, 2},
            {0, 0, 3, 0, 0, 4, 1, 9, 0},
            {1, 8, 5, 0, 6, 0, 0, 2, 0},
            {0, 0, 0, 0, 2, 0, 0, 6, 0},
            {9, 6, 0, 4, 0, 5, 3, 0, 0},
            {0, 3, 0, 0, 7, 2, 0, 0, 4},
            {0, 4, 9, 0, 3, 0, 0, 5, 7},
            {8, 2, 7, 0, 0, 9, 0, 1, 3} 
        };

        if (sudokusolver(sudoku, 0, 0)) {
            System.out.println("Solution exists:");
            printsudoku(sudoku);
        } else {
            System.out.println("No solution exists.");
        }
    }
}
