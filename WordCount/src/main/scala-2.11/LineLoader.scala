import akka.actor.{Actor, ActorRef, Props}

import scala.io.Source._


/**
 * Created by IBM on 4/15/2016.
 */
case class StartWordProcessing()

class LineLoader(fileName: String) extends Actor {


  var running: Boolean = false

  var fileSender: Option[ActorRef] = None

  var totalLines = 0

  var totalWords = 0

  var linesProcessed = 0

  def receive = {
    case StartWordProcessing() => {
      if (running) {
        println("Duplicate process started")
      } else {
        println("Rxed message @ Actor -Master")
        running = true
        fileSender = Some(sender())
        var lineIterator = fromFile(fileName).getLines()
        lineIterator.foreach(line => {
          //println(line)
          context.actorOf(Props[WordCounterProcessor]) ! InitiateWordCount(line)
          totalLines += 1
        })
      }
    }
    case WordsInLine(wordCount) => {
      totalWords += wordCount
      linesProcessed += 1
      if (totalLines == linesProcessed) {
        fileSender.map(_ ! totalWords)
      }
    }


    case _ => println("Error: message not recognized")
  }
}
