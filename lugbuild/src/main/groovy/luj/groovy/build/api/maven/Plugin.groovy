package luj.groovy.build.api.maven

interface Plugin {

  interface Goal {

    /**
     * @see #run
     */
    @Deprecated
    int exec(Map<String, ?> userProp)

    int run(Map<String, ?> userProp)
  }

  Goal goal(String name)
}
