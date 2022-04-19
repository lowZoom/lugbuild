package luj.groovy.build.api.docker

interface ContainerCmd {

  /**
   * @param columns https://docs.docker.com/engine/reference/commandline/ps/#formatting
   */
  List<Map<String, String>> ls(List<String> columns, List<String> options)

  List<Map<String, Object>> inspect(List<String> containers, List<String> includeKeys)

  void prune(List<String> options)
}
