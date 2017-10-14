package lidwin.prioritize;

/**
 * Created by Adalbert Gerald on 02-Oct-17.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by prasathth on 8/2/17.
 */

public class priorityActivity extends Activity {

    LinearLayout mLayout;
    EditText mEditText;
    Button mButton;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mLayout =  new LinearLayout(this);
        mEditText = new EditText(this);
        mButton = new Button(this);
        mButton.setText("Add Text");
        mButton.setOnClickListener(onClick());
        TextView textView = new TextView(this);
        textView.setText("New text");


        mLayout.addView(mEditText);

        mLayout.addView(mButton);

        setContentView(mLayout);


    }


    private View.OnClickListener onClick() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mLayout.addView(createNewTextView(mEditText.getText().toString()));
            }
        };
    }


    private TextView createNewTextView(String text) {

        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        mEditText.setLayoutParams(lparams);


        final TextView textView = new TextView(this);
        //textView.setLayoutParams(lparams);
        textView.setText("New text: " + text);
        return textView;
    }
}



