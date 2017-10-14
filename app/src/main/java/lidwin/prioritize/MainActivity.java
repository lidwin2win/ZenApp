package lidwin.prioritize;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;



public class MainActivity extends TabActivity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        Resources ressources = getResources();
        TabHost tabHost = getTabHost();
        //hello bilal

        // Android tab
        Intent intentAndroid = new Intent().setClass(this, timeActivity.class);
        TabHost.TabSpec tabSpecAndroid = tabHost
                .newTabSpec("Android")
                .setIndicator("", ressources.getDrawable(R.drawable.timeicon))
                .setContent(intentAndroid);

        // Apple tab
        Intent intentApple = new Intent().setClass(this, priorityActivity.class);
        TabHost.TabSpec tabSpecApple = tabHost
                .newTabSpec("Apple")
                .setIndicator("", ressources.getDrawable(R.drawable.priorityicon))
                .setContent(intentApple);


        // add all tabs
        tabHost.addTab(tabSpecAndroid);
        tabHost.addTab(tabSpecApple);

        //set Windows tab as default (zero based)
        tabHost.setCurrentTab(2);


    }
}
