package luj.groovy.build.internal.maven.exec

import java.nio.file.Path

class MavenCommandExecutor {

  MavenCommandExecutor(List cmd, Path dir, OutputStream out) {
    _cmd = cmd
    _dir = dir
    _out = out
  }

  int exec() {
    def builder = new ProcessBuilder(_cmd.collect { it.toString() })
    builder.environment()['MAVEN_OPTS'] = '-Dfile.encoding=UTF8'

    if (_dir) {
      builder.directory(_dir.toFile())
    }

    Process proc = builder.start()
    proc.waitForProcessOutput(_out, System.err)
    return proc.exitValue()
  }

  private final List _cmd
  private final Path _dir

  private final OutputStream _out
}
