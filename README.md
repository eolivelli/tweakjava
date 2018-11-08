# tweakjava
Utility for tweaking Java

This is an utility package which contains a Java Agent which is able to modify classes at boot.

Since Java 12 it is not possible to remove the 'final' qualifier from fields, and this prevents [PowerMock](https://github.com/powermock/powermock) and other tweaking tools from working.

This tool is very simple because it leverages the great [ByteBuddy](https://bytebuddy.net/) library.


# Usage
You have to build the Java Agent and configure your JVM, for instance the Maven Surefire plugin, in order to boot the agent.

The Agent can work with this two modes:

- Drop the 'final' qualifier from fields annotated with @tweakjava.RemoveFinalQualifierInTests
- Drop the 'final' qualifier from any field of classes whose name matches a regexp (defined with tweakjava.removefinal.classes.pattern)

```
<plugin>
   <artifactId>maven-surefire-plugin</artifactId>
   <version>2.22.0</version>
   <configuration>
      <argLine>-javaagent:${basedir}/../../tweakjava/target/tweakjava.agent-0.0.1.jar -Dtweakjava.removefinal.classes.pattern=.*ClassWithFinalFields</argLine>
      ....
  </configuration>
</plugin>

```
Do not ever use this tool in production. It is meant to be used only for mocking in tests.

# License

This code is licensed with Apache License v2
