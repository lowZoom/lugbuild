package luj.groovy.build.api.git

import luj.groovy.build.api.git.command.RemoteCmd

interface Project {

  void push(String repository, List<String> options)

  List<String> remote(List<String> options)

  RemoteCmd remote()
}
