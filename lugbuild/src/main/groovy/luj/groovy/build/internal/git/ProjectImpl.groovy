package luj.groovy.build.internal.git


import luj.groovy.build.api.git.Project
import luj.groovy.build.api.git.command.RemoteCmd
import luj.groovy.build.internal.common.ProcessOutputGetter
import luj.groovy.build.internal.git.command.RemoteImpl

import java.nio.file.Path

class ProjectImpl implements Project {

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

  String runAndGet(List cmd) {
    String outStr = new ProcessOutputGetter(cmd, _projPath, [:]).getOutput()
    return outStr.trim()
  }

  List _binCmd

  Path _projPath
}
