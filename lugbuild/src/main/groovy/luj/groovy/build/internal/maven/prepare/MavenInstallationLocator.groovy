package luj.groovy.build.internal.maven.prepare

import groovy.transform.PackageScope
import luj.groovy.build.internal.common.LocatePathWalker

import java.nio.file.Files
import java.nio.file.Path

@PackageScope
class MavenInstallationLocator {

  MavenInstallationLocator(Path startPath, String version) {
    _startPath = startPath
    _version = version
  }

  Path locate() {
    return new LocatePathWalker(_startPath).walk {
      it.parallel()
          .filter { Files.isRegularFile(it) }
          .filter { it.getFileName().toString() == 'mvn.cmd' }
          .map { new Tuple2<>(it, getVersion(it)) }
          .filter { it.second == _version }
          .map { it.first }
          .findAny()
          .orElse(null)
    }
  }

  private String getVersion(Path mvnPath) {
    def out = new ByteArrayOutputStream()
    "$mvnPath -v".execute().waitForProcessOutput(out, System.err)

    String firstLine = out.toString().split('\n', 2)[0]
    return ((firstLine =~ /\d+(\.\d+)+/)[0] as List)[0]
  }

  private final Path _startPath

  private final String _version
}
