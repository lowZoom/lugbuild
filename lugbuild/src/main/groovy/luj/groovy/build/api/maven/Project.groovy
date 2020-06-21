package luj.groovy.build.api.maven

import java.nio.file.Path

interface Project {

  int phase(String phase)

  String eval(String expression)

  Path path()
}
