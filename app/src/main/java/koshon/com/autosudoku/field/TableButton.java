package koshon.com.autosudoku.field;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import koshon.com.autosudoku.R;
import koshon.com.autosudoku.adapter.model.PossibleSolutions;


@SuppressLint("AppCompatCustomView")
public class TableButton extends TableLayout {
    public Button[] buttons;
    private static int SELLS_SIZE = 3;
    private static int BLOCK_SIZE = SELLS_SIZE*SELLS_SIZE;
    private int sellSize;
    private Button number;
    private boolean sellValueIsGiven;
    private boolean solved;
    private boolean start;
    private boolean gridInited;
    private int size;

    public TableButton(Context context) {
        super(context);
    }

    public void solve (int solution, boolean start){
        this.start = start;
        number.setVisibility(VISIBLE);
        number.setText(String.valueOf( solution));
        if (gridInited){
        for (int index = 0; index < BLOCK_SIZE; index++) {
            System.out.println("!!!"+index);
            buttons[index].getLayoutParams().height = 0;
            //buttons[index].setVisibility(INVISIBLE);
            }
        }
    }


    public void setSolutions(PossibleSolutions possibleSolutions, boolean start, boolean pensil){
        
        if  (possibleSolutions.solved){
            solve(possibleSolutions.solution, start);
        }
        else {
            if (pensil){
                initGrid();
            for (int index = 0; index < BLOCK_SIZE; index++) {
                if (possibleSolutions.solutions[index] == VISIBLE){
                buttons[index].setText(index+1);}
            }
            }
        }
    }

    public void fillSell(int size){

        this.getLayoutParams().width = size;
        this.getLayoutParams().height = size;
        sellSize = size/SELLS_SIZE;
        number = new Button(this.getContext());
        this.addView(number, new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        number.getLayoutParams().width = size;
        number.setPaddingRelative(0,0,0,0);
        number.getLayoutParams().height = size;
        number.setBackgroundResource(R.drawable.no_fill);
    }

    public void initGrid(){
        number.setVisibility(INVISIBLE);
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
        }
    }

    private void createButton(int index, TableRow row) {

        Button button = new Button(this.getContext());
        buttons[index] = button;
        button.setBackgroundResource(R.drawable.light_fill);

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
