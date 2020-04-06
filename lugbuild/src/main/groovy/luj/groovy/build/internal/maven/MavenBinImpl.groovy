package luj.groovy.build.internal.maven

import luj.groovy.build.api.maven.Maven
import luj.groovy.build.api.maven.Project

import java.nio.file.Path

class MavenBinImpl implements Maven.Bin {

  MavenBinImpl(Path mvnPath) {
    _mvnPath = mvnPath
  }

  @Override
  Project project(Path path) {
    return new MavenProjImpl(_mvnPath, path)
  }

  private final Path _mvnPath
}
