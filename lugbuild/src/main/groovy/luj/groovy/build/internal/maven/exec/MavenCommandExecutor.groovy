package luj.groovy.build.internal.maven.exec

import luj.groovy.build.internal.common.ProcessRunner

import java.nio.charset.Charset
import java.nio.file.Path

class MavenCommandExecutor {

  MavenCommandExecutor(List cmd, Path dir, OutputStream out) {
    _cmd = cmd
    _dir = dir
    _out = out
  }

  int exec() {
    def env = [MAVEN_OPTS: '-Dfile.encoding=' + Charset.defaultCharset().name()]
    return new ProcessRunner(_cmd, _dir, env, _out).runAndReturn()
  }

  private final List _cmd
  private final Path _dir

  private final OutputStream _out
}
