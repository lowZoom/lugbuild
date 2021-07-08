package luj.groovy.build.internal.maven

import groovy.transform.PackageScope
import luj.groovy.build.api.maven.Plugin

import java.nio.file.Path

@PackageScope
class PluginGoalImpl implements Plugin.Goal {

  @Override
  int exec(Map<String, ?> userProp) {
    return run(userProp)
  }

  @Override
  int run(Map<String, ?> userProp) {
    List<String> cmd = makeCmd(userProp)
    println("> ${cmd}")

    Process proc = cmd.execute(null as List, _projPath?.toFile())
    proc.waitForProcessOutput(System.out, System.err)

    return proc.exitValue()
  }

  private List<String> makeCmd(Map<String, ?> param) {
    List<String> paramList = param.collect { k, v -> "-D${k}=${v}" }
    return _plugin._mvnCmd + ["${_plugin._name}:${_name}"] + paramList
  }

  String _name

  PluginImpl _plugin
  Path _projPath
}
