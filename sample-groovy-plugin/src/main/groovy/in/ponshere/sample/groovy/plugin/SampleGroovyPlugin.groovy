package in.ponshere.sample.groovy.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project;

class SampleGroovyPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.configure(project) {
            apply plugin: 'com.android.application'
            android {
                compileSdkVersion 25
                buildToolsVersion "25.0.1"
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
        }
    }
}