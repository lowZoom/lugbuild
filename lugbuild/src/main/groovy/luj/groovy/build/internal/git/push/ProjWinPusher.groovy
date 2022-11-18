package luj.groovy.build.internal.git.push

import luj.groovy.build.internal.common.ProcessOutputGetter
import luj.groovy.build.internal.common.ProcessRunner

import java.nio.file.Files
import java.nio.file.Path

class ProjWinPusher {

  ProjWinPusher(Path gitExe, Path homePath, Path projPath, String repo, List<String> options) {
    _gitExe = gitExe
    _homePath = homePath
    _projPath = projPath
    _repo = repo
    _options = options
  }

  void push() {
    Path bashExe = findBash()
    String bashHome = new ProcessOutputGetter(
        [bashExe, '-c', 'pwd'], _homePath, [:]).getOutput().trim()

    def var = { String key, String val -> "${key}='${val}' " }
    String optStr = makeOpt()

    String cmd = var('HOME', bashHome) +
        var('GIT_SSH_COMMAND', 'ssh -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null') +
        "git push ${optStr}'${_repo}'"

//    println(cmd)
    new ProcessRunner([bashExe, '-c', cmd], _projPath, [:], System.out).run()
  }

  private Path findBash() {
    Path bash = _gitExe.resolveSibling('bash.exe')
    assert Files.isRegularFile(bash)
    return bash
  }

  private String makeOpt() {
    if (!_options) {
      return ''
    }
    return _options.join(' ') + ' '
  }

  private final Path _gitExe
  private final Path _homePath

  private final Path _projPath
  private final String _repo

  private final List<String> _options
}
