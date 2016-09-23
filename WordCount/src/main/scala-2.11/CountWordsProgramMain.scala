import akka.actor.{ActorSystem, Props}
import akka.dispatch.ExecutionContexts._
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Future
import scala.concurrent.duration._

/**
 * Created by IBM on 4/15/2016.
 */
object CountWordsProgramMain extends App {


  override def main(args: Array[String]) {
    implicit val ec = global
    var fileName = "C:\\AkkaDemos\\ScalaTest.txt"
    var system = ActorSystem("System")
    var actor = system.actorOf(Props(new LineLoader(fileName)))
    implicit val timeout = Timeout(25 seconds)
    val future:Future[Any] = actor ? StartWordProcessing()

    future.map { result =>
      println("Total number of words " + result)
      system.shutdown
    }

  }
}
