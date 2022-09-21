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
  void tag(String source, String target) {
    execImage(['tag', source, target])
  }

  @Override
  void push(String image) {
    execImage(['push', image])
  }

  @Override
  void pull(String image) {
    execImage(['pull', image])
  }

  @Override
  void prune(List<String> options) {
    execImage(['prune'] + options)
  }

  private void execImage(List cmd) {
    ProcessRunner.get(CMD + cmd).run()
  }
}
