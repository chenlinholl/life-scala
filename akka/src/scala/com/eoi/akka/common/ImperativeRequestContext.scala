package com.eoi.akka.common

import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.server.{RequestContext, Route, RouteResult}

import scala.concurrent.Promise

final class ImperativeRequestContext(ctx: RequestContext, promise: Promise[RouteResult]) {

  private implicit val context = ctx.executionContext

  private implicit val request = ctx.request

  def complete(obj: ToResponseMarshallable): Unit = ctx.complete(obj).onComplete(promise.complete)

  def failure(error: Throwable): Unit = ctx.fail(error).onComplete(promise.complete)
}

object ImperativeRequestContext {
  def imperativelyComplete(inner: ImperativeRequestContext => Unit): Route = {
    ctx: RequestContext =>
      val p = Promise[RouteResult]()
      inner(new ImperativeRequestContext(ctx, p))
      p.future
  }
}