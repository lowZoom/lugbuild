package luj.groovy.build.api.git

import luj.groovy.build.api.git.command.RemoteCmd

interface Project {

  List<String> remote(List<String> options)

  RemoteCmd remote()
}
