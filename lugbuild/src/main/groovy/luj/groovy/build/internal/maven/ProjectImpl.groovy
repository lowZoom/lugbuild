package luj.groovy.build.internal.maven

import groovy.transform.PackageScope
import luj.groovy.build.api.maven.Plugin
import luj.groovy.build.api.maven.Project

import java.nio.file.Path

@PackageScope
class ProjectImpl implements Project {

  ProjectImpl(List<String> mvnCmd, Path projPath) {
    _mvnCmd = mvnCmd
    _projPath = projPath
  }

  @Override
  int phase(String phase) {
    List<String> phaseCmd = phase.trim().split('\\s+').toList()
    return exec(_mvnCmd + ['-e'] + phaseCmd, System.out)
  }

  @Override
  Plugin plugin(String name) {
    def plugin = new PluginImpl()
    plugin._name = name
    plugin._mvnCmd = _mvnCmd
    plugin._projPath = _projPath
    return plugin
  }

  @Override
  String eval(String expression) {
    def out = new ByteArrayOutputStream()
    exec(_mvnCmd + ['help:evaluate', "-Dexpression=${expression}", '-q', '-DforceStdout'], out)
    return out.toString()
  }

  @Override
  Path path() {
    return _projPath
  }

  private int exec(List cmd, OutputStream out) {
    def builder = new ProcessBuilder(cmd.collect { it.toString() })
    builder.directory(_projPath.toFile())
    builder.environment()['MAVEN_OPTS'] = '-Dfile.encoding=UTF8'

    Process proc = builder.start()
    proc.waitForProcessOutput(out, System.err)
    return proc.exitValue()
  }

  private final List<String> _mvnCmd

  private final Path _projPath
}
