import akka.actor.Actor

/**
 * Created by IBM on 4/15/2016.
 */

case class InitiateWordCount(line: String)

case class WordsInLine(wordCount: Int)


class WordCounterProcessor extends Actor {


  def receive = {
    case InitiateWordCount(line) => {

      var wordCount = line.split(" ").length
      println("Rxed message at last worsds in line --->" + line + " ****** " + wordCount)
      sender ! WordsInLine(wordCount)
    }

    case _ => println("Error: message not recognized")
  }
}
