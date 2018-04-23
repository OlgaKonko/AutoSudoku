package koshon.com.autosudoku.field;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import koshon.com.autosudoku.R;

import static koshon.com.autosudoku.adapter.model.CurrentSudoku.currentSudoku;

public class NumbersField extends TableLayout {
    public Button[][] buttons;
    private int FIELD_SIZE = 3;
    private int NUMBERS_SCALE = 3;

    private int size;
    private int tableSize;


    public NumbersField(Context context) {
        super(context);
    }
    public NumbersField(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void drawField(int layoutSize) {
        buttons = new Button[FIELD_SIZE][FIELD_SIZE];

        this.tableSize = (layoutSize/NUMBERS_SCALE);
        int padding = (layoutSize-tableSize)/2;
       // this.setPadding(padding, 0, padding, 0);
        size = ((tableSize)/(FIELD_SIZE));

        TableRow row;
        for (int x = 0; x < FIELD_SIZE; x++) {
            row = new TableRow(this.getContext());
            for (int y = 0; y < FIELD_SIZE; y++) {
                createButton(x, y, row);
            }
            this.addView(row, new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
        }
    }

    private void createButton(int x, int y, TableRow row) {
        Button button = new Button(this.getContext());
        buttons[x][y] = button;
        button.setBackgroundResource(R.drawable.shape);
        button.setOnClickListener(new ChoseNumberListener(x*FIELD_SIZE+y));
        row.addView(button, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        button.getLayoutParams().width = size;
        button.getLayoutParams().height = size;
        button.setText(String.valueOf(x*FIELD_SIZE+y+1));
    }
}
