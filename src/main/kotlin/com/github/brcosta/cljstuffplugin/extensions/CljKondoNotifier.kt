package com.github.brcosta.cljstuffplugin.extensions

import com.github.brcosta.cljstuffplugin.actions.AnalyzeClasspathAction
import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity

class CljKondoNotifier : StartupActivity {
    override fun runActivity(project: Project) {
        val notification = Notification(
            "ProjectOpenNotification",
            "Clj-kondo: Analyze project classpath",
            "Analyze classpath on ${project.name} for better results",
            NotificationType.INFORMATION
        )

        val action = AnalyzeClasspathAction()
        action.templatePresentation.text = "Analyze Classpath"
        action.notification = notification

        notification.addAction(action)
        notification.notify(project)
    }
}
