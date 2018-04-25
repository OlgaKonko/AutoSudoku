package koshon.com.autosudoku.field;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import koshon.com.autosudoku.R;
import koshon.com.autosudoku.adapter.model.PossibleSolutions;

import static koshon.com.autosudoku.adapter.model.CurrentSudoku.currentSudoku;
import static koshon.com.autosudoku.field.constants.GameFieldConstants.INNER_BLOCK_SIZE;
import static koshon.com.autosudoku.field.constants.GameFieldConstants.INNER_SELL_SIZE;
import static koshon.com.autosudoku.field.constants.Options.usePen;
import static koshon.com.autosudoku.field.constants.Sizes.empty_size;
import static koshon.com.autosudoku.field.constants.Sizes.game_field_inner_sell_size;
import static koshon.com.autosudoku.field.constants.Sizes.game_field_sell_size;


@SuppressLint("AppCompatCustomView")
public class TableButton extends TableLayout {
    public Button[] buttons;
    private Button number;
    public int x;
    public int y;
    public boolean sellValueIsGiven = false;

    private boolean solved;

    private boolean gridInited = false;
    private boolean gridShowed = false;

    MoveListener listener;
    float textSize;

    public TableButton(Context context, int x, int y) {
        super(context);
        this.x = x;
        this.y = y;
    }

    public void refresh() {
        setSolutions(currentSudoku.field[x][y], false);
    }

    public void markPen(int solution, boolean start) {

        changeView(false);
        //this.start = start;

        if (solution == 0) {
            number.setText("");
        } else {
            number.setText(String.valueOf(solution + 1));
            if (start) {
                sellValueIsGiven = true;
                number.setBackgroundResource(R.color.given_color);
            }
        }
    }

    public void markPensil(PossibleSolutions possibleSolutions) {
        initGrid();
        changeView(true);
        for (int index = 0; index < INNER_BLOCK_SIZE; index++) {
            if (possibleSolutions.solutions[index] == 1) {
                buttons[index].setText(String.valueOf(index + 1));
                buttons[index].setBackgroundResource(R.drawable.selected_number);
            } else {
                buttons[index].setText("!");
                buttons[index].setBackgroundResource(R.drawable.gray_shape);
            }
        }
    }

    private void changeView(boolean showGrid) {
        if (showGrid && !gridShowed) {
            gridShowed = true;
            number.getLayoutParams().height = empty_size;

            for (int index = 0; index < INNER_BLOCK_SIZE; index++) {
                buttons[index].getLayoutParams().height = game_field_inner_sell_size;
            }
        } else {
            if (!showGrid && gridShowed) {
                gridShowed = false;
                number.getLayoutParams().height = game_field_sell_size;
                for (int index = 0; index < INNER_BLOCK_SIZE; index++) {
                    buttons[index].getLayoutParams().height = empty_size;
                }
            }

        }
    }

    public void setOnClickListener(MoveListener listener) {
        this.listener = listener;
    }


    public void setSolutions(PossibleSolutions possibleSolutions, boolean start) {
        if (possibleSolutions.solved) {
            markPen(possibleSolutions.solution, start);
        } else {
            if (!usePen) {
                markPensil(possibleSolutions);
            } else {
                markPen(0, start);
            }
        }
    }

    public void fillSell() {
        this.getLayoutParams().width = game_field_sell_size;
        this.getLayoutParams().height = game_field_sell_size;
        game_field_inner_sell_size = game_field_sell_size / INNER_SELL_SIZE;
        number = new Button(this.getContext());
        number.setOnClickListener(listener);
        //  number.setTextSize(TypedValue.COMPLEX_UNIT_DIP,number.getTextSize()/9 );
        textSize = number.getTextSize() / 12;
        this.addView(number, new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        number.getLayoutParams().width = game_field_sell_size;
        number.setPaddingRelative(0, 0, 0, 0);
        number.getLayoutParams().height = game_field_sell_size;
        number.setBackgroundResource(R.drawable.no_fill);
    }

    public void initGrid() {
        if (!gridInited) {
            buttons = new Button[INNER_BLOCK_SIZE];
            TableRow row = new TableRow(this.getContext());
            for (int index = 0; index < INNER_BLOCK_SIZE; index++) {
                if (index % INNER_SELL_SIZE == 0) {
                    row = new TableRow(this.getContext());
                    createButton(index, row);
                    this.addView(row, new LayoutParams(LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT));
                } else {
                    createButton(index, row);
                }
            }
            gridInited = true;
        }
    }

    private void createButton(int index, TableRow row) {

        Button button = new Button(this.getContext());
        buttons[index] = button;
        button.setBackgroundResource(R.drawable.light_fill);
        button.setOnClickListener(listener);
        //button.setTextSize(number.getTextSize()/9 );
        button.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        row.addView(button, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        button.getLayoutParams().width = game_field_inner_sell_size;
        button.getLayoutParams().height = game_field_inner_sell_size;

        //  button.setScaleType(ImageView.ScaleType.FIT_XY);

    }

    public void setImageResource(int resId) {
        Drawable drawable = ContextCompat.getDrawable(this.getContext(), resId);
        this.setBackground(drawable);

    }


}
