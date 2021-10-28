package luj.groovy.build.api.docker

interface VolumeCmd {

  /**
   * @param columns https://docs.docker.com/engine/reference/commandline/volume_ls/#formatting
   */
  List<Map<String, String>> ls(List<String> columns)

  void rm(List<String> volumes, List<String> options)
}
