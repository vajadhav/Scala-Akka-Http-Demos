package com.akkahttp

import java.util.concurrent.ConcurrentLinkedDeque

import akka.actor.ActorSystem
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.akkahttp.Models.{Customer, ServiceJsonProtoocol}

import scala.collection.JavaConverters._


trait RestService {
   implicit val system:ActorSystem
   implicit val materializer:ActorMaterializer

   val list = new ConcurrentLinkedDeque[Customer]()

   import ServiceJsonProtoocol._
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
}
