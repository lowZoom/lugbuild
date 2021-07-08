package luj.groovy.build.internal.maven

import luj.groovy.build.api.maven.Maven
import luj.groovy.build.api.maven.Plugin
import luj.groovy.build.api.maven.Project

import java.nio.file.Path

class MavenBinImpl implements Maven.Bin {

  MavenBinImpl(List<String> mvnCmd) {
    _mvnCmd = mvnCmd
  }

  @Override
  Project project(Path path) {
    return new ProjectImpl(_mvnCmd, path)
  }

  @Override
  Plugin plugin(String name) {
    def plugin = new PluginImpl()
    plugin._name = name
    plugin._mvnCmd = _mvnCmd
    plugin._projPath = null
    return plugin
  }

  private final List<String> _mvnCmd
}
