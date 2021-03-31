package luj.groovy.build.internal.maven

import groovy.transform.PackageScope
import luj.groovy.build.api.maven.Plugin

@PackageScope
class PluginGoalImpl implements Plugin.Goal {

  @Override
  int exec(Map<String, ?> param) {
    Process proc = makeCmd(param).execute()
    proc.waitForProcessOutput(System.out, System.err)
    return proc.exitValue()
  }

  private String makeCmd(Map<String, ?> param) {
    List<String> paramList = param.collect { k, v -> "-D${k}=${v}" }
    return ([_plugin._mvnPath, "${_plugin._name}:${_name}"] + paramList).join(' ')
  }

  String _name

  PluginImpl _plugin
}
