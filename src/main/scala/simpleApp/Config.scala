package simpleApp


import scopt.OParser

case class Config(
    saveTo: String = ""
)

object Config {
  val builder = OParser.builder[Config]

  val parser1 = {
    import builder._
    OParser.sequence(
      programName("scopt"),
      head("scopt", "4.x"),
      // option -f, --foo
      opt[String]('s', "saveTo")
        .action((x, c) => c.copy(saveTo = x)) // (value, arguments) => arguments.copy(link = value)
        .text("file name processed")
    )
  }

  def parseArgs(args: Array[String]): Config =
    OParser
      .parse(parser1, args, Config())
      .getOrElse {
        sys.error("Could not parse arguments")
      }
}
