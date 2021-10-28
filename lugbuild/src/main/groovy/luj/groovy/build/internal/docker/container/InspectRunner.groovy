package luj.groovy.build.internal.docker.container

import groovy.json.JsonSlurper
import groovy.transform.PackageScope
import luj.groovy.build.internal.common.ProcessOutputGetter

import java.util.stream.Collectors

@PackageScope
class InspectRunner {

  InspectRunner(List<String> containerList, Set<String> includeKeys) {
    _containerList = containerList
    _includeKeys = includeKeys
  }

  List<Map> run() {
    def exec = { List cmd -> ProcessOutputGetter.create(cmd).getOutput() }
    def inspect = { List cmd -> exec(ContainerImpl.CMD + ['inspect'] + cmd) }

    if (!_includeKeys) {
      String outStr = inspect(_containerList)
      return JSON.parseText(outStr)
    }

    String outStr = inspect(['--format', '{{range $k,$v:=.}}' + _includeKeys.stream()
        .map { '{{if eq $k "' + it + '"}}{{json $k}}:{{json $v}},{{end}}' }
        .collect(Collectors.joining()) + '{{end}}'] + _containerList)

    return Arrays.stream(outStr.split('\n'))
        .map { JSON.parseText('{' + it.replaceFirst(',$', '}')) }
        .collect(Collectors.toList())
  }

  private static final JsonSlurper JSON = new JsonSlurper()

  private final List<String> _containerList
  private final Set<String> _includeKeys
}
