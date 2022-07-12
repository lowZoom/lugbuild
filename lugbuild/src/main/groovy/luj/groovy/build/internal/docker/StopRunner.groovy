package luj.groovy.build.internal.docker

import groovy.transform.PackageScope
import luj.groovy.build.internal.common.ProcessRunner

@PackageScope
class StopRunner {

  StopRunner(List<String> containerList, List<String> options) {
    _containerList = containerList
    _options = options
  }

  void run() {
    ProcessRunner.create(['docker', 'stop'] + _options + _containerList).run()
  }

  private final List<String> _containerList

  private final List<String> _options
}
