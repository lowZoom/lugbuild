package luj.groovy.build.internal.git

import luj.groovy.build.api.git.Git

import java.nio.file.Path

class GitPrepareImpl implements Git {

  @Override
  Git locate(Path startPath) {
    _bin._binPath = startPath
    return this
  }

  @Override
  Bin bin() {
    return _bin
  }

  GitBinImpl _bin = new GitBinImpl()
}
