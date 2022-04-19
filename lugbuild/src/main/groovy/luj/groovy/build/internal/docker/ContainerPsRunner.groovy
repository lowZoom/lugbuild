package luj.groovy.build.internal.docker


import luj.groovy.build.internal.common.ProcessOutputGetter
import luj.groovy.build.internal.docker.format.FormatKeyCombiner

import java.util.stream.Collectors

class ContainerPsRunner {

  ContainerPsRunner(List<String> cmd, List<String> columnList, List<String> options) {
    _cmd = cmd
    _columnList = columnList
    _options = options
  }

  List<Map> run() {
    String format = _columnList.stream()
        .map { "{{.${it}}}" }
        .collect(Collectors.joining('\t'))

    String out = ProcessOutputGetter.create(_cmd + ['--format', format] + _options).getOutput()
//    print(out)

    return Arrays.stream(out.split('\n'))
        .map { new FormatKeyCombiner(_columnList, it).combine() }
        .collect(Collectors.toList())
  }

  private final List<String> _cmd

  private final List<String> _columnList
  private final List<String> _options
}
