package com.saventiy.wordly.screens.widget

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.saventiy.wordly.R
import com.saventiy.wordly.ui.theme.Alto

object WordWidget : GlanceAppWidget() {

    override val stateDefinition = PreferencesGlanceStateDefinition

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            WidgetContent()
        }
    }

    @Composable
    private fun WidgetContent() {
        val state = currentState<Preferences>()
        val word = state[stringPreferencesKey("word")]
        Column(
            modifier = GlanceModifier.fillMaxSize(),
            verticalAlignment = Alignment.Vertical.CenterVertically,
            horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
        ) {
            if (word.isNullOrBlank()) {
                Text(
                    text = stringResource(id = R.string.you_aint_add),
                    style = TextStyle(
                        color = ColorProvider(color = Color.Black),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            } else {
                Text(
                    text = word.toString(),
                    style = TextStyle(
                        color = ColorProvider(color = Color.White),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

class WordWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = WordWidget

    companion object {
        @SuppressLint("SuspiciousIndentation")
        suspend fun updateWidget(word: String, context: Context) {
            GlanceAppWidgetManager(context = context).getGlanceIds(provider = WordWidget::class.java)
                .forEach { glanceId ->
                    updateAppWidgetState(context, glanceId) { prefs ->
                        prefs[stringPreferencesKey("word")] = word
                    }
                    WordWidget.update(context, glanceId)
                }
        }
    }
}

