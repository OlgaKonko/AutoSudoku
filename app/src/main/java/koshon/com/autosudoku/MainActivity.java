package koshon.com.autosudoku;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import koshon.com.autosudoku.field.Field;
import koshon.com.autosudoku.field.NumbersField;

import static koshon.com.autosudoku.field.constants.Options.usePen;
import static koshon.com.autosudoku.field.constants.Sizes.layout_height;
import static koshon.com.autosudoku.field.constants.Sizes.layout_width;

public class MainActivity extends Activity {
    public static Field field;
    public static NumbersField numbersField;
    ImageButton penButton;
    ImageButton pencilButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTableSize();
        field = findViewById(R.id.sudokuField);
        field.drawField();
        field.fillField();
        numbersField = findViewById(R.id.numbersField);
        numbersField.drawField();
        setSized();

        penButton = findViewById(R.id.pen);
        pencilButton = findViewById(R.id.pencil);

    }

    private void setSized() {
        LinearLayout topLayout = findViewById(R.id.top);
        LinearLayout ceterLayout = findViewById(R.id.center);
        int paddings = field.getPaddingTop() + field.getPaddingBottom();
        ceterLayout.getLayoutParams().height = layout_width + paddings;
        ceterLayout.getLayoutParams().width = layout_width;
        topLayout.getLayoutParams().height = (layout_height - layout_width) / 6 + paddings;
    }


    private void setTableSize() {
        Point size = new Point();
        WindowManager w = getWindowManager();
        w.getDefaultDisplay().getSize(size);
        layout_height = size.y;
        layout_width = size.x;
    }

    public void changeTool(View view) {
        if ((view == penButton) && !usePen) {
            usePen = true;
            pencilButton.setBackgroundResource(R.drawable.no_fill);
            penButton.setBackgroundResource(R.drawable.set_aura);
        } else if ((view == pencilButton) && usePen) {
            usePen = false;
            pencilButton.setBackgroundResource(R.drawable.set_aura);
            penButton.setBackgroundResource(R.drawable.no_fill);
        }
    }
}
