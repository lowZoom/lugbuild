package luj.groovy.build.internal.maven

import groovy.transform.PackageScope
import luj.groovy.build.api.maven.Project

import java.nio.file.Path

@PackageScope
class MavenProjImpl implements Project {

  MavenProjImpl(Path mvnPath, Path projPath) {
    _mvnPath = mvnPath
    _projPath = projPath
  }

  @Override
  int phase(String phase) {
    return exec("$_mvnPath -e $phase", System.out)
  }

  @Override
  String eval(String expression) {
    def out = new ByteArrayOutputStream()
    exec("$_mvnPath help:evaluate -Dexpression=$expression -q -DforceStdout", out)
    return out.toString()
  }

  @Override
  Path path() {
    return _projPath
  }

  private int exec(String cmd, OutputStream out) {
    Process proc = cmd.execute(null as List, _projPath.toFile())
    proc.waitForProcessOutput(out, System.err)
    return proc.exitValue()
  }

  private final Path _mvnPath

  private final Path _projPath
}
