package luj.groovy.build.internal.docker

import groovy.transform.PackageScope
import luj.groovy.build.internal.common.ProcessOutputGetter

import java.util.stream.Collectors

@PackageScope
class PsRunner {

  PsRunner(List<String> columnList) {
    _columnList = columnList
  }

  List run() {
    String format = _columnList.stream()
        .map { "{{.${it}}}" }
        .collect(Collectors.joining('\t'))

    String out = ProcessOutputGetter.create(['docker', 'ps', '--format', format]).getOutput()
    return Arrays.stream(out.split('\n'))
        .map { combineHeader(it) }
        .collect(Collectors.toList())
  }

  private Map<String, String> combineHeader(String rowStr) {
    List rowList = rowStr.split('\t').toList()
    return (0..<_columnList.size()).collectEntries { [_columnList[it], rowList[it]] }
  }

  private final List<String> _columnList
}
