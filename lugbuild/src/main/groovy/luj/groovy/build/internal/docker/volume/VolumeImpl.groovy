package luj.groovy.build.internal.docker.volume

import luj.groovy.build.api.docker.VolumeCmd
import luj.groovy.build.internal.common.ProcessRunner

class VolumeImpl implements VolumeCmd {

  static final List<String> CMD = ['docker', 'volume']

  @Override
  List<Map<String, String>> ls(List<String> columns) {
    return new LsRunner(columns).run()
  }

  @Override
  void rm(List<String> volumes, List<String> options) {
    execVolume(['rm'] + options + volumes)
  }

  private void execVolume(List cmd) {
    ProcessRunner.create(CMD + cmd).run()
  }
}
