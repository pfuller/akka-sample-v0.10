import sbt._

class AkkaProject(info: ProjectInfo) extends DefaultWebProject(info) {

  val scalaReleases = ScalaToolsReleases

  // ------------------------------------------------------------
  // repositories
  val mavenmirror = "mavenmirror" at "http://mirrors.ibiblio.org/pub/mirrors/maven2/"
  val m2 = "m2" at "http://download.java.net/maven/2"
  val jboss = "jBoss" at "http://repository.jboss.org/maven2"
  val jbossnexus = "Jboss Nexus" at "http://repository.jboss.org/nexus/content/groups/public/"
  val akka = "Akka Repository" at "http://scalablesolutions.se/akka/repository"
  val toolsrepo = "toolsrepo" at "http://scala-tools.org/repo-releases/"
  val configgy = "Configgy" at "http://www.lag.net/repo"
  val guice = "Guice Repository" at "http://guiceyfruit.googlecode.com/svn/repo/releases/"
  val codehaus = "Codehaus" at "http://repository.codehaus.org"

  // project versions
  val akkaVersion = "0.10"

  override def libraryDependencies = Set(
    "se.scalablesolutions.akka" %% "akka-core"  % akkaVersion % "compile->default",
    "se.scalablesolutions.akka" %% "akka-http" % akkaVersion % "compile->default",
    "se.scalablesolutions.akka" %% "akka-persistence-mongo" % akkaVersion % "compile->default",
    "se.scalablesolutions.akka" %% "akka-sample-security" % akkaVersion % "compile->default",
    "se.scalablesolutions.akka" %% "akka-sample-rest-scala" % akkaVersion % "compile->default",
    "se.scalablesolutions.akka" %% "akka-sample-rest-java" % akkaVersion % "compile->default",
    "se.scalablesolutions.akka" %% "akka-sample-camel" % akkaVersion % "compile->default",
    "org.mortbay.jetty" % "jetty" % "6.1.22" % "test->default",
    "junit" % "junit" % "4.5" % "test->default",
    "org.scala-tools.testing" %% "specs" % "1.6.5" % "test->default"
  ) ++ super.libraryDependencies
}
