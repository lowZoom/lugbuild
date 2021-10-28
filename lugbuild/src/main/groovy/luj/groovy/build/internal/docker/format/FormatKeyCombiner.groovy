package luj.groovy.build.internal.docker.format

class FormatKeyCombiner {

  FormatKeyCombiner(List<String> keyList, String rowStr) {
    _keyList = keyList
    _rowStr = rowStr
  }

  Map<String, String> combine() {
    List rowList = _rowStr.split('\t').toList()
    return (0..<_keyList.size()).collectEntries { [_keyList[it], rowList[it]] }
  }

  private final List<String> _keyList

  private final String _rowStr
}
