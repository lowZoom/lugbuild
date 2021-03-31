package luj.groovy.build.internal.maven

import luj.groovy.build.api.maven.Maven
import luj.groovy.build.api.maven.Plugin
import luj.groovy.build.api.maven.Project

import java.nio.file.Path

class MavenBinImpl implements Maven.Bin {

  MavenBinImpl(Path mvnPath) {
    _mvnPath = mvnPath
  }

  @Override
  Project project(Path path) {
    return new ProjectImpl(_mvnPath, path)
  }

  @Override
  Plugin plugin(String name) {
    def plugin = new PluginImpl()
    plugin._name = name
    plugin._mvnPath = _mvnPath
    return plugin
  }

  private final Path _mvnPath
}
