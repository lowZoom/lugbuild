package luj.groovy.build.internal.docker

import groovy.transform.PackageScope
import luj.groovy.build.internal.common.ProcessRunner

@PackageScope
class RunRunner {

  RunRunner(String image, List<String> options) {
    _image = image
    _options = options
  }

  void run() {
    ProcessRunner.create(['docker', 'run'] + _options + [_image]).run()
  }

  private final String _image

  private final List<String> _options
}
