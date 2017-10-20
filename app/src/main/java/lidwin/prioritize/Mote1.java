package lidwin.prioritize;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.widget.Toast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.widget.Toast;

/**
 * Created by Adalbert Gerald on 11-Oct-17.
 */
public class Mote1 extends BroadcastReceiver {
    AudioManager myAudioManager;
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        Toast.makeText(context, "Phone Mode Changed To Normal.", Toast.LENGTH_LONG).show();

        myAudioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);

        myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

    }


}
