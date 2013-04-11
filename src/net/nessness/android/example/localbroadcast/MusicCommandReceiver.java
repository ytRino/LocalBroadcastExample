
package net.nessness.android.example.localbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MusicCommandReceiver extends BroadcastReceiver {

    public interface MusicCommandCallback {

        void onCommand(int command);
    }

    @SuppressWarnings("unused")
    private static final String TAG = MusicCommandReceiver.class.getSimpleName();

    @SuppressWarnings("unused")
    private final MusicCommandReceiver self = this;

    public static final String ACTION_COMMAND = "action_command";

    public static final String EXTRA_COMMAND = "command";
    public static final int COM_PLAY = 0;
    public static final int COM_STOP = 1;

    private final MusicCommandCallback mCallback;

    public MusicCommandReceiver(MusicCommandCallback c) {
        mCallback = c;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final int command = intent.getIntExtra(EXTRA_COMMAND, COM_STOP);
        mCallback.onCommand(command);
    }
}
