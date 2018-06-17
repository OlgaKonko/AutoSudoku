package koshon.com.autosudoku.adapter.model;


import static koshon.com.autosudoku.sudoku_generator.constants.Constants.SUDOKU_SIZE;

public class PossibleSolutions {
    public int[] solutions;
    public boolean solved;
    public int solution;
    public boolean usePen;


    private static int MARKED = 1;
    private static int UNMARKED = 0;

    public PossibleSolutions(int solution) {
        usePen = true;
        if (solution != 0) {
            solved = true;
            this.solution = solution;
        } else {
            solved = false;
            this.solution =0;
        }
        setSolutions();

    }

    public void setSolution(int number, boolean pen) {
        usePen = pen;
        if (pen){
            if (solution== number)
                solution = UNMARKED;
            else
                solution=number+1;
        }
        else
            if (solutions[number] == UNMARKED)
                solutions[number] = MARKED;
        else solutions[number]= UNMARKED;

        /*
        if (solutions[number] == UNMARKED) {

            if (pen) {
                for (int i = 0; i < SUDOKU_SIZE; i++) {
                    solutions[i] = UNMARKED;
                }
                solution = number;
                solved = true;
            }
            solutions[number] = MARKED;
        } else {
            solutions[number] = UNMARKED;
            solved = false;
        }*/
    }

    public boolean checkIfSolved() {
        if (!solved) {
            int possibleSolutionsCount = 0;
            int possibleSolution = -1;
            for (int i = 0; i < SUDOKU_SIZE; i++) {
                if (solutions[i] == MARKED) {
                    possibleSolutionsCount++;
                    possibleSolution = i + 1;
                }
            }
            if (possibleSolutionsCount == 1) {
                solution = possibleSolution;
                solved = true;
            }
        }
        return solved;
    }

    public void markImpossible(int number) {
        solutions[number - 1] = UNMARKED;
    }

    private void setSolutions() {
        solutions = new int[SUDOKU_SIZE];
        for (int i = 0; i < SUDOKU_SIZE; i++) {
            solutions[i] = UNMARKED;
        }
    }
}
