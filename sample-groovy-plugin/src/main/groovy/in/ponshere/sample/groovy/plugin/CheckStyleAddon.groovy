package in.ponshere.sample.groovy.plugin

import com.puppycrawl.tools.checkstyle.ant.CheckstyleAntTask
import org.gradle.api.Project

import java.util.jar.JarEntry
import java.util.jar.JarFile

/**
 * Created by root on 27/3/17.
 */
class CheckStyleAddon {

    protected void performCheck(Project project){
        if(!new File(project.buildDir, "outputs/checkstyle-reports.xml").exists()){
            File f = new File(project.buildDir, "outputs/checkstyle-reports.xml");
            f.parentFile.mkdirs()
            f.createNewFile();
        }
        performCheck(project,
                getAndroidSources(project),
                resolveConfigFile(project),
                new File(project.buildDir, "outputs/checkstyle-reports.xml"))
    }

    protected void performCheck(Project project, List<File> sources,
                                File configFile, File xmlReportFile) {
        project.task(
                [group      : 'verification',
                 description: 'Does checkstyle'],
                'checkstyle') << {
            CheckstyleAntTask checkStyleTask = new CheckstyleAntTask()

            checkStyleTask.project = project.ant.antProject
            checkStyleTask.configURL = configFile.toURI().toURL()
            checkStyleTask.addFormatter(new CheckstyleAntTask.Formatter(type: new CheckstyleAntTask.FormatterType(value: 'xml'), tofile: xmlReportFile))
            reformatReport(project, resolveStyleFile(project), xmlReportFile, new File(project.buildDir, "outputs/checkstyle-reports.html"))
            checkStyleTask.failOnViolation = true

            sources.findAll { it.exists() }.each {
                checkStyleTask.addFileset(project.ant.fileset(dir: it))
            }

            checkStyleTask.perform()
        }

    }

    List<File> getAndroidSources(Project project) {
        project.android.sourceSets.inject([]) {
            dirs, sourceSet -> dirs + sourceSet.java.srcDirs
        }
    }

    File resolveConfigFile(Project project) {
        File file = new File(project.buildDir, "tmp/android-check/checkstyle.xml")
        file.parentFile.mkdirs()
        file.delete()
        file << getResource(project,"checkstyle/checkstyle-easy.xml")
        return file
    }

    protected void reformatReport(Project project, File styleFile,
                                  File xmlReportFile, File htmlReportFile) {
        project.ant.xslt(in: xmlReportFile, out: htmlReportFile) {
            style { string(styleFile.text) }
        }
    }


    String getResource(Project project, String resourcePath) {
        Set<File> files = new HashSet<>()
        files += project.buildscript.configurations.classpath.resolve()
        files += project.rootProject.buildscript.configurations.classpath.resolve()
        File file = files.find { new JarFile(it).getJarEntry(resourcePath) }
        if (file == null) {
            return null
        } else {
            JarFile jarFile = new JarFile(file)
            JarEntry jarEntry = jarFile.getJarEntry(resourcePath)
            return jarFile.getInputStream(jarEntry).text
        }
    }

    File resolveStyleFile(Project project) {
        File file = new File(project.buildDir, "tmp/android-check/checkstyle.xsl")
        file.parentFile.mkdirs()
        file.delete()
        file << getResource(project, "checkstyle/checkstyle.xsl")
        return file
    }
}
