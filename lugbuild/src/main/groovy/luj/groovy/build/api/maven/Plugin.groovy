package luj.groovy.build.api.maven

interface Plugin {

  interface Goal {

    int exec(Map<String, ?> param)
  }

  Goal goal(String name)
}
