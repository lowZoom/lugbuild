package luj.groovy.build.api.docker

interface Docker {

  /**
   * @param columns https://docs.docker.com/engine/reference/commandline/ps/#formatting
   * @see luj.groovy.build.api.docker.ContainerCmd#ls
   */
  List<Map<String, String>> ps(List<String> columns)

  void run(String image, List<String> options)

  void run(String image, List<String> command, List<String> options)

  void exec(String container, List<String> command)

  void stop(List<String> containers, List<String> options)

  void rm(List<String> containers, List<String> options)

  void login(String server, List<String> options)

  ContainerCmd container()

  ImageCmd image()

  VolumeCmd volume()

  SystemCmd system()
}
