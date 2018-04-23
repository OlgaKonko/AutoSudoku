package koshon.com.autosudoku;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import koshon.com.autosudoku.adapter.model.SudokuSolutions;
import koshon.com.autosudoku.field.Field;
import koshon.com.autosudoku.field.MoveListener;
import koshon.com.autosudoku.field.NumbersField;
import koshon.com.autosudoku.field.TableButton;
import koshon.com.autosudoku.sudoku_generator.model.Sudoku;

import static koshon.com.autosudoku.sudoku_generator.SudokuGenerator.generateSudoku;

public class MainActivity extends Activity {
    Field field;
    public static NumbersField numbersField;
int wight;
int height;
    ImageButton penButton;
    ImageButton pencilButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTableSize();
        field = findViewById(R.id.sudokuField);
        field.drawField(wight);
        field.fillField();
        numbersField = findViewById(R.id.numbersField);
        numbersField.drawField(wight);
        setSized();

        penButton = findViewById(R.id.pen);
        pencilButton = findViewById(R.id.pencil);

    }

    private void setSized(){
        LinearLayout topLayout = findViewById(R.id.top);
        LinearLayout ceterLayout = findViewById(R.id.center);
        int paddings = field.getPaddingTop()+ field.getPaddingBottom();
        ceterLayout.getLayoutParams().height = wight+paddings;
        ceterLayout.getLayoutParams().width = wight;
        topLayout.getLayoutParams().height = (height-wight)/6+paddings;
    }


    private void setTableSize() {
        Point size = new Point();
        WindowManager w = getWindowManager();
        w.getDefaultDisplay().getSize(size);
        wight = size.x;
        height = size.y;
    }

    public void changeTool(View view) {
        if ((view == penButton)&& !Field.usePen){
            Field.usePen = true;
            pencilButton.setBackgroundResource(R.drawable.no_fill);
            penButton.setBackgroundResource(R.drawable.set_aura);
        }
        else if ((view == pencilButton)&& Field.usePen){
            Field.usePen = false;
            pencilButton.setBackgroundResource(R.drawable.set_aura);
            penButton.setBackgroundResource(R.drawable.no_fill);
        }
    }
}
