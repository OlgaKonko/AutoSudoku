package koshon.com.autosudoku.field;

import android.content.Context;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import koshon.com.autosudoku.R;

import static koshon.com.autosudoku.field.constants.GameFieldConstants.INNER_BLOCK_SIZE;
import static koshon.com.autosudoku.field.constants.GameFieldConstants.INNER_SELL_SIZE;
import static koshon.com.autosudoku.field.constants.Sizes.game_field_inner_sell_size;

public class PencilNumbers  extends TableLayout {
    Button[] buttons;
    boolean showed;
    boolean inited;
    MoveListener listener;

    public PencilNumbers(Context context,  MoveListener listener) {
        super(context);
        showed = true;
        this.listener = listener;
        inited = false;
    }

    public void setText (int index, String text){
        buttons[index].setText(text);
    }
    public void setBackground (int index, int resource){
        buttons[index].setBackgroundResource(resource);
    }

    public void initGrid() {
        if (!inited){
            inited=true;
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
    }
    }

    private void createButton(int index, TableRow row) {

        Button button = new Button(this.getContext());
        buttons[index] = button;
        button.setBackgroundResource(R.drawable.light_fill);
        button.setOnClickListener(listener);
        //button.setTextSize(number.getTextSize()/9 );
       // button.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        row.addView(button, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        button.getLayoutParams().width = game_field_inner_sell_size;
        button.getLayoutParams().height = game_field_inner_sell_size;

        //  button.setScaleType(ImageView.ScaleType.FIT_XY);

    }
}
