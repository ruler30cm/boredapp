package id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.fragment.TimerFragment
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.util.NotificationUtil
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.util.PrefUtil

class TimerNotificationActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            AppConstants.ACTION_STOP -> {
                TimerFragment.removeAlarm(context)
                PrefUtil.setTimerState(TimerFragment.TimerState.Stopped, context)
                NotificationUtil.hideTimerNotification(context)
            }
            AppConstants.ACTION_PAUSE -> {
                var secondsRemaining = PrefUtil.getSecondsRemaining(context)
                val alarmSetTime = PrefUtil.getAlarmSetTime(context)
                val nowSeconds = TimerFragment.nowSeconds

                secondsRemaining -= nowSeconds - alarmSetTime
                PrefUtil.setSecondsRemaining(context, secondsRemaining)

                TimerFragment.removeAlarm(context)
                PrefUtil.setTimerState(TimerFragment.TimerState.Paused, context)
                NotificationUtil.showTimerPaused(context)
            }
            AppConstants.ACTION_RESUME -> {
                val secondsRemaining = PrefUtil.getSecondsRemaining(context)
                val wakeUpTime = TimerFragment.setAlarm(context, TimerFragment.nowSeconds, secondsRemaining)
                PrefUtil.setTimerState(TimerFragment.TimerState.Running, context)
                NotificationUtil.showTimerRunning(context, wakeUpTime)
            }
            AppConstants.ACTION_START -> {
                val minutesRemaining = PrefUtil.getTimerLength(context)
                val secondsRemaining = minutesRemaining * 60L
                val wakeUpTime = TimerFragment.setAlarm(context, TimerFragment.nowSeconds, secondsRemaining)
                PrefUtil.setTimerState(TimerFragment.TimerState.Running, context)
                PrefUtil.setSecondsRemaining(context, secondsRemaining)
                NotificationUtil.showTimerRunning(context, wakeUpTime)
            }
        }
    }
}