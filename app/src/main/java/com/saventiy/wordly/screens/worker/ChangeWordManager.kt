package com.saventiy.wordly.screens.worker

import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ChangeWordManager @Inject constructor(
    private val workManager: WorkManager
) {
    fun execute() = enqueueWorker()

    private fun enqueueWorker() {
        workManager.enqueueUniquePeriodicWork(
            TAG,
            ExistingPeriodicWorkPolicy.KEEP,
            buildRequest
        )
    }

    private val buildRequest: PeriodicWorkRequest =
        PeriodicWorkRequestBuilder<ChangeWordWorker>(1, TimeUnit.HOURS)
            .addTag(TAG)
//            .setConstraints(getDRMConstraints())
            .build()


    companion object {
        private const val TAG = "ChangedWordWorker"

        private fun getDRMConstraints(): Constraints {
            return Constraints.Builder()
//                .setRequiresBatteryNotLow(true)
                .build()
        }
    }
}