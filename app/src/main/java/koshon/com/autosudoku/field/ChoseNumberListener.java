package koshon.com.autosudoku.field;

import android.view.View;

import koshon.com.autosudoku.MainActivity;

import static koshon.com.autosudoku.adapter.model.CurrentSudoku.currentSudoku;
import static koshon.com.autosudoku.field.constants.Options.lastButton;
import static koshon.com.autosudoku.field.constants.Options.usePen;
import static koshon.com.autosudoku.sudoku_generator.constants.Constants.SUDOKU_SIZE;

public class ChoseNumberListener implements View.OnClickListener {
    public int number;

    public ChoseNumberListener(int number) {
        this.number = number;
    }

    @Override
    public void onClick(View view) {
        currentSudoku.addSolution(lastButton.x, lastButton.y, number, usePen);
        clearPencilsMarksInBlock();
        if (currentSudoku.isSolved){
        MainActivity.field.fillSolvedField();}
        lastButton.refresh();
    }

    private void clearPencilsMarksInBlock(){
        if (usePen){
            int xSquare = (lastButton.x / 3) * 3;
            int ySquare = (lastButton.y / 3) * 3;
            for (int i = 0; i < SUDOKU_SIZE; i++) {
                if (currentSudoku.field[xSquare + i % 3][ySquare + i / 3].solutions[number] == 1) {
                    currentSudoku.field[xSquare + i % 3][ySquare + i / 3].solutions[number] = 0;
                    if (MainActivity.field.buttons[xSquare + i % 3][ySquare + i / 3].gridShowed){
                        MainActivity.field.buttons[xSquare + i % 3][ySquare + i / 3].markPensil(currentSudoku.field[xSquare + i % 3][ySquare + i / 3]);
                    }
                }
            }

        }
    }
}
