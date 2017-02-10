package in.ponshere.sample.gradle.plugin;

import in.ponshere.sample.gradle.plugin.extensions.DemoPluginExtension;
import in.ponshere.sample.gradle.plugin.tasks.DemoTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Entry point for the plugin.
 *
 * Configuration phase is triggered by this class.
 *
 * The plugin name and the implementation mapping is done inside the properties file. Check the resources folder.
 *
 * Name of the properties file = plugin name.
 *
 * @author Ponsuyambu V
 */
public class DemoPlugin implements Plugin<org.gradle.api.Project> {
    public void apply(Project project) {
        System.out.println("Plugin Applied");
        project.getExtensions().create("demoSetting", DemoPluginExtension.class);
        project.getTasks().create("demo", DemoTask.class);
    }
}
