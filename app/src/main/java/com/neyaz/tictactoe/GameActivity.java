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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        butto_1 =  (Button) findViewById(R.id.butto_1);
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
        buttons = new Button[] { butto_1, butto_2 , butto_3 , butto_4 , butto_5 , butto_6 , butto_7 , butto_8 , butto_9 };
    }

    @Override
    public void onClick(View view) {
        Button clicked = ((Button) view);
        if (clicked.getText() !=null && !clicked.getText().toString().isEmpty())
            return;
        if (userTurn == 1) {
            clicked.setText(userLogo1);
            userTurn = 2;
        } else {
            userTurn = 1;
            clicked.setText(userLogo2);
        }
        checkGameEnd();
    }

    private void checkGameEnd() {
        // 123, 456, 789
        // 147, 258, 369
        // 159, 357
        int winner = -1;
        winner = isSame(butto_1.getText().toString(), butto_2.getText().toString(), butto_3.getText().toString());
        if (winner == -1) {
            winner = isSame(butto_4.getText().toString(), butto_5.getText().toString(), butto_6.getText().toString());
        }
        if (winner == -1) {
            winner = isSame(butto_7.getText().toString(), butto_8.getText().toString(), butto_9.getText().toString());
        }
        if (winner == -1) {
            winner = isSame(butto_1.getText().toString(), butto_4.getText().toString(), butto_7.getText().toString());
        }
        if (winner == -1) {
            winner = isSame(butto_2.getText().toString(), butto_5.getText().toString(), butto_8.getText().toString());
        }
        if (winner == -1) {
            winner = isSame(butto_3.getText().toString(), butto_6.getText().toString(), butto_9.getText().toString());
        }

        if (winner == -1) {
            winner = isSame(butto_1.getText().toString(), butto_5.getText().toString(), butto_9.getText().toString());
        }
        if (winner == -1) {
            winner = isSame(butto_3.getText().toString(), butto_5.getText().toString(), butto_7.getText().toString());
        }

        if (winner != -1) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            if (winner == 1)
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
        } else {
            int checkedCount = 0;
            for (int i=0; i<buttons.length; i++) {
                if (!(buttons[i].getText() !=null && buttons[i].getText().toString().isEmpty()))
                    checkedCount++;
            }
            if (checkedCount > 8) {
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
        }
    }

    private int isSame(String a, String b, String c) {
        if (a.isEmpty() || b.isEmpty() || c.isEmpty())
            return -1;
        if (a.equals(b) && b.equals(c)) {
            if (a.equals("X"))
                return 1;
            else
                return 2;
        } else
            return -1;
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
        for (int i=0; i< buttons.length; i++) {
            buttons[i].setText("");
        }
    }
}
