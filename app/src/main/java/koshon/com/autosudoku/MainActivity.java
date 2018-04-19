package koshon.com.autosudoku;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import static koshon.com.autosudoku.Adapter.takeNumbers;
import static koshon.com.autosudoku.Adapter.takeSquare;

public class MainActivity extends Activity {
    GridView gvMain;
    ArrayAdapter<String> adapter;
   // ArrayAdapter<GridView> gridAdapter;
    GridView[] blocks;
    static int BLOCKS_NUMBER = 9;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGrid();
        //GridAdapter gridAdapter = new GridAdapter(this,  R.id.square_field, blocks);
        ///gridAdapter= new ArrayAdapter<>(this, R.layout.square, R.id.square_field, blocks);
        //gvMain = findViewById(R.id.sudoku_field);
        //gvMain.setAdapter(gridAdapter);
        //adjustGridView();
    }

    private void initBlocks(){
        blocks = new GridView[BLOCKS_NUMBER];
        GridView blockView;
        for (int index = 0; index<BLOCKS_NUMBER; index++){
            String[] data = takeSquare(index);
            adapter = new ArrayAdapter<>(this, R.layout.field, R.id.sell, data);
            blockView = new GridView(this);
            blockView.setAdapter(adapter);
            blocks[index] = blockView;
        }
    }
    private void initGrid(){
        ArrayAdapter<String> gridAdapter = new ArrayAdapter<>(this, R.layout.field, R.id.sell, takeNumbers() );
        gvMain = findViewById(R.id.sudoku_field);
        gvMain.setAdapter(gridAdapter);
    }
    private void adjustGridView() {

    }
}
