package luj.groovy.build.internal.git

import groovy.transform.PackageScope
import luj.groovy.build.api.git.Git
import luj.groovy.build.api.git.Project

import java.nio.file.Path

@PackageScope
class GitBinImpl implements Git.Bin {

  @Override
  Project project(Path path) {
    def proj = new ProjectImpl()
    proj._binCmd = [_path]
    return proj
  }

  Path _path
}
