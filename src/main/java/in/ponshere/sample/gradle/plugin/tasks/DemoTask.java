package in.ponshere.sample.gradle.plugin.tasks;

import in.ponshere.sample.gradle.plugin.extensions.DemoPluginExtension;
import in.ponshere.sample.gradle.plugin.utils.HelloUtils;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

/**
 * A task which can be added to the consumer project.
 */
public class DemoTask extends DefaultTask {
    @TaskAction
    public void greet() {
        DemoPluginExtension extension = getProject().getExtensions().findByType(DemoPluginExtension.class);
        if (extension == null) {
            extension = new DemoPluginExtension();
        }

        String message = extension.getMessage();
        HelloUtils helloUtils = new HelloUtils(message);
        System.out.println(helloUtils.greet());
    }
}