package koshon.com.autosudoku.field;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import koshon.com.autosudoku.R;

import static koshon.com.autosudoku.adapter.model.CurrentSudoku.currentSudoku;
import static koshon.com.autosudoku.field.constants.GameFieldConstants.BLOCK_SIZE;
import static koshon.com.autosudoku.field.constants.GameFieldConstants.FIELD_SIZE;
import static koshon.com.autosudoku.field.constants.GameFieldConstants.LINES_SIZE;
import static koshon.com.autosudoku.field.constants.GameFieldConstants.LINE_SCALE;
import static koshon.com.autosudoku.field.constants.Sizes.game_field_lines_size;
import static koshon.com.autosudoku.field.constants.Sizes.game_field_sell_size;
import static koshon.com.autosudoku.field.constants.Sizes.game_field_size;
import static koshon.com.autosudoku.field.constants.Sizes.layout_width;

public class Field extends TableLayout {
    public TableButton[][] buttons;

    public Field(Context context) {
        super(context);
    }

    public Field(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void fillField() {
        for (int x = 0; x < FIELD_SIZE; x++) {
            for (int y = 0; y < FIELD_SIZE; y++) {
                buttons[x][y].setSolutions(currentSudoku.field[x][y], true);
            }

        }
    }

    public void drawField() {
        game_field_size = layout_width - (this.getPaddingLeft() + this.getPaddingRight());
        buttons = new TableButton[FIELD_SIZE][FIELD_SIZE];
        game_field_lines_size = game_field_size / LINE_SCALE;
        game_field_sell_size = ((game_field_size - (game_field_lines_size * LINES_SIZE)) / (FIELD_SIZE));
        TableRow row;
        for (int x = 0; x < FIELD_SIZE; x++) {
            if (x % BLOCK_SIZE == 0) {
                createHorizontalLine();
            }
            row = new TableRow(this.getContext());
            for (int y = 0; y < FIELD_SIZE; y++) {
                createButton(x, y, row);
            }
            createLine(row, game_field_lines_size, game_field_sell_size);
            this.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }
        createHorizontalLine();
    }

    private void createButton(int x, int y, TableRow row) {
        if (y % BLOCK_SIZE == 0) {
            createLine(row, game_field_lines_size, game_field_sell_size);
        }
        TableButton button = new TableButton(this.getContext(), x, y);
        buttons[x][y] = button;
        button.setImageResource(R.drawable.shape);
        button.setOnClickListener(new MoveListener(x, y, button));
        row.addView(button, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        button.fillSell();
    }

    private void createLine(TableRow row, int width, int height) {
        ImageView button = new ImageView(this.getContext());
        button.setImageResource(R.drawable.fill);
        row.addView(button, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        button.getLayoutParams().width = width;
        button.getLayoutParams().height = height;

        button.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    private void createHorizontalLine() {
        TableRow row = new TableRow(this.getContext());
        for (int y = 0; y < FIELD_SIZE; y++) {
            if (y % BLOCK_SIZE == 0) {
                createLine(row, game_field_lines_size, game_field_lines_size);
            }
            createLine(row, game_field_sell_size, game_field_lines_size);
        }
        createLine(row, game_field_lines_size, game_field_lines_size);
        this.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
    }
}
