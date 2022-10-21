package luj.groovy.build.internal.docker

import groovy.transform.PackageScope
import luj.groovy.build.internal.common.ProcessRunner

@PackageScope
class RunRunner {

  RunRunner(String image, List<String> options, List<String> command) {
    _image = image
    _options = options
    _command = command
  }

  void run() {
    ProcessRunner.get(['docker', 'run'] + _options + [_image] + _command).run()
  }

  private final String _image

  private final List<String> _options
  private final List<String> _command
}
