package koshon.com.autosudoku.field;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.ViewGroup;
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
    public PencilNumbers buttons;
    private Button number;
    TableRow row;
    public int x;
    public int y;
    public boolean sellValueIsGiven = false;

    private boolean solved;

   // private boolean gridInited = false;
    public boolean gridShowed = false;

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


    public void setSolutions(PossibleSolutions possibleSolutions, boolean start) {
        if (start){
            markPen(possibleSolutions.solution, start);
        }
        else {
        if (possibleSolutions.solved) {
            System.out.println("!!!! try to mark solution pen "+possibleSolutions.solution);
            markPen(possibleSolutions.solution, start);
        } else {
            if (!usePen) {
                System.out.println("!!!! try to mark pensil");
                markPensil(possibleSolutions);
            } else {
                System.out.println("!!!! try to mark pen"+possibleSolutions.solution);
                markPen(possibleSolutions.solution, start);
            }
        }}
    }

    public void markPen(int solution, boolean start) {
        System.out.println("!!!! mark pen");
        changeView(false);
        //this.start = start;

        if (solution == 0) {
            number.setText("");
        } else {
            number.setText(String.valueOf(solution));
            if (start) {
                sellValueIsGiven = true;
                number.setBackgroundResource(R.color.given_color);
            }
        }
    }

    public void markPensil(PossibleSolutions possibleSolutions) {
        System.out.println("!!!! mark pensil");
        buttons.initGrid();
        changeView(true);

        for (int index = 0; index < INNER_BLOCK_SIZE; index++) {
            if (possibleSolutions.solutions[index] == 1) {
                System.out.println("!!!! mark pensil sell "+index);
                buttons.setText(index, String.valueOf(index + 1));
               buttons.setBackground(index, R.drawable.selected_number);
            } else {
                buttons.setText(index, "!");
                buttons.setBackground(index, R.drawable.gray_shape);
            }
        }
    }

    public void markSolved() {
                sellValueIsGiven = true;
                number.setBackgroundResource(R.color.solved_color);


    }

    private void changeView(boolean showGrid) {
        if (showGrid && !gridShowed){
            System.out.println("!!!! change view to table");
           //number.getLayoutParams().width = empty_size;
           setDimensions(number, true);
            gridShowed = true;
        }
        else
            if (!showGrid && gridShowed){
                System.out.println("!!!! change view to button");
                //number.getLayoutParams().width = game_field_sell_size;
                setDimensions(number, false);
                gridShowed = false;
            }

     /*   if (showGrid && !gridShowed) {
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

        }*/
    }
    private void setDimensions(Button number, boolean isEmpty){
        android.view.ViewGroup.LayoutParams params = number.getLayoutParams();
        if (isEmpty)
        params.width = empty_size;
        else
            params.width = game_field_sell_size;
        params.height =  game_field_sell_size;
        number.setLayoutParams(params);
    }

    public void setOnClickListener(MoveListener listener) {
        this.listener = listener;
    }

    public void fillSell() {
        this.getLayoutParams().width = game_field_sell_size;
        this.getLayoutParams().height = game_field_sell_size;
        game_field_inner_sell_size = game_field_sell_size / INNER_SELL_SIZE;
        number = new Button(this.getContext());
        number.setOnClickListener(listener);
          number.setTextSize(TypedValue.COMPLEX_UNIT_DIP,number.getTextSize()*0.7f );
        textSize = number.getTextSize() / 12;
       // this.addView(number, new LayoutParams(LayoutParams.MATCH_PARENT,
       //         LayoutParams.MATCH_PARENT));
        row = new TableRow(this.getContext());
        row.addView(number, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        buttons = new PencilNumbers(this.getContext(), listener);
        row.addView(buttons, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));


        setDimensions(number, false);
        //number.setTextSize(number.getTextSize()*2);
        //number.getLayoutParams().width = game_field_sell_size;
        number.setPaddingRelative(0, 0, 0, 0);
       //number.getLayoutParams().height = game_field_sell_size;
        number.setBackgroundResource(R.drawable.no_fill);
        this.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
    }

    public void setImageResource(int resId) {
        Drawable drawable = ContextCompat.getDrawable(this.getContext(), resId);
        this.setBackground(drawable);

    }


}
