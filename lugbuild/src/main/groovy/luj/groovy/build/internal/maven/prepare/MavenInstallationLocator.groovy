package luj.groovy.build.internal.maven.prepare

import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Stream

class MavenInstallationLocator {

  MavenInstallationLocator(Path startPath, String version) {
    _startPath = startPath
    _version = version
  }

  Path locate() {
    Stream<Path> walker = Files.walk(_startPath)

    Path binPath = walker.parallel()
        .filter { it.getFileName().toString() == 'mvn.cmd' }
        .map { new Tuple2<>(it, getVersion(it)) }
        .filter { it.second == _version }
        .map { it.first }
        .findAny()
        .orElse(null)

    walker.close()
    return binPath
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
