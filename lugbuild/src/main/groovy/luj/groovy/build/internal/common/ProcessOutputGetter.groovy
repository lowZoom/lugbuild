package luj.groovy.build.internal.common


import java.nio.file.Path

class ProcessOutputGetter {

  ProcessOutputGetter(String cmd, Path execDir, Map<String, String> env) {
    _cmd = cmd
    _execDir = execDir
    _env = env
  }

  String getOutput() {
    List envList = _env ? _env.collect { k, v -> "$k=$v" } : null
    Process proc = _cmd.execute(envList, _execDir.toFile())

    def out = new ByteArrayOutputStream()
    proc.waitForProcessOutput(out, System.err)
    return out.toString()
  }

  private final String _cmd

  private final Path _execDir
  private final Map<String, String> _env
}
