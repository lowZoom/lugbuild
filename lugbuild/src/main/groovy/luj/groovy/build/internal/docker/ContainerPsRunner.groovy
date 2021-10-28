package luj.groovy.build.internal.docker

import groovy.transform.PackageScope
import luj.groovy.build.internal.common.ProcessOutputGetter
import luj.groovy.build.internal.docker.format.FormatKeyCombiner

import java.util.stream.Collectors

@PackageScope
class ContainerPsRunner {

  ContainerPsRunner(List<String> columnList) {
    _columnList = columnList
  }

  List<Map> run() {
    String format = _columnList.stream()
        .map { "{{.${it}}}" }
        .collect(Collectors.joining('\t'))

    String out = ProcessOutputGetter.create(['docker', 'ps', '--format', format]).getOutput()
//    print(out)

    return Arrays.stream(out.split('\n'))
        .map { new FormatKeyCombiner(_columnList, it).combine() }
        .collect(Collectors.toList())
  }

  private final List<String> _columnList
}
