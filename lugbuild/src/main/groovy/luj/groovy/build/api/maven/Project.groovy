package luj.groovy.build.api.maven

interface Project {

  void phase(String phase)

  String eval(String expression)
}
