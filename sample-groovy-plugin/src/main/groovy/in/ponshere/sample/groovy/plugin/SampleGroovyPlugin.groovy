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

    @Override
    void apply(Project project) {
        super.apply(project)
    }
//    @Override
//    void apply(Project project) {
//        super.apply(project)
//        project.configure(project) {
//
//
//
//
//
////            project.extensions.configure(AppExtension.class,android)
////            project.extensions.add("android",android);
//
//
//
//            n
//        }
//    }


//    static List<File> getAndroidSources(Project project) {
//        project.android.sourceSets.inject([]) {
//            dirs, sourceSet -> dirs + sourceSet.java.srcDirs
//        }
//    }

//    static String getResource(Project project, String resourcePath) {
//        Set<File> files = new HashSet<>()
//        files += project.buildscript.configurations.classpath.resolve()
//        files += project.rootProject.buildscript.configurations.classpath.resolve()
//        File file = files.find { new JarFile(it).getJarEntry(resourcePath) }
//        if (file == null) {
//            return null
//        } else {
//            JarFile jarFile = new JarFile(file)
//            JarEntry jarEntry = jarFile.getJarEntry(resourcePath)
//            return jarFile.getInputStream(jarEntry).text
//        }
//    }
}