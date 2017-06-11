name := """brighton-pubs-app"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies += "com.typesafe.play" %% "play-slick" % "2.0.2"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "2.0.2"
libraryDependencies += "com.h2database" % "h2" % "1.4.192"
libraryDependencies += "org.postgresql" % "postgresql" % "9.4-1201-jdbc41"
libraryDependencies += "de.svenkubiak" % "jBCrypt" % "0.4.1"

libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0" % "test"

libraryDependencies += specs2 % Test
libraryDependencies += filters
libraryDependencies += ws
libraryDependencies += cache

enablePlugins(SbtWeb)

enablePlugins(SbtJsEngine)

updateOptions := updateOptions.value.withCachedResolution(true)

import com.typesafe.sbt.jse.JsEngineImport.JsEngineKeys._
import com.typesafe.sbt.jse.SbtJsTask._
import com.typesafe.sbt.jse.SbtJsEngine.autoImport.JsEngineKeys._
import scala.concurrent.duration._

lazy val install = taskKey[Unit]("npm")

install := {
  ( npmNodeModules in Assets ).value
  val modules =  (baseDirectory.value / "node_modules").getAbsolutePath
  executeJs(state.value,
    engineType.value,
    None,
    Seq(modules),
    baseDirectory.value / "node_modules/webpack/bin/webpack.js",
    Seq(),
    30.seconds)
  ()
}

(packageBin in Assets) <<= (packageBin in Assets) dependsOn install

herokuProcessTypes in Compile := Map(
  "web" -> "target/universal/stage/bin/brighton-pubs-app -Dhttp.port=$PORT -Dconfig.resource=${CONF} -Dplay.crypto.secret=${SECRET}"
)

herokuAppName in Compile := Map(
  "dev"      -> "brightonpubs-dev",
  "staging"  -> "brightonpubs-staging",
  "prod"     -> "brightonpubs-prod"
).getOrElse(sys.props("appEnv"), "brightonpubs-dev")
