name := "play-java"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava,PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,

  "org.pac4j"%"play-pac4j"%"2.6.0-SNAPSHOT",
  "org.pac4j"%"pac4j-http"%"1.9.4",
  "org.pac4j"%"pac4j-jwt"%"1.9.4"  exclude("commons-io" , "commons-io"),
  "org.pac4j"%"pac4j-sql"%"1.9.4",

  "org.webjars" %% "webjars-play" % "2.5.0",
  "org.webjars" % "bootstrap" % "3.3.7",
  "org.webjars" % "font-awesome" % "4.6.3",
  //"org.webjars" % "jquery" % "1.12.3",
  "org.webjars" % "jquery" % "2.2.4",
  "commons-io" % "commons-io" % "2.4"

)
resolvers ++= Seq(Resolver.mavenLocal, "Sonatype snapshots repository" at "https://oss.sonatype.org/content/repositories/snapshots/",
                  "Typesafe Releases" at "http://repo.typesafe.com/typesafe/maven-releases/"
)

routesGenerator := InjectedRoutesGenerator