package luj.groovy.build.internal.common

import java.nio.file.Path

class ProcessRunner {

  static ProcessRunner create(List cmd) {
    return new ProcessRunner(cmd, null, [:], System.out)
  }

  ProcessRunner(List cmd, Path execDir, Map<String, String> env, OutputStream out) {
    _cmd = cmd
    _execDir = execDir
    _env = env
    _out = out
  }

  void run() {
    List envList = _env ? _env.collect { k, v -> "$k=$v" } : null
//    println(_cmd)

    Process proc = _cmd.collect { it.toString() }.execute(envList, _execDir?.toFile())
    proc.waitForProcessOutput(_out, System.err)

    assert proc.exitValue() == 0
  }

  private final List _cmd

  private final Path _execDir
  private final Map<String, String> _env

  private final OutputStream _out
}
