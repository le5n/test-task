name := "test-task"

version := "0.1"

javacOptions in (Compile, compile) ++= Seq("-source", "1.8", "-target", "1.8", "-g:lines")

crossPaths := false // drop off Scala suffix from artifact names.
autoScalaLibrary := false // exclude scala-library from dependencies

lazy val akkaHttpVersion = "10.1.11"
lazy val akkaStreamVersion = "2.5.26"
lazy val lombokVersion = "1.16.16"
lazy val jacksonVersion = "10.1.11"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http"   % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaStreamVersion,
  "org.projectlombok" % "lombok" % lombokVersion,
  "com.typesafe.akka" %% "akka-http-jackson" % jacksonVersion,
)
