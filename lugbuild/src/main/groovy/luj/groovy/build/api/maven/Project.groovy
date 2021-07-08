package luj.groovy.build.api.maven

import java.nio.file.Path

interface Project {

  int phase(String phase)

  Plugin plugin(String name)

  String eval(String expression)

  Path path()
}
