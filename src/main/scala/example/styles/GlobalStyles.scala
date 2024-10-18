package example.styles

import com.raquo.laminar.api.L.{HtmlElement, Mod, cls}
import scalacss.ProdDefaults.*
import scalacss.internal.mutable.GlobalRegistry
import scala.concurrent.duration.*

import scala.language.{implicitConversions, postfixOps}

object GlobalStyles extends StyleSheet.Inline {

  import dsl.*

  val songListDatabase: StyleA = style("songListDatabase") (
    backgroundColor.lightgray,
    width(100%%),
    borderRadius(1.em),
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
    padding(1.em, 1.em),
    width(100%%),
    justifyContent.center,
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
//    fontSize(20 px),
//    padding(1.em, 2.em),
    width(100%%),
    borderRadius(1.em),
  )

  val userTableTextStyle: StyleA = style("userTableTextStyle")(
    color.black,
    fontSize(20 px),
    paddingRight(10.em)
  )

  val songTableTextStyle: StyleA = style("songTableTextStyle")(
    color.black,
    paddingRight(1.em),
    padding(0.001.em, 0.1.em),
  )

  val horizontal: StyleA = style("horizontal")(
    display.flex,
    flexDirection.row,
  )

  val vertical: StyleA = style("vertical")(
    display.flex,
    flexDirection.column,
  )

  val toTheLeft: StyleA = style("toTheLeft")(
    justifyContent.left,
  )

  val label: StyleA = style("label")(
    color.black,
    fontSize(15 px),
    justifyContent.left,
  )

  val columnWidth: StyleA = style("columnWidth")(
    width(200px),
  )

  val toTheRight: StyleA = style("toTheRight")(
    justifyContent.right,
  )

  val delete: StyleA = style("delete")(
    color.black,
    backgroundColor.red,
    fontSize(20 px),
    fontStyle.normal,
    fontWeight.bold,
    padding(1.rem, 1.rem),
    borderRadius(1.em),
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
    fontSize(18 px),
    width(80%%),
    padding(0.5.rem, 0.5.rem),
  )

  val addSongUserBoard: StyleA = style("addSongUserBoardg")(
    color.black,
    backgroundColor.lightyellow,
    fontSize(18 px),
    borderColor.black,
    width(100%%),
    justifyContent.center,
    borderRadius(1.em),
  )

  val addSongUserForm: StyleA = style("addSongUserForm")(
    color.black,
    fontSize(15 px),
    width(100%%),
    padding(1.rem, 1.rem),
    borderRadius(1.em),
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

  val userRow: StyleA = style("userRow")(
    color.black,
    backgroundColor.white,
//    fontSize(10 px),
    borderColor.black,
    borderWidth(1.px),
    borderStyle.solid,
    width(100%%),
//    justifyContent.left,
    padding(1.rem, 1.rem),
    boxSizing.borderBox,
    borderRadius(1.em),
    height(3.em),
  )

  val userPadding: StyleA = style("userPadding")(
    padding(0.1.rem, 0.3.rem),
  )

  val userRowPadding: StyleA = style("userRowPadding")(
    paddingRight(30.rem),
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
