package luj.groovy.build.internal.docker

import groovy.transform.PackageScope
import luj.groovy.build.internal.common.ProcessRunner

@PackageScope
class ExecRunner {

  ExecRunner(String container, List<String> cmd) {
    _container = container
    _cmd = cmd
  }

  void run() {
    ProcessRunner.create(['docker', 'exec'] + [_container] + _cmd).run()
  }

  private final String _container

  private final List<String> _cmd
}
