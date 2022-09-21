package luj.groovy.build.internal.docker

import groovy.transform.PackageScope
import luj.groovy.build.internal.common.ProcessRunner

@PackageScope
class LoginRunner {

  LoginRunner(List<String> options) {
    _options = options
  }

  void run() {
    def err = new ByteArrayOutputStream()
    int exit = ProcessRunner.get(['docker', 'login'] + _options).runAndReturn(err)

    if (exit) {
      throw new RuntimeException(err.toString())
    }
  }

  private final List<String> _options
}
