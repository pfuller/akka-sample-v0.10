package samplez

import javax.ws.rs.{GET, Path, Produces}
import se.scalablesolutions.akka.config.ScalaConfig._
import se.scalablesolutions.akka.actor.{ActorRegistry, SupervisorFactory, Actor}
import se.scalablesolutions.akka.actor.Actor._
import se.scalablesolutions.akka.actor._
import se.scalablesolutions.akka.config.ScalaConfig._
import se.scalablesolutions.akka.security.{BasicAuthenticationActor,BasicCredentials, UserInfo}


class Boot {
  val factory = SupervisorFactory(
    SupervisorConfig(
      RestartStrategy(OneForOne, 3, 100, List(classOf[Exception])),
      Supervise(Actor.actorOf[MyActor], LifeCycle(Permanent)) ::
      Supervise(Actor.actorOf[BasicAuthenticationService], LifeCycle(Permanent)) :: Nil))
  factory.newInstance.start
}

private case object Hello

@Path("/hello")
class HelloService {
  @GET
  @Produces(Array("text/html"))
  def hello = {
    val myActor = ActorRegistry.actorFor[MyActor].get
    (myActor !! Hello).getOrElse("Hyyy")

  }
}

class MyActor extends Actor {
  import self._

  def receive = {
    case Hello => reply(<h1>Hello, World</h1>.toString)
  }
}

class BasicAuthenticationService extends BasicAuthenticationActor {

  //Change this to whatever you want
  override def realm = "test"

  //Dummy method that allows you to log on with whatever username
  def verify(odc: Option[BasicCredentials]): Option[UserInfo] = odc match {
    case Some(dc) => userInfo(dc.username)
    case _ => None
  }

  //Dummy method that allows you to log on with whatever username with the password "bar"
  def userInfo(username: String): Option[UserInfo] = Some(UserInfo(username, "bar", "ninja" :: "chef" :: Nil))
}