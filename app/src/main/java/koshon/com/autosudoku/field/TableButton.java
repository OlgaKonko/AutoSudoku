package koshon.com.autosudoku.field;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import koshon.com.autosudoku.R;
import koshon.com.autosudoku.adapter.model.PossibleSolutions;

import static koshon.com.autosudoku.adapter.model.CurrentSudoku.currentSudoku;


@SuppressLint("AppCompatCustomView")
public class TableButton extends TableLayout {
    public Button[] buttons;
    public int x;
    public int y;
    private static int SELLS_SIZE = 3;
    private static int BLOCK_SIZE = SELLS_SIZE*SELLS_SIZE;
    private int sellSize;
    private Button number;
    public boolean sellValueIsGiven=false;
    private boolean solved;
    private boolean start;
    private boolean gridInited = false;
    private boolean gridShowed = false;
    private int size;
    MoveListener listener;
    float textSize;

    public TableButton(Context context, int x, int y) {
        super(context);
        this.x=x;
        this.y=y;
    }
    public void refresh(){
        setSolutions(currentSudoku.field[x][y], false);
    }

    public void markPen(int solution, boolean start){

       changeView(false);
        this.start = start;

        if (solution ==0){
            number.setText("");
        }
        else{
        number.setText(String.valueOf( solution+1));
            if (start){
                sellValueIsGiven = true;
                number.setBackgroundResource(R.color.given_color);
            }
        }
    }

    public void markPensil (PossibleSolutions possibleSolutions){
            initGrid();
            changeView(true);
            for (int index = 0; index < BLOCK_SIZE; index++) {
                if (possibleSolutions.solutions[index] == 1) {
                    buttons[index].setText(String.valueOf(index + 1));
                    buttons[index].setBackgroundResource(R.drawable.selected_number);
                } else {
                    buttons[index].setText("!");
                    buttons[index].setBackgroundResource(R.drawable.gray_shape);
                }
            }
    }

    private void changeView(boolean showGrid){
        if (showGrid && !gridShowed){
            gridShowed = true;
            number.getLayoutParams().height = 0;

            for (int index = 0; index < BLOCK_SIZE; index++) {
                buttons[index].getLayoutParams().height = sellSize;
            }
        }
        else{
            if (!showGrid && gridShowed){
                gridShowed = false;
                number.getLayoutParams().height = size;
                for (int index = 0; index < BLOCK_SIZE; index++) {
                    buttons[index].getLayoutParams().height = 0;
            }
        }

    }}

    public void setOnClickListener(MoveListener listener){
        this.listener = listener;
    }


    public void setSolutions(PossibleSolutions possibleSolutions, boolean start){
        if  (possibleSolutions.solved){
            markPen(possibleSolutions.solution, start);
        }
        else {
            if (!Field.usePen){
                markPensil(possibleSolutions);
            }
            else {
                markPen(0, start);
            }
        }
    }

    public void fillSell(int size){
        this.size = size;
        this.getLayoutParams().width = size;
        this.getLayoutParams().height = size;
        sellSize = size/SELLS_SIZE;
        number = new Button(this.getContext());
        number.setOnClickListener(listener);
      //  number.setTextSize(TypedValue.COMPLEX_UNIT_DIP,number.getTextSize()/9 );
        textSize = number.getTextSize()/12;
        this.addView(number, new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        number.getLayoutParams().width = size;
        number.setPaddingRelative(0,0,0,0);
        number.getLayoutParams().height = size;
        number.setBackgroundResource(R.drawable.no_fill);
    }

    public void initGrid(){
       // number.setVisibility(INVISIBLE);
        if (!gridInited){
            buttons = new Button[BLOCK_SIZE];
            TableRow row =new TableRow(this.getContext());
            for (int index = 0; index < BLOCK_SIZE; index++) {
                if (index%3==0) {
                    row = new TableRow(this.getContext());
                    createButton(index, row);
                    this.addView(row, new LayoutParams(LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT));
                }
                else {
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
        button.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize );
        row.addView(button, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        button.getLayoutParams().width = sellSize;
        button.getLayoutParams().height = sellSize;

      //  button.setScaleType(ImageView.ScaleType.FIT_XY);

    }

    public void setImageResource(int resId) {
        Drawable drawable = ContextCompat.getDrawable(this.getContext(),resId);
            this.setBackground(drawable);

    }


}
