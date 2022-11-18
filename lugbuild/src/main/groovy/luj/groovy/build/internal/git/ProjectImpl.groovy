package luj.groovy.build.internal.git

import luj.groovy.build.api.git.Project
import luj.groovy.build.api.git.command.RemoteCmd
import luj.groovy.build.internal.common.ProcessOutputGetter
import luj.groovy.build.internal.git.command.RemoteImpl
import luj.groovy.build.internal.git.push.ProjWinPusher

import java.nio.file.Path

class ProjectImpl implements Project {

  @Override
  void push(String repository, List<String> options) {
    def gitExe = _binCmd[0] as Path
    new ProjWinPusher(gitExe, _homePath, _projPath, repository, options).push()
  }

  @Override
  List<String> remote(List<String> options) {
    List cmd = _binCmd + ['remote'] + options
    return runAndGet(cmd).split('\n').toList()
  }

  @Override
  RemoteCmd remote() {
    def remote = new RemoteImpl()
    remote._cmdStart = _binCmd + ['remote']
    remote._proj = this
    return remote
  }

  @Override
  String toString() {
    return _projPath.toString()
  }

  String runAndGet(List cmd) {
    String outStr = new ProcessOutputGetter(cmd, _projPath, [:]).getOutput()
    return outStr.trim()
  }

  List _binCmd

  Path _projPath
  Path _homePath
}
