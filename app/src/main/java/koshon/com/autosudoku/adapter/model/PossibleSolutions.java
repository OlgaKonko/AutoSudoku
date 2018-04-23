package koshon.com.autosudoku.adapter.model;


import static koshon.com.autosudoku.sudoku_generator.constants.Constants.SUDOKU_SIZE;

public class PossibleSolutions {
    public int[] solutions;
    public boolean solved;
    public int solution;


    private static int POSSIBLE =1;
    private static int IMPOSSIBLE =0;

    public PossibleSolutions(int solution){
        if (solution!=0){
        solved = true;
        this.solution = solution-1;
        }
        else
        {
            solved = false;
        }
        setSolutions();

    }

    public void setSolution(int number, boolean pen){
        if (solutions[number] == IMPOSSIBLE){

        if (pen){
            for (int i = 0; i<SUDOKU_SIZE; i++){
                 solutions[i] = IMPOSSIBLE;
        }
        solution=number;
            solved = true;
    }
    solutions[number]=POSSIBLE;
    }
    else {
            solutions[number] = IMPOSSIBLE;
            solved = false;
        }
    }

    public boolean checkIfSolved(){
        if (!solved){
        int possibleSolutionsCount = 0;
        int possibleSolution= -1;
        for (int i = 0; i<SUDOKU_SIZE; i++){
            if ( solutions[i] == POSSIBLE){
                possibleSolutionsCount++;
                possibleSolution = i+1;
            }
        }
        if (possibleSolutionsCount ==1){
            solution = possibleSolution;
            solved=true;
        }}
        return solved;
    }

    public void markImpossible(int number) {
        solutions[number-1] = IMPOSSIBLE;
    }

    private void setSolutions(){
        solutions = new int[SUDOKU_SIZE];
        for (int i = 0; i<SUDOKU_SIZE; i++){
            solutions[i]=IMPOSSIBLE;
        }

        if (solved){
            solutions[solution]=POSSIBLE;
        }
    }
}
