package com.saventiy.wordly.screens.worker

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.saventiy.wordly.MainActivity
import com.saventiy.wordly.R
import com.saventiy.wordly.domain.usecase.local.GetAllCollectionsUseCase
import com.saventiy.wordly.screens.widget.WordWidgetReceiver.Companion.updateWidget
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@HiltWorker
internal class ChangeWordWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val params: WorkerParameters,
    private val getAllCollectionsUseCase: GetAllCollectionsUseCase
) : CoroutineWorker(context, params) {

    //Could be withContext(IO)
    override suspend fun doWork(): Result =
        try {
            getAllCollectionsUseCase.invoke()
                .onEach { collections ->
                    val word = collections.find { it.isActive }?.collection?.random()
                    updateWidget(word.toString(), context)
                    sendNotification(word = word)
                }
                .collect()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }


    //TODO should I moved it?
    @SuppressLint("MissingPermission")
    private fun sendNotification(word: String?) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(context, "wordly")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("wordly")
            .setContentText(word.toString())
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(1, builder.build())
    }
}
