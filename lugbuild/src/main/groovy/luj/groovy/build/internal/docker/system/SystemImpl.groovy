package luj.groovy.build.internal.docker.system

import luj.groovy.build.api.docker.SystemCmd
import luj.groovy.build.internal.common.ProcessRunner

class SystemImpl implements SystemCmd {

  @Override
  void prune(List<String> options) {
    execSystem(['prune'] + options)
  }

  private void execSystem(List cmd) {
    ProcessRunner.get(['docker', 'system'] + cmd).run()
  }
}
