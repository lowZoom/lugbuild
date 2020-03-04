package luj.build.api

import luj.build.api.maven.Maven
import luj.build.internal.maven.prepare.MavenPrepareImpl

enum LugBuild {

  static Maven.Preparer maven() {
    return new MavenPrepareImpl()
  }
}
