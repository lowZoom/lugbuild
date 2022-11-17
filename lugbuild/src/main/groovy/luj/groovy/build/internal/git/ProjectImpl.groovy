package luj.groovy.build.internal.git

import groovy.transform.PackageScope
import luj.groovy.build.api.git.Project
import luj.groovy.build.api.git.command.RemoteCmd
import luj.groovy.build.internal.git.command.RemoteImpl

@PackageScope
class ProjectImpl implements Project {

  @Override
  RemoteCmd remote() {
    def remote = new RemoteImpl()
    remote._cmdStart = _binCmd + ['remote']
    return remote
  }

  List _binCmd
}
