package luj.groovy.build.internal.docker

import groovy.transform.PackageScope
import luj.groovy.build.internal.common.ProcessRunner

@PackageScope
class LoginRunner {

  LoginRunner(String server, List<String> options) {
    _server = server
    _options = options
  }

  void run() {
    def err = new ByteArrayOutputStream()
    int exit = ProcessRunner.get(['docker', 'login'] + _options + [_server]).runAndReturn(err)

    if (exit) {
      throw new RuntimeException(err.toString())
    }
  }

  private final String _server

  private final List<String> _options
}
