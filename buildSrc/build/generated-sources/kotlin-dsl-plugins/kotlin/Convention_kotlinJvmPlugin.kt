/**
 * Precompiled [convention.kotlin-jvm.gradle.kts][Convention_kotlin_jvm_gradle] script plugin.
 *
 * @see Convention_kotlin_jvm_gradle
 */
public
class Convention_kotlinJvmPlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("Convention_kotlin_jvm_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
