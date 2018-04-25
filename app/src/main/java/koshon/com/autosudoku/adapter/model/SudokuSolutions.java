package koshon.com.autosudoku.adapter.model;

import koshon.com.autosudoku.sudoku_generator.model.Sudoku;

import static koshon.com.autosudoku.sudoku_generator.constants.Constants.SUDOKU_SIZE;

public class SudokuSolutions {
    public PossibleSolutions[][] field;
    Sudoku sudoku;
    public int solvedQuantity = 0;

    public SudokuSolutions(Sudoku sudoku) {
        this.sudoku = sudoku;
        startFill();
    }

    public void addSolution(int x, int y, int number, boolean pen) {
        field[x][y].setSolution(number, pen);
    }

    private void startFill() {
        field = new PossibleSolutions[SUDOKU_SIZE][SUDOKU_SIZE];
        for (int x = 0; x < SUDOKU_SIZE; x++)
            for (int y = 0; y < SUDOKU_SIZE; y++) {
                field[x][y] = new PossibleSolutions(sudoku.get(x, y));
            }
    }
}
