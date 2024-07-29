package example.styles

import com.raquo.laminar.api.L.{HtmlElement, Mod, cls}
import scalacss.ProdDefaults.*
import scalacss.internal.mutable.GlobalRegistry
import scala.concurrent.duration.*

import scala.language.{implicitConversions, postfixOps}

object GlobalStyles extends StyleSheet.Inline {

  import dsl.*

//  implicit def applyStyle(styleA: StyleA): Mod[HtmlElement] =
//    cls(styleA.className.value)

  val header: StyleA = style("header")(
    color.pink,
    backgroundColor.red,
    margin.auto,
    fontSize(62 px),
    backgroundImage := "radial-gradient(5em circle at top left, yellow, blue)",
    fontFamily.attr := "cursive"
  )

  val sky: StyleA = style("sky")(
    color.white,
    backgroundColor.skyblue,
    fontSize(24 px),
    border.blue
  )

  val book: StyleA = style("book")(
    color.lightpink,
    backgroundColor.antiquewhite,
    border.cornflowerblue,
    fontSize(18 px)
  )
  val myAnimation = keyframes(
    0.%% -> keyframe(color.blue),
    50.%% -> keyframe(color.yellow),
    100.%% -> keyframe(color.green)
  )
  val sun: StyleA = style("sun")(
    color.orangered,
    backgroundColor.white,
    fontSize(20 px),
    animationName(myAnimation),
    animationDuration(2 seconds),
    animationIterationCount.infinite
  )

  val animPartOne = keyframe(
    height(300 px),
    width(300 px))

  val animPartTwo = keyframe(
    height(150 px),
    width(150 px))

  val kf1 = keyframes(
    (0 %%) -> animPartOne,
    (50 %%) -> animPartTwo,
    (100 %%) -> keyframe(
      height(300 px),
      width(300 px))
  )

  val harvAnim: StyleA = style(
    animationName(kf1),
    animationDuration(2 seconds),
    animationIterationCount.infinite
  )

  val terminal: StyleA = style("terminal")(
    color.white,
    backgroundColor.black,
    fontSize(20 px),
    border.grey
  )

  val button53 = style(
    backgroundColor(c"#3DD1E7"),
    border(0.px, solid, c"#E5E7EB"),
    boxSizing.borderBox,
    color.black,
    display.flex,
    fontFamily :=! "ui-sans-serif,system-ui,-apple-system,system-ui,\"Segoe UI\",Roboto,\"Helvetica Neue\",Arial,\"Noto Sans\",sans-serif,\"Apple Color Emoji\",\"Segoe UI Emoji\",\"Segoe UI Symbol\",\"Noto Color Emoji\"",
    fontSize(1.rem),
    fontWeight.bold,
    justifyContent.center,
    lineHeight(1.75.rem),
    padding(0.75.rem, 1.65.rem),
    position.relative,
    textAlign.center,
    textDecoration := "none",
    textDecorationColor.black,
    width(100 %%),
    maxWidth(460.px),
    cursor.pointer,
    transform := "rotate(-2deg)",
    userSelect := "none",
    unsafeChild(":after")(
      content := "''",
      position.absolute,
      border(1.px, solid, c"#000000"),
      bottom(4.px),
      left(4.px)
    ),
    &.hover(
      unsafeChild(":after")(
        bottom(2.px),
        left(2.px)
      )
    ),
    media.minWidth(768.px)(
      padding(0.75.rem, 3.rem),
      fontSize(1.25.rem)
    ),
    &.focus(
      outline := "0"
    )
  )
}
