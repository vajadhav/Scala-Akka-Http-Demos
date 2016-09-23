package com.akkahttp

import java.util.concurrent.ConcurrentLinkedDeque

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.akkahttp.Models.{Customer, ServiceJsonProtoocol}
import com.akkahttp.Models.Customer
import spray.json.DefaultJsonProtocol._

import scala.collection.JavaConverters._


object AkkaJsonParsing {


  def main(args: Array[String]) {

    implicit val actorSystem = ActorSystem("rest-api")

    implicit val actorMaterializer = ActorMaterializer()

    val list = new ConcurrentLinkedDeque[Customer]()

    import com.akkahttp.Models.ServiceJsonProtoocol.customerProtocol
    val route =
      path("customer") {
        post {
          entity(as[Customer]) {
            customer => complete {
              list.add(customer)
              s"got customer with name ${customer.name}"
            }
          }
        } ~
          get {
            complete {
              list.asScala
            }
          }
      }

    Http().bindAndHandle(route, "localhost", 8080)
  }


}
