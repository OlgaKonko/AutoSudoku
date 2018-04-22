package koshon.com.autosudoku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import koshon.com.autosudoku.adapter.model.SudokuSolutions;
import koshon.com.autosudoku.sudoku_generator.model.Sudoku;

import static koshon.com.autosudoku.adapter.model.CurrentSudoku.createNewSudoku;
import static koshon.com.autosudoku.sudoku_generator.SudokuGenerator.generateSudoku;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNewSudoku();
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
