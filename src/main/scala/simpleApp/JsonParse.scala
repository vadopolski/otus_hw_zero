package simpleApp.JsonParse

import com.github.plokhotnyuk.jsoniter_scala.core._
import com.github.plokhotnyuk.jsoniter_scala.macros._

object JsonParse extends App {
  case class Name(common: String, official: String)
  case class RootInterface(name: Name,
                           capital: Seq[String],
                           region: String,
                           area: Int
                          )

  implicit val codec: JsonValueCodec[RootInterface] = JsonCodecMaker.make

  // фильтровать по стране "region": "Africa" List[RootInterface]
  //val africanCountries = text.copy(entry = text.region.filter(_.end.isBefore(to)))
  //val africanCountries = text.region.equals("africa")
  //val africanCountries = text.values.find(_.region == "africa")

  // сортировка по площади area (18ый массив)
  // как применить descending order?
  //  import play.api.libs.json.{JsArray, JsObject, JsValue, Json}
  //  object JsonSorter {
  //    def sort(json: String): String = {
  //      val root: JsValue = Json.parse(json)
  //      val newRoot = sortElements(root)
  //      newRoot.toString
  //    }
  //
  //    def sortElements(root: JsValue): JsValue = root match {
  //      case obj: JsObject => JsObject(obj.fields.sortBy(e => e._18.isInstanceOf[JsObject]).map(t => (t._1, sortElements(t._2))))
  //      case array: JsArray => JsArray(array.value.map(e => sortElements(e)))
  //      case other => other
  //    }
  //  }
  //  val sortedAfricanCountries = sort(africanCountries)

  // вывести топ 10
  //  val top10 = sortedAfricanCountries.take(10)

  // финальный формат: name-> official, capital->1st value, area
}
