package luj.groovy.build.internal.common


import java.nio.file.Path

class ProcessOutputGetter {

  /**
   * @see #get
   */
  @Deprecated
  static ProcessOutputGetter create(List cmd) {
    return get(cmd)
  }

  static ProcessOutputGetter get(List cmd) {
    return new ProcessOutputGetter(cmd, null, [:])
  }

  ProcessOutputGetter(List cmd, Path execDir, Map<String, String> env) {
    _cmd = cmd
    _execDir = execDir
    _env = env
  }

  String getOutput() {
    def out = new ByteArrayOutputStream()
    new ProcessRunner(_cmd, _execDir, _env, out).run()
    return out.toString()
  }

  private final List _cmd

  private final Path _execDir
  private final Map<String, String> _env
}
