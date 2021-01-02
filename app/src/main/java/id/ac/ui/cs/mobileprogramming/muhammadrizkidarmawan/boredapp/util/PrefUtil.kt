package id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.util

import android.content.Context
import android.preference.PreferenceManager
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.fragment.TimerFragment

class PrefUtil {

    companion object {

        private const val TIMER_LENGTH_ID = "id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.timer_length"
        fun getTimerLength(context : Context) : Int {
            // placeholder
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getInt(TIMER_LENGTH_ID, 5)
        }

        private const val PREVIOUS_TIMER_LENGTH_SECONDS_ID = "id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.previous_timer_length"

        fun getPreviousTimerLengthSeconds(context: Context) : Long {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, 0)
        }

        fun setPreviousTimerLengthSeconds(context: Context, seconds: Long) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, seconds)
            editor.apply()
        }

        private const val TIMER_STATE_ID = "id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.timer_state"

        fun getTimerState(context: Context): TimerFragment.TimerState{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val ordinal = preferences.getInt(TIMER_STATE_ID, 0)
            return TimerFragment.TimerState.values()[ordinal]
        }

        fun setTimerState(state: TimerFragment.TimerState, context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            val ordinal = state.ordinal
            editor.putInt(TIMER_STATE_ID, ordinal)
            editor.apply()
        }

        private const val SECONDS_REMAINING_ID = "id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.seconds_remaining"

        fun getSecondsRemaining(context: Context) : Long {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(SECONDS_REMAINING_ID, 0)
        }

        fun setSecondsRemaining(context: Context, seconds: Long) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(SECONDS_REMAINING_ID, seconds)
            editor.apply()
        }

        private const val ALARM_SET_TIME_ID = "id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.backgrounded_time"

        fun getAlarmSetTime(context: Context) : Long {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(ALARM_SET_TIME_ID, 0)
        }

        fun setAlarmSetTime(time: Long, context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(ALARM_SET_TIME_ID, time)
            editor.apply()
        }
    }

}