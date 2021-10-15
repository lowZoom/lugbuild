package luj.groovy.build.api.docker

interface ImageCmd {

  /**
   * https://docs.docker.com/engine/reference/commandline/build
   */
  void build(String path, List<String> options)

  void prune(List<String> options)
}
