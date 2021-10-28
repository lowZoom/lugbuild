package luj.groovy.build.internal.docker.container

import luj.groovy.build.api.docker.ContainerCmd
import luj.groovy.build.internal.common.ProcessRunner

class ContainerImpl implements ContainerCmd {

  static final List<String> CMD = ['docker', 'container']

  @Override
  List<Map<String, Object>> inspect(List<String> containers, List<String> includeKeys) {
    return new InspectRunner(containers, includeKeys.toSet()).run()
  }

  @Override
  void prune(List<String> options) {
    execContainer(['prune'] + options)
  }

  private void execContainer(List cmd) {
    ProcessRunner.create(CMD + cmd).run()
  }
}
