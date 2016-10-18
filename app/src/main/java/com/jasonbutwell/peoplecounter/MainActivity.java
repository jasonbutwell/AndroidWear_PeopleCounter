package com.jasonbutwell.peoplecounter;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    // Class variables

    private TextView mTextView;
    private Button resetButton, countButton;

    private final int defaultTextSize = 100;        // The start size in SP of the counter text
    private final int defaultCharSize = 8;          // The amount decrement by when we have additional characters in the counter
    private int actualTextSize;

    private int counter;

    // Updates the counter view and sets it's text size

    private void updateCounterView() {
        mTextView.setText(Integer.toString(counter));
        setTextSize();
    }

    // Initialise the views and set the counter view

    private void initialistViews() {
        mTextView = (TextView) findViewById(R.id.counterTextView);
        countButton = (Button) findViewById(R.id.buttonCount);
        resetButton = (Button) findViewById(R.id.buttonReset);

        actualTextSize = defaultTextSize;
        updateCounterView();
    }

    // Sets the text size of the counter view based upon the length of the counter
    // so that it adapts when the counter gets larger.

    private void setTextSize() {
        actualTextSize = defaultTextSize - (( Integer.toString(counter).length()-1 ) * defaultCharSize);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, actualTextSize);
    }

    // Sets up the click listeners for both the reset and the count buttons and what to do when the buttons are clicked

    private void initialistButtons() {
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = 0;                            // reset counter
                updateCounterView();
            }
        });

        countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;                              // +1 to counter
                updateCounterView();
            }
        });
    }

    // Uses onCreate like native Android apps do

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {

                counter = 0;            // start counter at 0

                initialistViews();      // set up our views
                initialistButtons();    // set up the button behaviours
            }
        });
    }
}
