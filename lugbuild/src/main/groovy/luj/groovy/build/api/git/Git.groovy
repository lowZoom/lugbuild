package luj.groovy.build.api.git

import java.nio.file.Path

interface Git {

  Git locate(Path startPath)

  Git bashHome(Path homePath)

  Bin bin()

  interface Bin {

    Project project(Path path)
  }
}
