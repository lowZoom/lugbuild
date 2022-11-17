package luj.groovy.build.api.git

import luj.groovy.build.api.git.command.RemoteCmd

interface Project {

  RemoteCmd remote()
}
