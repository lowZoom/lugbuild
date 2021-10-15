package luj.groovy.build.internal.docker.image

import luj.groovy.build.api.docker.ImageCmd
import luj.groovy.build.internal.common.ProcessRunner

class ImageImpl implements ImageCmd {

  static final List<String> CMD = ['docker', 'image']

  @Override
  void build(String path, List<String> options) {
    execImage(['build'] + options + [path])
  }

  @Override
  void prune(List<String> options) {
    execImage(['prune'] + options)
  }

  private void execImage(List cmd) {
    ProcessRunner.create(CMD + cmd).run()
  }
}