package id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.fragment

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.preference.PreferenceFragment
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.R
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.TimerExpiredReceiver
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.util.NotificationUtil
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.util.PrefUtil
import me.zhanghai.android.materialprogressbar.MaterialProgressBar
import java.time.Duration
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TimerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TimerFragment : Fragment() {
    // TODO: Rename and change types of parameters

    enum class TimerState {
        Stopped, Paused, Running
    }

    companion object {
        fun setAlarm(context: Context, nowSeconds: Long, secondsRemaining: Long): Long {
            val wakeUpTime = (nowSeconds + secondsRemaining) * 1000
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, TimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, wakeUpTime, pendingIntent)
            PrefUtil.setAlarmSetTime(nowSeconds, context)
            return wakeUpTime
        }

        fun removeAlarm(context: Context) {
            val intent = Intent(context, TimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(pendingIntent)
            PrefUtil.setAlarmSetTime(0, context)
        }

        val nowSeconds: Long get() = Calendar.getInstance().timeInMillis / 100
    }

    private var timer: CountDownTimer? = null
    private var timerLengthSeconds = 0L
    private var timerState = TimerState.Stopped

    private var secondsRemaining = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_timer, container, false)

        val startButton = view.findViewById<Button>(R.id.start_button)
        val pauseButton = view.findViewById<Button>(R.id.pause_button)
        val stopButton = view.findViewById<Button>(R.id.stop_button)
        val timerButton = view.findViewById<Button>(R.id.timer_length_button)
        val timerLength = view.findViewById<EditText>(R.id.timer_length)

        startButton.setOnClickListener {
            startTimer()
            timerState = TimerState.Running
            updateButtons()
        }

        pauseButton.setOnClickListener {
            timer?.cancel()
            timerState = TimerState.Paused
            updateButtons()
        }

        stopButton.setOnClickListener {
            timer?.cancel()
            onTimerFinished()
        }

        timerButton.setOnClickListener {
            val sharedPref = PreferenceManager.getDefaultSharedPreferences(requireContext())
            val editor : SharedPreferences.Editor = sharedPref.edit()
            editor.putInt("id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.timer_length", timerLength.text.toString().toInt())
            editor.apply()
            Toast.makeText(requireContext(), "Next timer length should be updated", Toast.LENGTH_LONG).show()
        }

        return view
    }

    override fun onResume() {
        super.onResume()

        initTimer()
        // TODO: hide notification
        removeAlarm(requireContext())
        NotificationUtil.hideTimerNotification(requireContext())
    }

    override fun onPause() {
        super.onPause()

        if (timerState == TimerState.Running) {
            timer?.cancel()
            val wakeUpTime = setAlarm(requireContext(), nowSeconds, secondsRemaining)

            NotificationUtil.showTimerRunning(requireContext(), wakeUpTime)
        } else if (timerState == TimerState.Paused) {
            NotificationUtil.showTimerPaused(requireContext())
        }

        PrefUtil.setPreviousTimerLengthSeconds(requireContext(), timerLengthSeconds)
        PrefUtil.setSecondsRemaining(requireContext(), secondsRemaining)
        PrefUtil.setTimerState(timerState, requireContext())
    }

    private fun initTimer() {
        timerState = PrefUtil.getTimerState(requireContext())
        if (timerState == TimerState.Stopped) {
            setNewTimerLength()
        } else {
            setPreviousTimerLength()
        }
        secondsRemaining = if (timerState == TimerState.Running || timerState == TimerState.Paused)
            PrefUtil.getSecondsRemaining(requireContext())
        else
            timerLengthSeconds

        val alarmSetTime = PrefUtil.getAlarmSetTime(requireContext())
        if (alarmSetTime > 0) secondsRemaining -= nowSeconds - alarmSetTime

        if (secondsRemaining <= 0) onTimerFinished() else if (timerState == TimerState.Running)
            startTimer()

        updateButtons()
        updateCountdownUI()
    }

    private fun onTimerFinished() {
        val progress_countdown = view?.findViewById<MaterialProgressBar>(R.id.progress_countdown)
        timerState = TimerState.Stopped

        setNewTimerLength()

        progress_countdown?.progress = 0

        PrefUtil.setSecondsRemaining(requireContext(), timerLengthSeconds)
        secondsRemaining = timerLengthSeconds

        updateButtons()
        updateCountdownUI()
    }

    private fun startTimer() {
        timerState = TimerState.Running

        timer = object : CountDownTimer(secondsRemaining * 1000, 1000) {
            override fun onFinish() = onTimerFinished()

            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000
                updateCountdownUI()
            }
        }.start()
    }

    private fun setNewTimerLength(){
        val progress_countdown = view?.findViewById<MaterialProgressBar>(R.id.progress_countdown)
        val lengthInMinutes = PrefUtil.getTimerLength(requireContext())
        timerLengthSeconds = (lengthInMinutes * 60L)
        progress_countdown?.max = timerLengthSeconds.toInt()
    }

    private fun setPreviousTimerLength() {
        val progress_countdown = view?.findViewById<MaterialProgressBar>(R.id.progress_countdown)
        timerLengthSeconds = PrefUtil.getPreviousTimerLengthSeconds(requireContext())
        progress_countdown?.max = timerLengthSeconds.toInt()
    }

    private fun updateCountdownUI() {
        val textView_countdown = view?.findViewById<TextView>(R.id.textView_countdown)
        val progress_countdown = view?.findViewById<MaterialProgressBar>(R.id.progress_countdown)
        val minutesUntilFinished = secondsRemaining / 60
        val secondsInMinuteUntilFinished = secondsRemaining - minutesUntilFinished * 60
        val secondsStr = secondsInMinuteUntilFinished.toString()
        textView_countdown?.text = "$minutesUntilFinished:${
            if (secondsStr.length == 2) secondsStr
            else "0" + secondsStr
        }"
        progress_countdown?.progress = (timerLengthSeconds - secondsRemaining).toInt()
    }

    private fun updateButtons() {
        val startButton = view?.findViewById<Button>(R.id.start_button)
        val pauseButton = view?.findViewById<Button>(R.id.pause_button)
        val stopButton = view?.findViewById<Button>(R.id.stop_button)
        when(timerState) {
            TimerState.Running -> {
                startButton?.isEnabled = false
                pauseButton?.isEnabled = true
                stopButton?.isEnabled = true
            }
            TimerState.Stopped -> {
                startButton?.isEnabled = true
                pauseButton?.isEnabled = false
                stopButton?.isEnabled = false
            }
            TimerState.Paused -> {
                startButton?.isEnabled = true
                pauseButton?.isEnabled = false
                stopButton?.isEnabled = true
            }
        }
    }
}