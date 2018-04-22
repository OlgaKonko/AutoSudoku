package koshon.com.autosudoku.field;

import android.view.View;


public class MoveListener implements View.OnClickListener {
    private int x = 0;
    private int y = 0;

    public MoveListener(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public void onClick(View view) {

    }
}
