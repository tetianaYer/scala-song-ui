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
//
  val songListDatabase: StyleA = style("songListDatabase") (
    backgroundColor.lightgray,
    width(30%%),
    borderRadius(2.em),
    display.flex,
    flexDirection.row,
  )

  val songName: StyleA = style("song-name") (
    margin(10px, 10px)
  )

  val controlPanel: StyleA = style("control-panel") (
    backgroundColor.grey,
    width(70%%),
    display.flex,
    flexDirection.row,
    borderRadius(2.em)
  )

  val pageContainer: StyleA = style("page-container")(
    display.flex,
    flexDirection.column,
    padding(2.em, 2.em),
    width(100%%),
)

  val topPanel: StyleA = style("top-panel")(
  backgroundColor.beige,
    display.flex,
    flexDirection.row,
    padding(1.rem, 1.em),
    borderColor.black,
    borderRadius(1.em)
)

  val header: StyleA = style("header")(
    color.pink,
    backgroundColor.red,
    margin.auto,
    fontSize(62 px),
    backgroundImage := "radial-gradient(5em circle at top left, yellow, blue)",
    fontFamily.attr := "cursive"
  )

  val userlist: StyleA = style("userlist")(
    color.white,
    backgroundColor.grey,
    fontSize(20 px),
    border.white
  )
  val delete: StyleA = style("delete")(
    color.black,
    backgroundColor.orange,
    fontSize(16 px),
    border.orange,
    fontStyle.normal,
    fontWeight.bold,
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

  val inputField: StyleA = style("input-field")(
    color.white,
    fontSize(18 px)
  )

  val addSong: StyleA = style("add-song")(
    color.black,
    backgroundColor.lightyellow,
    fontSize(18 px),
//    border(2.px),
//    borderWidth(10.em),
    borderColor.black,
    width(50%%),
    justifyContent.left,
    borderRadius(2.em),
    paddingRight(5.em)
  )

  val users: StyleA = style("users")(
    color.black,
    backgroundColor.lightblue,
    fontSize(18 px),
    borderColor.black,
    width(100%%),
    justifyContent.left,
    borderRadius(2.em)
  )

  val ctaButton: StyleA = style("cta-button")(
    color.black,
    backgroundColor.lightgreen,
    borderColor.green,
    borderRadius(1.em),
    fontSize(20 px),
    fontStyle.normal,
    fontWeight.bold,
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
