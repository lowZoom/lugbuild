package luj.groovy.build.api.docker

interface ContainerCmd {

  List<Map<String, Object>> inspect(List<String> containers, List<String> includeKeys)
}
