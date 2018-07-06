package koshon.com.autosudoku.adapter.model;

import koshon.com.autosudoku.sudoku_generator.model.Sudoku;

import static koshon.com.autosudoku.sudoku_generator.constants.Constants.SUDOKU_SIZE;

public class SudokuSolutions {
    public PossibleSolutions[][] field;
    Sudoku sudoku;
    public int solvedQuantity = 0;
    public boolean isSolved;

    public SudokuSolutions(Sudoku sudoku) {
        this.sudoku = sudoku;
        isSolved=false;
        startFill();
    }

    public void addSolution(int x, int y, int number, boolean pen) {
        field[x][y].setSolution(number, pen);
        isSolved = checkSudokuSolved();
    }

    private boolean checkSudokuSolved(){
        for (int x = 0; x < SUDOKU_SIZE; x++)
            for (int y = 0; y < SUDOKU_SIZE; y++) {
            if (field[x][y].solution == 0 || !field[x][y].usePen)
                return false;
            }

        for (int x = 0; x < SUDOKU_SIZE; x++)
            for (int y = 0; y < SUDOKU_SIZE; y++) {
            if (isNumberRepeat(x,y, field[x][y].solution))
                    return false;
            }
        return true;
    }

    private boolean isNumberRepeat(int x, int y, int number) {
        int xSquare = (x / 3) * 3;
        int ySquare = (y / 3) * 3;
        boolean repeat = false;
        for (int i = 0; i < SUDOKU_SIZE; i++) {
            if ((i!=y) && (field[x][i].solution == number)) {
                return true;
            }
            if ((i!=y) && (field[i][y].solution == number)) {
                return true;
            }
            if ((xSquare + i % 3 !=x)&& (ySquare + i / 3!=y) && (field[xSquare + i % 3][ySquare + i / 3].solution == number)) {
                return true;
            }
        }
        return repeat;
    }

    private void startFill() {
        field = new PossibleSolutions[SUDOKU_SIZE][SUDOKU_SIZE];
        for (int x = 0; x < SUDOKU_SIZE; x++)
            for (int y = 0; y < SUDOKU_SIZE; y++) {
                field[x][y] = new PossibleSolutions(sudoku.get(x, y));
            }
    }
}
