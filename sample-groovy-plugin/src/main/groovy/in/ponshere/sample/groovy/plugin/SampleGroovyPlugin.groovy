package in.ponshere.sample.groovy.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class SampleGroovyPlugin extends BasePlugin implements Plugin<Project> {

    public SampleGroovyPlugin() {
        super("com.android.application")
    }

    @Override
    protected void configure(Project project) {
        super.configure(project)
        mAndroidAppConfigBlock.call();
        mDefaultConfigBlock();
        mProductFlavorsConfigBlock.call();
    }
}