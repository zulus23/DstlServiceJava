name := "play-java"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava,PlayEbean)

scalaVersion := "2.11.8"
javaOptions in Test += "-Dconfig.file=conf/application.conf"


libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,

  "org.pac4j"%"play-pac4j"%"2.5.0",
  "org.pac4j"%"pac4j-http"%"1.9.3",
  "org.pac4j"%"pac4j-jwt"%"1.9.3"  exclude("commons-io" , "commons-io"),
  "io.javaslang" % "javaslang" % "2.0.4",

  "org.webjars" %% "webjars-play" % "2.5.0",
  "org.webjars" % "bootstrap" % "3.3.7",
  "org.webjars" % "font-awesome" % "4.6.3",
  "org.webjars.bower" % "axios" % "0.14.0",
  //"org.webjars" % "jquery" % "1.12.3",
  "org.webjars" % "jquery" % "2.2.4",
  "org.webjars.npm" % "moment" % "2.15.2",
  "commons-io" % "commons-io" % "2.4",
  "org.mockito" % "mockito-core" % "1.10.19" % "test"

)
resolvers ++= Seq(Resolver.mavenLocal, "Sonatype snapshots repository" at "https://oss.sonatype.org/content/repositories/snapshots/")

routesGenerator := InjectedRoutesGenerator