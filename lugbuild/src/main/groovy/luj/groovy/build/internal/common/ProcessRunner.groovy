package luj.groovy.build.internal.common

import java.nio.file.Path

class ProcessRunner {

  static ProcessRunner get(List cmd) {
    return new ProcessRunner(cmd, null, [:], System.out)
  }

  ProcessRunner(List cmd, Path execDir, Map<String, String> env, OutputStream out) {
    _cmd = cmd
    _execDir = execDir
    _env = env
    _out = out
  }

  void run() {
//    println(_cmd)
    assert runAndReturn(System.err) == 0
  }

  int runAndReturn(OutputStream err) {
    def builder = new ProcessBuilder(_cmd.collect { it.toString() })
    builder.environment().putAll(_env)
    if (_execDir) {
      builder.directory(_execDir.toFile())
    }

    Process proc = builder.start()
    proc.waitForProcessOutput(_out, err)
    return proc.exitValue()
  }

  private final List _cmd

  private final Path _execDir
  private final Map<String, String> _env

  private final OutputStream _out
}
