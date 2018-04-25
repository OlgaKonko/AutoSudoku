package koshon.com.autosudoku.field;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import koshon.com.autosudoku.R;

import static koshon.com.autosudoku.field.constants.GameFieldConstants.INNER_SELL_SIZE;
import static koshon.com.autosudoku.field.constants.GameFieldConstants.NUMBERS_SCALE;
import static koshon.com.autosudoku.field.constants.Sizes.layout_width;
import static koshon.com.autosudoku.field.constants.Sizes.numbers_field_size;
import static koshon.com.autosudoku.field.constants.Sizes.numbers_sell_size;

public class NumbersField extends TableLayout {
    public Button[][] buttons;
    //private int FIELD_SIZE = 3;
    //private int NUMBERS_SCALE = 3;

    // private int size;


    public NumbersField(Context context) {
        super(context);
    }

    public NumbersField(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void drawField() {
        buttons = new Button[INNER_SELL_SIZE][INNER_SELL_SIZE];

        numbers_field_size = (layout_width / NUMBERS_SCALE);
        numbers_sell_size = ((numbers_field_size) / (INNER_SELL_SIZE));

        TableRow row;
        for (int x = 0; x < INNER_SELL_SIZE; x++) {
            row = new TableRow(this.getContext());
            for (int y = 0; y < INNER_SELL_SIZE; y++) {
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
        button.setOnClickListener(new ChoseNumberListener(x * INNER_SELL_SIZE + y));
        row.addView(button, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        button.getLayoutParams().width = numbers_sell_size;
        button.getLayoutParams().height = numbers_sell_size;
        button.setText(String.valueOf(x * INNER_SELL_SIZE + y + 1));
    }
}
