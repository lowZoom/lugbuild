package luj.groovy.build.internal.maven.prepare

import groovy.transform.PackageScope
import luj.ava.file.path.PathX
import luj.groovy.build.internal.common.LocatePathWalker

import java.nio.file.Files
import java.nio.file.Path

@PackageScope
class MavenInstallationLocator {

  MavenInstallationLocator(Path startPath, String version) {
    _startPath = startPath
    _version = version
  }

  /**
   * @see #locateV2
   */
  @Deprecated
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

  List<String> locateV2() {
    boolean isWin = System.getProperty('os.name').toLowerCase().startsWith('windows')
    String launcher = isWin ? 'mvn.cmd' : 'mvn'

    return PathX.of(_startPath).walk {
      it.parallel()
          .filter { it.isRegularFile() }
          .filter { it.fileName == launcher }
          .map { it.toString() }
          .map { isWin ? [it] : ['bash', it] }
          .map { [it, getVersion(it)] }
          .filter { it[1] == _version }
          .findAny()
          .map { it[0] as List }
          .orElseThrow { new IllegalStateException("没有发现maven程序：${_version}") }
    }
  }

  private String getVersion(List<String> mvnCmd) {
    def out = new ByteArrayOutputStream()
    (mvnCmd + ['-v']).join(' ').execute().waitForProcessOutput(out, System.err)

    String firstLine = out.toString().split('\n', 2)[0]
    return ((firstLine =~ /\d+(\.\d+)+/)[0] as List)[0]
  }

  private final Path _startPath

  private final String _version
}
