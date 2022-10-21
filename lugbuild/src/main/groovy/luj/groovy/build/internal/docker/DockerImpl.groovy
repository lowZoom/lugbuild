package luj.groovy.build.internal.docker

import luj.groovy.build.api.docker.ContainerCmd
import luj.groovy.build.api.docker.Docker
import luj.groovy.build.api.docker.ImageCmd
import luj.groovy.build.api.docker.SystemCmd
import luj.groovy.build.api.docker.VolumeCmd
import luj.groovy.build.internal.docker.container.ContainerImpl
import luj.groovy.build.internal.docker.image.ImageImpl
import luj.groovy.build.internal.docker.system.SystemImpl
import luj.groovy.build.internal.docker.volume.VolumeImpl

class DockerImpl implements Docker {

  @Override
  List<Map<String, String>> ps(List<String> columns) {
    return new ContainerPsRunner(['docker', 'ps'], columns, []).run()
  }

  @Override
  void run(String image, List<String> options) {
    new RunRunner(image, options, []).run()
  }

  @Override
  void run(String image, List<String> command, List<String> options) {
    new RunRunner(image, options, command).run()
  }

  @Override
  void exec(String container, List<String> command) {
    new ExecRunner(container, command).run()
  }

  @Override
  void stop(List<String> containers, List<String> options) {
    new StopRunner(containers, options).run()
  }

  @Override
  void rm(List<String> containers, List<String> options) {
    new RmRunner(containers, options).run()
  }

  @Override
  void login(String server, List<String> options) {
    new LoginRunner(server, options).run()
  }

  @Override
  ContainerCmd container() {
    return new ContainerImpl()
  }

  @Override
  ImageCmd image() {
    return new ImageImpl()
  }

  @Override
  VolumeCmd volume() {
    return new VolumeImpl()
  }

  @Override
  SystemCmd system() {
    return new SystemImpl()
  }
}
