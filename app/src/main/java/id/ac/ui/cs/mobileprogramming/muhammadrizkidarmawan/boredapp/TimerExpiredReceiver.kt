package id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.fragment.TimerFragment
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.util.NotificationUtil
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.util.PrefUtil

class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        NotificationUtil.showTimerExpired(context)

        PrefUtil.setTimerState(TimerFragment.TimerState.Stopped, context)
        PrefUtil.setAlarmSetTime(0, context)

    }
}