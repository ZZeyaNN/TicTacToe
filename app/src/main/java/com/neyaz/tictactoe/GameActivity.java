package com.neyaz.tictactoe;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    int userTurn = 1; // or user 2
    private String userLogo1 = "X", userLogo2 = "0";
    private Button butto_1, butto_2, butto_3, butto_4, butto_5, butto_6, butto_7, butto_8, butto_9;
    private Button[] buttons;
    private int[] rowsSum;
    private int[] colsSum;
    private int diagSum, rDiagSum, n = 3;
    private int movesCnt = n * n, movesTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        butto_1 = (Button) findViewById(R.id.butto_1);
        butto_1.setOnClickListener(this);
        butto_2 = (Button) findViewById(R.id.butto_2);
        butto_2.setOnClickListener(this);
        butto_3 = (Button) findViewById(R.id.butto_3);
        butto_3.setOnClickListener(this);
        butto_4 = (Button) findViewById(R.id.butto_4);
        butto_4.setOnClickListener(this);
        butto_5 = (Button) findViewById(R.id.butto_5);
        butto_5.setOnClickListener(this);
        butto_6 = (Button) findViewById(R.id.butto_6);
        butto_6.setOnClickListener(this);
        butto_7 = (Button) findViewById(R.id.butto_7);
        butto_7.setOnClickListener(this);
        butto_8 = (Button) findViewById(R.id.butto_8);
        butto_8.setOnClickListener(this);
        butto_9 = (Button) findViewById(R.id.butto_9);
        butto_9.setOnClickListener(this);
        buttons = new Button[]{butto_1, butto_2, butto_3, butto_4, butto_5, butto_6, butto_7, butto_8, butto_9};

        restartGame();
    }

    @Override
    public void onClick(View view) {
        Button clicked = ((Button) view);
        if (clicked.getText() != null && !clicked.getText().toString().isEmpty())
            return;
        movesTotal++;
        int button = Integer.parseInt((String) clicked.getTag()) - 1;
        checkGameEnd((button % n), (button / n));
        if (userTurn == 1) {
            clicked.setText(userLogo1);
            userTurn = 2;
        } else {
            userTurn = 1;
            clicked.setText(userLogo2);
        }
    }

    private void checkGameEnd(int x, int y) {
        int delta;
        if (userTurn == 1) {
            delta = -1;
        } else {
            delta = 1;
        }
        colsSum[x] += delta;
        rowsSum[y] += delta;
        if (x == y)
            diagSum += delta;
        if (x == (n - y - 1))
            rDiagSum += delta;

        if (colsSum[x] == n  || rowsSum[y] == n || diagSum == n || rDiagSum  == n
                || colsSum[x] == -1 * n  || rowsSum[y] == -1 * n || diagSum == -1 * n || rDiagSum == -1 * n)
            showWinnerDialog();
        else if (movesCnt == movesTotal)
            showDrawDialog();
    }

    private void showDrawDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("The game is a draw");
        alertDialogBuilder.setPositiveButton(getString(R.string.restart),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        restartGame();
                    }
                });
        alertDialogBuilder.create();
        alertDialogBuilder.show();
    }

    private void showWinnerDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        if (userTurn == 1)
            alertDialogBuilder.setTitle("Player 1 won");
        else
            alertDialogBuilder.setTitle("Player 2 won");
        alertDialogBuilder.setPositiveButton(getString(R.string.restart),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        restartGame();
                    }
                });
        alertDialogBuilder.create();
        alertDialogBuilder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                restartGame();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void restartGame() {
        rowsSum = new int[n];
        colsSum = new int[n];
        diagSum = 0;
        rDiagSum = 0;

        userTurn = 1;
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText("");
        }
    }
}
