package luj.groovy.build.internal.docker.container

import luj.groovy.build.api.docker.ContainerCmd

class ContainerImpl implements ContainerCmd {

  static final List<String> CMD = ['docker', 'container']

  @Override
  List<Map<String, Object>> inspect(List<String> containers, List<String> includeKeys) {
    return new InspectRunner(containers, includeKeys.toSet()).run()
  }
}
