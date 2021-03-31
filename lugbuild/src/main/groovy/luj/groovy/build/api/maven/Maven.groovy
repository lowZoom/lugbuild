package luj.groovy.build.api.maven

import java.nio.file.Path

interface Maven {

  Maven version(String version)

  Maven locate(Path startPath)

  Maven downloadIfAbsent()

  Bin bin()

  interface Bin {

    Project project(Path path)

    Plugin plugin(String name)
  }
}
