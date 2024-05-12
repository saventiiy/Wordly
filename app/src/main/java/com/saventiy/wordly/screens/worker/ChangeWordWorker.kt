package com.saventiy.wordly.screens.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
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
                }
                .collect()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
}
