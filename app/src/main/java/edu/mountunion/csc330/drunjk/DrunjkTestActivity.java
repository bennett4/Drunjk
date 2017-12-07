package edu.mountunion.csc330.drunjk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class DrunjkTestActivity extends AppCompatActivity {

    private static final String TEST = "The quick brown fox jumps over the lazy dog!\n\n\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ////////////////
        RelativeLayout layout = new RelativeLayout( this );
        ScrollView scrollView = new ScrollView( this );
        // Set Content View
        TextView tipsView = new TextView(this);
        tipsView.setText(TEST);
        tipsView.setTextSize(15);

        EditText editText = new EditText(this);
        editText.addTextChangedListener(new TextListener);
        //Add the textView to layout
        layout.addView(tipsView);

        layout.addView( editText );

        scrollView.addView( layout );

        setContentView( scrollView );

        ////////////////
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    } // end of method onCreateOptionsMenu


    private class TextListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}

