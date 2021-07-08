package luj.groovy.build.internal.maven.prepare

import luj.groovy.build.api.maven.Maven
import luj.groovy.build.internal.maven.MavenBinImpl

import java.nio.file.Path

class MavenPrepareImpl implements Maven {

  @Override
  Maven version(String version) {
    return this
  }

  @Override
  Maven locate(Path startPath) {
    _locatePath = startPath
    return this
  }

  @Override
  Maven downloadIfAbsent() {
    return this
  }

  @Override
  Maven.Bin bin() {
    def mvn = new MavenInstallationLocator(_locatePath, '3.5.4').locateV2()
    return new MavenBinImpl(mvn)
  }

  private Path _locatePath
}
