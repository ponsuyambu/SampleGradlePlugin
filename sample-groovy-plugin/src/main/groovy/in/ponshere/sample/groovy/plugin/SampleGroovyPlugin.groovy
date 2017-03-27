package in.ponshere.sample.groovy.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

import java.util.jar.JarEntry
import java.util.jar.JarFile;

class SampleGroovyPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.configure(project) {
            apply plugin: 'com.android.application'
            android {
                compileSdkVersion 25
                buildToolsVersion VersionSettings.BUILD_TOOLS_VERSION
                defaultConfig {
                    applicationId "in.ponshere.progaurdforlibrary"
                    minSdkVersion 14
                    targetSdkVersion 25
                    versionCode 1
                    versionName "1.0"
                    testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
                }
                buildTypes {
                    release {
                        minifyEnabled true
                        proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
                    }
                }
                productFlavors {
                    demo {

                    }
                    full {

                    }
                }
            }

            new CheckStyleAddon().performCheck(project)
            preBuild.dependsOn checkstyle
        }
    }


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