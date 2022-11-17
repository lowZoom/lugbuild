package luj.groovy.build.internal.git.command


import luj.groovy.build.api.git.command.RemoteCmd
import luj.groovy.build.internal.common.ProcessOutputGetter

class RemoteImpl implements RemoteCmd {

  @Override
  String getUrl(String name) {
    List cmd = _cmdStart + ['get-url', name]
    return ProcessOutputGetter.get(cmd).getOutput()
  }

  List<String> _cmdStart
}
