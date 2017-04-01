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

    def mProductFlavorsConfigBlock;
    def mAndroidAppConfigBlock;
    def mDefaultConfigBlock;

    protected void configure(Project project){

    }

    @Override
    void apply(Project project) {
        project.configure(project) {
            apply plugin: mPluginId
            mProductFlavorsConfigBlock = {
                android{
                    productFlavors {
                        demo {

                        }
                        full {

                        }
                    }
                }

            }
            mAndroidAppConfigBlock = {
                android{
                    compileSdkVersion 25
                    buildToolsVersion VersionSettings.BUILD_TOOLS_VERSION
                }
            }
            mDefaultConfigBlock = {
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

            configure(project);
            new CheckStyleAddon().performCheck(project)
            preBuild.dependsOn checkstyle
        }
    }


}
