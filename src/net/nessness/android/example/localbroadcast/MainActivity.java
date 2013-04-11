
package net.nessness.android.example.localbroadcast;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private final MainActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.player_play)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalBroadcastManager.getInstance(self).sendBroadcast(new Intent(MusicCommandReceiver.ACTION_COMMAND).putExtra(
                        MusicCommandReceiver.EXTRA_COMMAND, MusicCommandReceiver.COM_PLAY));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        bindService(new Intent().setClass(self, MusicService.class), new ServiceConnection() {
            @Override
            public void onServiceDisconnected(ComponentName name) {

            }

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

            }
        }, BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
