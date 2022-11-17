package luj.groovy.build.internal.git.command

import luj.groovy.build.api.git.command.RemoteCmd
import luj.groovy.build.internal.git.ProjectImpl

class RemoteImpl implements RemoteCmd {

  @Override
  String getUrl(String name) {
    List cmd = _cmdStart + ['get-url', name]
    return _proj.runAndGet(cmd)
  }

  List<String> _cmdStart

  ProjectImpl _proj
}
