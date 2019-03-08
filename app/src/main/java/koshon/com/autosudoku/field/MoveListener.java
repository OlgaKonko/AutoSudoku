package koshon.com.autosudoku.field;

import android.view.View;

import koshon.com.autosudoku.MainActivity;
import koshon.com.autosudoku.R;

import static koshon.com.autosudoku.field.constants.Options.lastButton;


public class MoveListener implements View.OnClickListener {
    private int x = 0;
    private int y = 0;
    private TableButton tableButton;

    public MoveListener(int x, int y, TableButton tableButton) {
        this.x = x;
        this.y = y;
        this.tableButton = tableButton;
    }

    public void onClick(View view) {
        if (tableButton.sellValueIsGiven) {
            MainActivity.numbersField.setVisibility(View.INVISIBLE);
        } else {
            MainActivity.numbersField.setVisibility(View.VISIBLE);
            tableButton.setBackgroundResource(R.drawable.selected_sell);
            if (lastButton != null && !lastButton.sellValueIsGiven && lastButton != tableButton) {

                lastButton.updateBackGround();
            }
            lastButton = tableButton;
        }
    }
}
