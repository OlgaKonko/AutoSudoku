package koshon.com.autosudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static koshon.com.autosudoku.adapter.model.CurrentSudoku.createNewSudoku;
import static koshon.com.autosudoku.field.constants.Options.usePen;

public class WaitingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        CreateSudokuThread createSudokuThread = new CreateSudokuThread();
        createSudokuThread.execute();
    }

    public void createNewGame(){
        boolean usePenOption = usePen;
        usePen = true;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        usePen = usePenOption;
        finish();
    }

    public class CreateSudokuThread extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            createNewSudoku();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
           createNewGame();
        }
    }
}
