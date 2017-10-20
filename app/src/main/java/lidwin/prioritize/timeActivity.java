package lidwin.prioritize;

/**
 * Created by Adalbert Gerald on 02-Oct-17.
 */
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by prasathth on 8/2/17.
 */

public class timeActivity extends Activity {

    TextView time1,time2;
    //TimePicker b;
    LinearLayout mLayout;


    Button call1,call2,a;

    TextView time3,time4;
    //TimePicker b;
    //LinearLayout mLayout;


    Button call3,call4,b;


    private DBManager dbManager;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        dbManager = new DBManager(this);


        dbManager.open();


        mLayout =  new LinearLayout(this);


        mLayout.setOrientation(LinearLayout.VERTICAL);

        time1 = new TextView(this);
        time2 = new TextView(this);
        time1.setText("Start-Time:");

        time2.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        time2.setText("End-Time:");

        call1 = new Button(this);

        call1.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        call2 = new Button(this);

        call2.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));


        try {



            Cursor cursor = dbManager.fetch();
            cursor.moveToFirst();

            String a1 = cursor.getString(1);

            String a2 = cursor.getString(2);

            call1.setText(a1);
            call2.setText(a2);
        }
        catch (NullPointerException e){

            Toast.makeText(getApplicationContext(),"Null",Toast.LENGTH_SHORT).show();

        }

        catch (CursorIndexOutOfBoundsException e){
            Toast.makeText(getApplicationContext(),"New Install",Toast.LENGTH_SHORT).show();

            dbManager.insert("00:00", "00:00");

        }

 //       Toast.makeText(this.getApplicationContext(),username+"&"+password,Toast.LENGTH_SHORT);


        call1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                showTimePicker(view);

            }
        });


        call2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                showTimePicker(view);

            }
        });

        mLayout.addView(time1);

        mLayout.addView(call1);

        mLayout.addView(time2);

        mLayout.addView(call2);


        a = new Button(this);

        a.setText("Save");

        a.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        mLayout.addView(a);





        a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                String t1 = call1.getText().toString();

                String t2 = call2.getText().toString();


                //dbManager.insert(t1, t2);

                String a[] = t1.split(":");
                String hours = a[0];
                String minutes = a[1];

                int hr = Integer.parseInt(hours);

                int min = Integer.parseInt(minutes);


                String b[] = t2.split(":");
                String hours1 = b[0];
                String minutes1 = b[1];

                int hr1 = Integer.parseInt(hours1);

                int min1 = Integer.parseInt(minutes1);

                if (hr > hr1) {

                    //Error

                    Toast.makeText(getApplicationContext(),"Time Conflict hr Occurred",Toast.LENGTH_LONG).show();

                }

                else if (hr == hr1 && min >= min1) {

                    //Error
                    Toast.makeText(getApplicationContext(),"Time Conflict min Occurred",Toast.LENGTH_LONG).show();


                } else {


                    dbManager.update(1, t1, t2);

                    Toast.makeText(getApplicationContext(), "Saved Success", Toast.LENGTH_SHORT).show();



                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
                    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
                    cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH));

                    cal.set(Calendar.HOUR_OF_DAY, hr);
                    cal.set(Calendar.MINUTE, min);

                    Intent intent = new Intent(timeActivity.this.getApplicationContext(), Mote.class);

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(timeActivity.this.getApplicationContext(), 1256, intent, PendingIntent.FLAG_ONE_SHOT | Intent.FILL_IN_DATA);


                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                    alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);



                    Calendar cal1 = Calendar.getInstance();

                    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
                    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
                    cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH));

                    cal1.set(Calendar.HOUR_OF_DAY, hr1);
                    cal1.set(Calendar.MINUTE, min1);

                    Intent intent1 = new Intent(timeActivity.this, Mote1.class);
                    PendingIntent pendingIntent1 = PendingIntent.getBroadcast(timeActivity.this.getApplicationContext(), 1258, intent1, PendingIntent.FLAG_ONE_SHOT | Intent.FILL_IN_DATA);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, cal1.getTimeInMillis(), pendingIntent1);


                    try {

                        Cursor cursor = dbManager.fetch();

                        cursor.moveToFirst();

                        String a1 = cursor.getString(1);

                        String a2 = cursor.getString(2);

                        call1.setText(a1);
                        call2.setText(a2);

                        Toast.makeText(getApplicationContext(), a1 + '&' + a2, Toast.LENGTH_LONG).show();


                    } catch (SQLiteException e) {
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    } catch (CursorIndexOutOfBoundsException e) {

                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }


                }
            }
        });



        /*Second timer for a particular gap after this*/

        time3 = new TextView(this);
        time4 = new TextView(this);
        time3.setText("Start-Time:");

        time4.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        time4.setText("End-Time:");

        call3 = new Button(this);

        call3.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        call4 = new Button(this);

        call4.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));


        try {



            Cursor cursor = dbManager.fetch();
            cursor.moveToPosition(1);

            String a1 = cursor.getString(1);

            String a2 = cursor.getString(2);

            call3.setText(a1);
            call4.setText(a2);
        }
        catch (NullPointerException e){

            Toast.makeText(getApplicationContext(),"Null",Toast.LENGTH_SHORT).show();

        }

        catch (CursorIndexOutOfBoundsException e){
        //    Toast.makeText(getApplicationContext(),"New Install",Toast.LENGTH_SHORT).show();

            dbManager.insert("00:00", "00:00");

        }

        //       Toast.makeText(this.getApplicationContext(),username+"&"+password,Toast.LENGTH_SHORT);


        call3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                showTimePicker(view);

            }
        });


        call4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                showTimePicker(view);

            }
        });

        mLayout.addView(time3);

        mLayout.addView(call3);

        mLayout.addView(time4);

        mLayout.addView(call4);


        b = new Button(this);

        b.setText("Save");

        b.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        mLayout.addView(b);


        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                String t1 = call3.getText().toString();

                String t2 = call4.getText().toString();

                String a[] = t1.split(":");
                String hours = a[0];
                String minutes = a[1];

                int hr = Integer.parseInt(hours);

                int min = Integer.parseInt(minutes);


                String b[] = t2.split(":");
                String hours1 = b[0];
                String minutes1 = b[1];

                int hr1 = Integer.parseInt(hours1);

                int min1 = Integer.parseInt(minutes1);


                if (hr > hr1) {

                    //Error

                    Toast.makeText(getApplicationContext(), "Time Conflict hr Occurred", Toast.LENGTH_LONG).show();

                } else if (hr == hr1 && min >= min1) {

                    //Error
                    Toast.makeText(getApplicationContext(), "Time Conflict min Occurred", Toast.LENGTH_LONG).show();


                }

                //dbManager.insert(t1, t2);

                else {


                    dbManager.update(2, t1, t2);

                    Toast.makeText(getApplicationContext(), "Saved Success", Toast.LENGTH_SHORT).show();


                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
                    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
                    cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH));

                    cal.set(Calendar.HOUR_OF_DAY, hr);
                    cal.set(Calendar.MINUTE, min);

                    Intent intent = new Intent(timeActivity.this.getApplicationContext(), Mote.class);

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(timeActivity.this.getApplicationContext(), 1260, intent, PendingIntent.FLAG_UPDATE_CURRENT | Intent.FILL_IN_DATA);


                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                    alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);


                    Calendar cal1 = Calendar.getInstance();

                    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
                    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
                    cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH));

                    cal1.set(Calendar.HOUR_OF_DAY, hr1);
                    cal1.set(Calendar.MINUTE, min1);

                    Intent intent1 = new Intent(timeActivity.this, Mote1.class);
                    PendingIntent pendingIntent1 = PendingIntent.getBroadcast(timeActivity.this.getApplicationContext(), 1263, intent1, PendingIntent.FLAG_UPDATE_CURRENT | Intent.FILL_IN_DATA);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, cal1.getTimeInMillis(), pendingIntent1);


                    try {

                        Cursor cursor = dbManager.fetch();

                        cursor.moveToPosition(1);

                        String a1 = cursor.getString(1);

                        String a2 = cursor.getString(2);

                        call3.setText(a1);
                        call4.setText(a2);

                        Toast.makeText(getApplicationContext(), a1 + '&' + a2, Toast.LENGTH_LONG).show();


                    } catch (SQLiteException e) {
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    } catch (CursorIndexOutOfBoundsException e) {

                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }


                }
            }
        });





        /*Second timer ends here!*/

        setContentView(mLayout);


    }




    public void showTimePicker(View v) {
        DialogFragment newFragment = new TimePickerFragment((TextView)v);
        newFragment.show(getFragmentManager(), "timePicker");
    }

    public class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        TextView mResultText;

        public TimePickerFragment(TextView textView) {
            mResultText = textView;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String time = hourOfDay+":"+minute;/*CONVERT YOUR TIME FROM hourOfDay and minute*/;
            mResultText.setText(time);
        }
    }



}
