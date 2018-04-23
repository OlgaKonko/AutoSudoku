package koshon.com.autosudoku.field;

import android.view.View;

import static koshon.com.autosudoku.adapter.model.CurrentSudoku.currentSudoku;

public class ChoseNumberListener implements View.OnClickListener{
public int number;

    public ChoseNumberListener(int number) {
        this.number = number;
    }

    @Override
    public void onClick(View view) {
        currentSudoku.addSolution(Field.lastButton.x, Field.lastButton.y, number, Field.usePen);
        Field.lastButton.refresh();
    }
}
