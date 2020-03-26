package luj.groovy.build.internal.maven

import groovy.transform.PackageScope
import luj.groovy.build.api.maven.Maven

import java.nio.file.Path

@PackageScope
class MavenProjImpl implements Maven.Project {

  MavenProjImpl(Path mvnPath, Path projPath) {
    _mvnPath = mvnPath
    _projPath = projPath
  }

  @Override
  void phase(String phase) {
    exec("$_mvnPath -e $phase", System.out)
  }

  @Override
  String eval(String expression) {
    def out = new ByteArrayOutputStream()
    exec("$_mvnPath help:evaluate -Dexpression=$expression -q -DforceStdout", out)
    return out.toString()
  }

  private void exec(String cmd, OutputStream out) {
    Process proc = cmd.execute(null as List, _projPath.toFile())
    proc.waitForProcessOutput(out, System.err)
  }

  private final Path _mvnPath

  private final Path _projPath
}
