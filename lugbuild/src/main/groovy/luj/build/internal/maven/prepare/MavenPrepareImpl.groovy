package luj.build.internal.maven.prepare

import luj.build.api.maven.Maven
import luj.build.internal.maven.MavenBinImpl

import java.nio.file.Path

class MavenPrepareImpl implements Maven.Preparer {

  @Override
  Maven.Preparer version(String version) {
    return this
  }

  @Override
  Maven.Preparer locate(Path startPath) {
    _locatePath = startPath
    return this
  }

  @Override
  Maven.Preparer downloadIfAbsent() {
    return this
  }

  @Override
  Maven.Bin bin() {
    Path binPath = new MavenInstallationLocator(_locatePath, '3.5.4').locate()
    return new MavenBinImpl(binPath)
  }

  private Path _locatePath
}
