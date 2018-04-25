package koshon.com.autosudoku.field;

import android.view.View;

import static koshon.com.autosudoku.adapter.model.CurrentSudoku.currentSudoku;
import static koshon.com.autosudoku.field.constants.Options.lastButton;
import static koshon.com.autosudoku.field.constants.Options.usePen;

public class ChoseNumberListener implements View.OnClickListener {
    public int number;

    public ChoseNumberListener(int number) {
        this.number = number;
    }

    @Override
    public void onClick(View view) {
        currentSudoku.addSolution(lastButton.x, lastButton.y, number, usePen);
        lastButton.refresh();
    }
}
