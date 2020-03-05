package luj.groovy.build.internal.maven

import luj.groovy.build.api.maven.Maven

import java.nio.file.Path

class MavenProjImpl implements Maven.Project {

  MavenProjImpl(Path mvnPath, Path projPath) {
    _mvnPath = mvnPath
    _projPath = projPath
  }

  @Override
  void phase(String phase) {
    "$_mvnPath -e $phase"
        .execute(null as List, _projPath.toFile())
        .waitForProcessOutput(System.out, System.err)
  }

  private final Path _mvnPath

  private final Path _projPath
}
