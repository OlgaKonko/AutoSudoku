package koshon.com.autosudoku.sudoku_generator;


import koshon.com.autosudoku.sudoku_generator.cleaner.SudokuCleaner;
import koshon.com.autosudoku.sudoku_generator.model.Sudoku;

public class SudokuGenerator {

    public static Sudoku generateSudoku(int seed){
        Sudoku sudoku = new Sudoku(seed);

        return compliteSudoku(sudoku);
    }
    public static Sudoku compliteSudoku(Sudoku sudoku){
        //SudokuCleaner cleaner = new SudokuCleaner(sudoku);
        //cleaner.clearSells();
        return sudoku;
    }

}
