package lidwin.prioritize;
/**
 * Created by Adalbert Gerald on 04-Oct-17.
 */
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.widget.Toast;

public class Mote extends BroadcastReceiver{

    AudioManager myAudioManager;

    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        Toast.makeText(context, "Phone Mode Changed To Silent.", Toast.LENGTH_LONG).show();

        myAudioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);

        myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);

    }



}
