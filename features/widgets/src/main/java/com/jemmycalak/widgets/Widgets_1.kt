package com.jemmycalak.widgets

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.jemmycalak.listrepository.activity.HomeActivity

class Widgets_1 : AppWidgetProvider() {

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        val view = RemoteViews(context?.packageName, R.layout.layout_widget_1)

        val intent = Intent(context, HomeActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        view.setOnClickPendingIntent(R.id.layout, pendingIntent)

        appWidgetManager?.updateAppWidget(appWidgetIds, view)
    }
}