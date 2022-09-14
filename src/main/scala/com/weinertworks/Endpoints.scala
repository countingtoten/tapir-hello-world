package com.weinertworks

import sttp.tapir._

import sttp.tapir.Codec.JsonCodec
import sttp.tapir.generic.auto._
import sttp.tapir.json.zio._
import sttp.tapir.swagger.bundle.SwaggerInterpreter
import sttp.tapir.ztapir.ZServerEndpoint
import zio.Task
import zio.ZIO
import zio.json.{DeriveJsonDecoder, DeriveJsonEncoder}

object Endpoints {

  val helloEndpoint = endpoint.get
    .in("hello" / path[String]("name"))
    .out(stringBody)

  val helloServerEndpoint: ZServerEndpoint[Any, Any] = helloEndpoint.serverLogicSuccess(name => ZIO.succeed(s"Hello ${name}"))

  val apiEndpoints: List[ZServerEndpoint[Any, Any]] = List(helloServerEndpoint)

  val docEndpoints: List[ZServerEndpoint[Any, Any]] = SwaggerInterpreter()
    .fromServerEndpoints[Task](apiEndpoints, "example", "1.0.0")

  val all: List[ZServerEndpoint[Any, Any]] = apiEndpoints ++ docEndpoints
}
