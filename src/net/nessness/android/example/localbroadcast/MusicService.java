
package net.nessness.android.example.localbroadcast;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

import net.nessness.android.example.localbroadcast.MusicCommandReceiver.MusicCommandCallback;

public class MusicService extends Service {

    @SuppressWarnings("unused")
    private static final String TAG = MusicService.class.getSimpleName();

    private final MusicService self = this;

    private BroadcastReceiver mReceiver;
    
    private MediaPlayer mPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        registerLoacalBroadcastReceiver();
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        unregisterLocalBroadcastReceiver();

        super.onUnbind(intent);
        return false;
    }

    private void registerLoacalBroadcastReceiver() {
        mReceiver = new MusicCommandReceiver(new MusicCommandCallback() {
            @Override
            public void onCommand(int command) {
                switch (command) {
                case MusicCommandReceiver.COM_PLAY:
                    onReceivePlayCommand();
                    break;
                case MusicCommandReceiver.COM_STOP:
                    onReceiveStopCommand();
                }
            }
        });

        LocalBroadcastManager.getInstance(self).registerReceiver(mReceiver,
                new IntentFilter(MusicCommandReceiver.ACTION_COMMAND));
    }

    private void unregisterLocalBroadcastReceiver() {
        LocalBroadcastManager.getInstance(self).unregisterReceiver(mReceiver);
    }

    private void onReceivePlayCommand() {
        if(mPlayer != null && mPlayer.isPlaying()){
            mPlayer.stop();
            mPlayer.release();
        }
        mPlayer = MediaPlayer.create(self, R.raw.music);
        mPlayer.start();
        mPlayer.setLooping(true);
    }

    private void onReceiveStopCommand() {
        if(mPlayer != null && mPlayer.isPlaying()){
            mPlayer.stop();
            mPlayer.release();
        }
    }
}
