package luj.groovy.build.api.docker

interface ImageCmd {

  /**
   * https://docs.docker.com/engine/reference/commandline/build
   */
  void build(String path, List<String> options)

  /**
   * https://docs.docker.com/engine/reference/commandline/tag
   */
  void tag(String source, String target)

  void prune(List<String> options)
}
