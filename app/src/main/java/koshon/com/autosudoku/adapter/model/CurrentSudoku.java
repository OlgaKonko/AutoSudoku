package koshon.com.autosudoku.adapter.model;

import koshon.com.autosudoku.sudoku_generator.model.Sudoku;

import static koshon.com.autosudoku.sudoku_generator.SudokuGenerator.generateSudoku;

public class CurrentSudoku {
    public static SudokuSolutions currentSudoku;

    public static void createNewSudoku() {
        long seed = System.currentTimeMillis();
        Sudoku sudoku = generateSudoku(seed);
        currentSudoku = new SudokuSolutions(sudoku);
    }
}
