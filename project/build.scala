import sbt._
import Keys._

object Colors extends Build {

  lazy val defaults = super.settings ++ Seq(
    organization := "d01100100",
    version := "0.1",
    scalaVersion := "2.10.3",
    scalacOptions := Seq(
      "-unchecked",
      "-deprecation",
      "-feature",
      "-encoding",
      "utf8"),
    libraryDependencies ++= Seq(
      "org.parboiled" %% "parboiled" % "2.0-M2",
      "org.specs2"    %% "specs2"    % "2.3.10" % "test"),
    resolvers ++= Seq(
      "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/")
  ) ++ Project.defaultSettings

  val collab = Project(
    id = "colors",
    base = file("."),
    settings = defaults)
}
