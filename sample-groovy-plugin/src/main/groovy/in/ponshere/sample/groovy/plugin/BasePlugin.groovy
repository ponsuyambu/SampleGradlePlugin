package in.ponshere.sample.groovy.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by root on 1/4/17.
 */
class BasePlugin implements Plugin<Project>{

    protected String mPluginId = "";

    public BasePlugin(String pluginId){
        mPluginId = pluginId;
    }

    def mProductFlavorsConfigBlock = {
        android{
            productFlavors {
                demo {

                }
                full {

                }
                partial {

                }
            }
        }

    }
    def mAndroidAppConfigBlock = {
        android{
            compileSdkVersion 25
            buildToolsVersion VersionSettings.BUILD_TOOLS_VERSION
        }
    }
    def mDefaultConfigBlock = {
        android {
            defaultConfig {
                applicationId "in.ponshere.progaurdforlibrary"
                minSdkVersion 14
                targetSdkVersion 25
                versionCode 1
                versionName "1.0"
                testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
            }
        }
    }
    def mRepoConfigBlock = {
        repositories {
            jcenter()
        }
    }
    def mDependenciesConfigBlock = {
        android {
            dependencies {
                compile fileTree(dir: 'libs', include: ['*.jar'])
                testCompile 'junit:junit:4.12'
                compile 'com.android.support:appcompat-v7:25.1.1'
            }
        }
    }

    protected void configure(Project project){

    }

    @Override
    void apply(Project project) {
        project.configure(project) {
            apply plugin: mPluginId
            mProductFlavorsConfigBlock.delegate = project;
            mRepoConfigBlock.delegate = project;
            mDependenciesConfigBlock.delegate = project;
            mDefaultConfigBlock.delegate = project;
            mAndroidAppConfigBlock.delegate = project;




            mRepoConfigBlock();
            mDependenciesConfigBlock();

            configure(project);
            new CheckStyleAddon().performCheck(project)
            //preBuild.dependsOn checkstyle
        }
    }


}
