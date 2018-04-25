package koshon.com.autosudoku.adapter;

import static koshon.com.autosudoku.sudoku_generator.constants.Constants.SUDOKU_SIZE;

public class Adapter {
    static int[][] sudoku = {
            {11, 12, 13, 14, 15, 16, 17, 18, 19},
            {21, 22, 23, 24, 25, 26, 27, 28, 29},
            {31, 32, 33, 34, 35, 36, 37, 38, 39},

            {41, 42, 43, 44, 45, 46, 47, 48, 49},
            {51, 52, 53, 54, 55, 56, 57, 58, 59},
            {61, 62, 63, 64, 65, 66, 67, 68, 69},

            {71, 72, 73, 74, 75, 76, 77, 78, 79},
            {81, 82, 83, 84, 85, 86, 87, 88, 89},
            {91, 92, 93, 94, 95, 96, 97, 98, 99}
    };

    public static String[] takeNumbers() {
        String[] numbers = new String[SUDOKU_SIZE * SUDOKU_SIZE];
        for (int x = 0; x < SUDOKU_SIZE; x++)
            for (int y = 0; y < SUDOKU_SIZE; y++) {
                numbers[x * SUDOKU_SIZE + y] = sudoku[x][y] > 0 ? Integer.toString(sudoku[x][y]) : " ";
            }
        return numbers;
    }

    public static String[] takeSquare(int index) {
        String[] numbers = new String[9];
        for (int i = 0; i < 9; i++) {
            numbers[i] = Integer.toString(sudoku[index / 3 + i % 3][index % 3 + i / 3]);
        }
        return numbers;
    }

}
