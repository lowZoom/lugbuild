package luj.groovy.build.api

import luj.groovy.build.api.docker.Docker
import luj.groovy.build.api.maven.Maven
import luj.groovy.build.internal.docker.DockerImpl
import luj.groovy.build.internal.maven.prepare.MavenPrepareImpl

enum LugBuild {

  static Maven maven() {
    return new MavenPrepareImpl()
  }

  static Docker docker() {
    return new DockerImpl()
  }
}
