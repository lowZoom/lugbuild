package luj.groovy.build.api.git

import java.nio.file.Path

interface Git {

  Git locate(Path startPath)

  Bin bin()

  interface Bin {

    Project project(Path path)
  }
}
