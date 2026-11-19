package expo.modules.rnhaptics

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition

class RnHapticsModule : Module() {

  private val context: Context
    get() = appContext.reactContext ?: throw IllegalStateException("React context is not available")

  private val vibrator: Vibrator
    get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
      (context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager).defaultVibrator
    } else {
      @Suppress("DEPRECATION")
      context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

  override fun definition() = ModuleDefinition {
    Name("RnHaptics")

    AsyncFunction("triggerHaptic") { style: String ->
      val deviceVibrator = vibrator

      if (deviceVibrator.hasVibrator()) {
        val (duration, amplitude) = when (style.lowercase()) {
          "medium" -> 40L to 150
          "heavy" -> 60L to 255
          else -> 20L to 50
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
          val supportedAmplitude = if (deviceVibrator.hasAmplitudeControl()) {
            amplitude
          } else {
            VibrationEffect.DEFAULT_AMPLITUDE
          }
          deviceVibrator.vibrate(VibrationEffect.createOneShot(duration, supportedAmplitude))
        } else {
          @Suppress("DEPRECATION")
          deviceVibrator.vibrate(duration)
        }
      }
    }
  }
}
