package luj.groovy.build.api

import luj.groovy.build.api.maven.Maven
import luj.groovy.build.internal.maven.prepare.MavenPrepareImpl

enum LugBuild {

  static Maven.Preparer maven() {
    return new MavenPrepareImpl()
  }
}
