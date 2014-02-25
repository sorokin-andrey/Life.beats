package com.quickblox.lifebeats.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 14.02.14
 * Time: 14:22
 */
public class AlarmPopup extends Activity {

    private MediaPlayer mPlayer;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.alarmpopup);
        displayAlert();
    }

    private void displayAlert() {
        try {
            Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            mPlayer = new MediaPlayer();
            mPlayer.setDataSource(this, soundUri);
            final AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
            if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
                mPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                mPlayer.setLooping(false);
                mPlayer.prepare();
                mPlayer.start();
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Reminder. You should take your pills. Did you already take it?").setCancelable(
                    false).setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            mPlayer.stop();
                            dialog.cancel();
                        }
                    }).setNegativeButton("No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            mPlayer.stop();
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        } catch (IllegalArgumentException e) {
        } catch (SecurityException e) {
        } catch (IllegalStateException e) {
        } catch (IOException e) {
        } catch (Exception e) {
        }
    }
}