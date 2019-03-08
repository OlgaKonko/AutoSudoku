package koshon.com.autosudoku.adapter.model;

import java.util.ArrayList;
import java.util.Collections;

import koshon.com.autosudoku.sudoku_generator.model.Sudoku;

import static koshon.com.autosudoku.sudoku_generator.constants.Constants.BLOCK_SIZE;
import static koshon.com.autosudoku.sudoku_generator.constants.Constants.SUDOKU_SIZE;

public class SudokuSolutions {
    public PossibleSolutions[][] field;
    public int[][] status;
    Sudoku sudoku;
    public boolean isSolved;
    int solvedNumbers;

    public SudokuSolutions(Sudoku sudoku) {
        this.sudoku = sudoku;
        isSolved=false;
        startFill();
    }

    public boolean isSolved(){
        return (solvedNumbers == SUDOKU_SIZE);
    }
    public void addSolution(int x, int y, int number, boolean pen) {
        field[x][y].setSolution(number, pen);
        updateStatus();
        isSolved = checkSudokuSolved();
    }

    private void updateStatus(){
        for (int x = 0; x < SUDOKU_SIZE; x++)
            for (int y = 0; y < SUDOKU_SIZE; y++) {
            status[x][y]=0;
            }
            solvedNumbers = 0;
        for (int x = 0; x < SUDOKU_SIZE; x++){
            checkHorizontal(x);
            checkVertical(x);
            checkBlock(x);
        }
    }

    private void checkHorizontal(int x){
        ArrayList<Integer> solutions = new ArrayList<>(SUDOKU_SIZE);
        for (int y = 0; y < SUDOKU_SIZE; y++) {
            if (!field[x][y].usePen){
                solutions.add(0);
            }
            else
                solutions.add(field[x][y].solution);
        }
        if (isListHasAllNumbers(solutions)){
            for (int y = 0; y < SUDOKU_SIZE; y++) {
                status[x][y]++;
            }
        }
    }

    private void checkVertical(int y){
        ArrayList<Integer> solutions = new ArrayList<>(SUDOKU_SIZE);
        for (int x = 0; x < SUDOKU_SIZE; x++) {
            if (!field[x][y].usePen){
                solutions.add(0);
            }
            else
                solutions.add(field[x][y].solution);
        }
        if (isListHasAllNumbers(solutions)){
            for (int x = 0; x < SUDOKU_SIZE; x++) {
                status[x][y]++;
            }
        }
    }

    private void checkBlock(int index){
        int xIndex = (index/BLOCK_SIZE)*BLOCK_SIZE;
        int yIndex = (index%BLOCK_SIZE)*BLOCK_SIZE;
        System.out.println ("!!! for index "+ index+" "+xIndex+" "+yIndex);
        ArrayList<Integer> solutions = new ArrayList<>(SUDOKU_SIZE);
        for (int innerIndex = 0; innerIndex < SUDOKU_SIZE; innerIndex++) {
            System.out.println ("!!! check in "+(xIndex+innerIndex/BLOCK_SIZE)+" "+(yIndex+innerIndex%BLOCK_SIZE));
            if (!field[xIndex+innerIndex/BLOCK_SIZE][yIndex+innerIndex%BLOCK_SIZE].usePen){
                solutions.add(0);
            }
            else
                solutions.add(field[xIndex+innerIndex/BLOCK_SIZE][yIndex+innerIndex%BLOCK_SIZE].solution);
        }
        if (isListHasAllNumbers(solutions)){
            for (int innerIndex = 0; innerIndex < SUDOKU_SIZE; innerIndex++) {
                status[xIndex+innerIndex/BLOCK_SIZE][yIndex+innerIndex%BLOCK_SIZE]++;
                if (status[xIndex+innerIndex/BLOCK_SIZE][yIndex+innerIndex%BLOCK_SIZE] == 3)
                    solvedNumbers++;
            }
        }
    }

    private boolean isListHasAllNumbers(ArrayList<Integer> list){
        Collections.sort(list);
        for (int index = 0; index < SUDOKU_SIZE; index++) {
            if (list.get(index)!= (index+1))
                return false;
        }
        return true;
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
            status = new int[SUDOKU_SIZE][SUDOKU_SIZE];
    }
}
