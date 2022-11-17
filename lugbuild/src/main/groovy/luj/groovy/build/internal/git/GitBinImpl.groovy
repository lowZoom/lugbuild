package luj.groovy.build.internal.git

import groovy.transform.PackageScope
import luj.ava.file.path.PathX
import luj.groovy.build.api.git.Git
import luj.groovy.build.api.git.Project

import java.nio.file.Path

@PackageScope
class GitBinImpl implements Git.Bin {

  @Override
  Project project(Path path) {
    def proj = new ProjectImpl()
    proj._binCmd = [_binPath]
    proj._projPath = getProjPath(PathX.of(path)).asPath()
    return proj
  }

  private PathX getProjPath(PathX path) {
    assert path.isDirectory()
    if ('.git'.equals(path.fileName)) {
      return path.parent
    }

    PathX gitDir = path.resolve('.git')
    assert gitDir.isDirectory()
    return gitDir
  }

  Path _binPath
}
