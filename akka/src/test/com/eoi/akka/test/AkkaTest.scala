package com.eoi.akka.test

import org.junit.Test
import org.slf4j.LoggerFactory

class AkkaTest {

  private val log = LoggerFactory.getLogger(getClass)

  private val wordFrequencies = ("habitual", 6) :: ("and", 56) :: ("consuetudinary", 2) ::
    ("additionally", 27) :: ("homely", 5) :: ("society", 13) :: Nil

  def wordsWithoutOutliers(wordFrequencies: Seq[(String, Int)]): Seq[String] =
    wordFrequencies.filter(wf => wf._2 > 3 && wf._2 < 25).map(_._1)

  def wordsWithoutOutliers1(wordFrequencies: Seq[(String, Int)]): Seq[String] =
    wordFrequencies.filter { case (_, f) => f > 3 && f < 25 } map { case (w, _) => w }


  @Test
  def test(): Unit = {
    val res = wordsWithoutOutliers(wordFrequencies)
    log.info("res:{}", res)
    val res1 = wordsWithoutOutliers1(wordFrequencies)
    log.info("res:{}", res1)
  }

  @Test
  def test1(): Unit = {

    import scala.util.control.Breaks._
    val list = 1.to(50).toList

    for (i <- list) {
      if (i == 4) {
        log.debug("i=>{}", i)
        try {
          break
        } catch {
          case e: Throwable => log.error(s"throwable ->:${e}")
        }
      }
      log.info("i=>{}", i)
    }
  }

  @Test
  def test2(): Unit = {
    val map = Map(0 -> "123")
    log.info("{}", map.apply(0))
  }

  @Test
  def test3(): Unit = {

    val customer = Customer(20)
    try {
      val cig = buyCigarettes(customer)
      cig.test()
    } catch {
      case UnderAgeException(message) => log.error(message)
    }
  }

  @Test
  def test4(): Unit = {
    val list = 2.to(20).toList
    log.info(s"${list.headOption.getOrElse(-1)}")
  }


  case class Customer(age: Int)

  class Cigarettes {
    def test(): Unit = {
      log.info("hello world!!!")
    }
  }

  case class UnderAgeException(message: String) extends Exception(message)

  def buyCigarettes(customer: Customer): Cigarettes =
    if (customer.age < 16)
      throw UnderAgeException(s"Customer must be older than 16 but was ${customer.age}")
    else new Cigarettes
}
