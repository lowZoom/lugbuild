package luj.groovy.build.api.maven;

import java.nio.file.Path;

public interface Maven {

  interface Preparer {

    Preparer version(String version);

    Preparer locate(Path startPath);

    Preparer downloadIfAbsent();

    Bin bin();
  }

  interface Bin {

    Project project(Path path);
  }

  interface Project {

    void phase(String phase);

    String eval(String expression);
  }
}
