package simpleApp

import java.nio.file._
import com.github.plokhotnyuk.jsoniter_scala.core._
import com.github.plokhotnyuk.jsoniter_scala.macros._
import scala.io.BufferedSource
import scala.io.Source.fromURL

object SimpleApp {

  def main(args: Array[String]): Unit = {

    case class Name(official: String)
    case class RootInterface(name: Name,
                             region: String,
                             capital: Seq[String],
                             area: Double
                            )

    implicit val codec: JsonValueCodec[List[RootInterface]] = JsonCodecMaker.make

    //val args = Array("-s", "theName")
    val writeTo = Config.parseArgs(args)

    val url = "https://raw.githubusercontent.com/mledoze/countries/master/countries.json"

    val parsedData = {
      val source: BufferedSource = fromURL(url)
      try readFromString(source.mkString)
      finally source.close()
    }

    val africanCountries = parsedData.filter(p => p.region.contains("Africa"))
    val sortedAfricanCountries = africanCountries.groupBy(_.area).toList.sortWith(_._1 > _._1).map(_._2)
    val top10 = sortedAfricanCountries.take(10).flatten

    Files.write(Paths.get("/tmp/" + writeTo + ".json"), writeToArray(top10))

  }
}

//    val parsedData = fromURL(url).mkString
//    val bytes = parsedData.stripMargin.getBytes("UTF-8")
//    val countries = readFromArray[List[RootInterface]](bytes)

//val theBiggestCountry = africanCountries

//val africanCountries = parsed.region.equals("Africa")
//val africanCountries = parsed.map { case (_.region) => _.region.contains("Africa") }
// val africanCountries = parsed.filter(_.get("region")=="Africa")
//println(parsed.map(_.region.contains("Africa")))
//val sortedAfricanCountries = africanCountries.filter(p => max(p.area))
//val sortedAfricanCountries = sortElements(africanCountries.area)
//    val maxArea = (africanCountries.map(_.area)).max // 2381741.0
//    val theBiggestCountry = africanCountries.filter(p => p.area == maxArea)
//val l2 = africanCountries.collect.map(x => (x(3)))

//val theBiggest = africanCountries.filter(p => p.area.find(_.toString == maxArea.toString))
//    for (country <- africanCountries) {
//      if (country.area == maxArea){
//        val theBiggestCountry = country
//      }
//    }
//    val theBiggestCountry = africanCountries.collect { country => country.area match {
//      case maxArea => country
//    }}



//val user = JsonParse.readFromArray("""{"name":"John","devices":[{"id":1,"model":"HTC One X"}]}""".getBytes("UTF-8"))