package example.styles

import com.raquo.laminar.api.L.{HtmlElement, Mod, cls}
import scalacss.ProdDefaults.*
import scalacss.internal.mutable.GlobalRegistry

import scala.language.{implicitConversions, postfixOps}

object GlobalStyles extends StyleSheet.Inline {

  import dsl.*

  implicit def applyStyle(styleA: StyleA): Mod[HtmlElement] =
    cls(styleA.className.value)

  val header: StyleA = style("header")(
    color.pink,
    backgroundColor.red,
    margin.auto,
    fontSize(62 px),
    backgroundImage := "radial-gradient(5em circle at top left, yellow, blue)"
  )
}
