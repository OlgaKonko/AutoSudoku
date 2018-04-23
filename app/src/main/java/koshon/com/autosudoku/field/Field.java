package koshon.com.autosudoku.field;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import koshon.com.autosudoku.R;
import koshon.com.autosudoku.adapter.model.SudokuSolutions;

import static koshon.com.autosudoku.adapter.model.CurrentSudoku.currentSudoku;

public class Field extends TableLayout {
    public TableButton[][] buttons;
    NumbersField numbersField;
    public static TableButton lastButton;
    private int FIELD_SIZE = 9;
    private int LINES_SIZE = 4;
    private int LINE_SCALE = 150;
    private int lineSize;
    private int size;
    private int tableSize;
    public static boolean usePen = true;

    public Field(Context context) {
        super(context);
    }
    public Field(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void fillField (){
        for (int x = 0; x < FIELD_SIZE; x++) {
            for (int y = 0; y < FIELD_SIZE; y++) {
            buttons[x][y].setSolutions(currentSudoku.field[x][y], true);
            }

    }}

    public void drawField(int layoutSize) {
        numbersField = findViewById(R.id.numbersField);
        usePen = true;
        this.tableSize = layoutSize-(this.getPaddingLeft()+this.getPaddingRight());
        buttons = new TableButton[FIELD_SIZE][FIELD_SIZE];
        lineSize = tableSize/ LINE_SCALE;
        size = ((tableSize -(lineSize *LINES_SIZE))/(FIELD_SIZE));
        TableRow row;
        for (int x = 0; x < FIELD_SIZE; x++) {
            if (x%3 == 0){
                createHorizontalLine();
            }
            row = new TableRow(this.getContext());
            for (int y = 0; y < FIELD_SIZE; y++) {
                createButton(x, y, row);
            }
            createLine(row, lineSize, size);
            this.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }
        createHorizontalLine();
    }

    private void createButton(int x, int y, TableRow row) {
        if (y%3 == 0){
            createLine(row, lineSize, size);
        }
        TableButton button = new TableButton(this.getContext(),x,y);
        buttons[x][y] = button;
        button.setImageResource(R.drawable.shape);
        button.setOnClickListener(new MoveListener(x, y, button));
        row.addView(button, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        button.fillSell(size);
    }

    private void createLine(TableRow row, int width, int height){
        ImageView button = new ImageView(this.getContext());
        button.setImageResource(R.drawable.fill);
        row.addView(button, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        button.getLayoutParams().width = width;
        button.getLayoutParams().height = height;

        button.setScaleType(ImageView.ScaleType.FIT_XY);
    }
    private void createHorizontalLine(){
        TableRow row = new TableRow(this.getContext());
        for (int y = 0; y < FIELD_SIZE; y++) {
            if (y%3 == 0){
                createLine(row, lineSize, lineSize);
            }
            createLine(row, size, lineSize);
        }
        createLine(row, lineSize, lineSize);
        this.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
    }
}
