package luj.groovy.build.internal.maven

import groovy.transform.PackageScope
import luj.groovy.build.api.maven.Plugin

import java.nio.file.Path

@PackageScope
class PluginImpl implements Plugin {

  @Override
  Goal goal(String name) {
    def goal = new PluginGoalImpl()
    goal._name = name
    goal._plugin = this
    return goal
  }

  String _name

  Path _mvnPath
}
