val tapirVersion = "1.1.0"

lazy val rootProject = (project in file(".")).settings(
  Seq(
    name := "example",
    version := "0.1.0-SNAPSHOT",
    organization := "com.weinertworks",
    scalaVersion := "2.13.8",
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-zio-http-server" % tapirVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-bundle" % tapirVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-json-zio" % tapirVersion,
      "ch.qos.logback" % "logback-classic" % "1.4.0",
      "com.softwaremill.sttp.tapir" %% "tapir-sttp-stub-server" % tapirVersion % Test,
      "dev.zio" %% "zio-test" % "2.0.0" % Test,
      "dev.zio" %% "zio-test-sbt" % "2.0.0" % Test,
      "com.softwaremill.sttp.client3" %% "zio-json" % "3.7.6" % Test
    ),
    testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))
  )
)
