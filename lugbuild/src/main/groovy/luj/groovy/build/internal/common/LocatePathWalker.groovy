package luj.groovy.build.internal.common

import groovy.transform.stc.ClosureParams
import groovy.transform.stc.SimpleType

import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Stream

class LocatePathWalker {

  LocatePathWalker(Path path) {
    _path = path
  }

  def <T> T walk(@ClosureParams(value = SimpleType,
      options = 'java.util.stream.Stream<java.nio.file.Path>') Closure<T> s) {
    Stream<Path> stream = Files.walk(_path)
    T result = s.call(stream)
    stream.close()
    return result
  }

  private final Path _path
}
