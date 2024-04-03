/**
 * Precompiled [convention.android-base.gradle.kts][Convention_android_base_gradle] script plugin.
 *
 * @see Convention_android_base_gradle
 */
public
class Convention_androidBasePlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("Convention_android_base_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
