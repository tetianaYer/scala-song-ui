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

  val pageContainer: StyleA = style("page-container")(
    display.flex,
    flexDirection.column,
    padding(1.em, 1.em),
    width(95%%),
    justifyContent.center,
  )

  val topPanel: StyleA = style("top-panel")(
    backgroundColor.beige,
    display.flex,
    flexDirection.row,
    width(95%%),
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

  val deleteIcon: StyleA = style("deleteIcon")(
    color.black,
    backgroundColor.red,
    fontSize(20 px),
    fontStyle.normal,
    fontWeight.bold,
    padding(0.5.rem, 0.8.rem),
    borderRadius(2.em),
  )

  val inputField: StyleA = style("input-field")(
    color.black,
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

  val userInfo: StyleA = style("userInfo")(
    color.black,
    backgroundColor.lightgreen,
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

  val ctaButton: StyleA = style("cta-button")(
    color.black,
    backgroundColor.lightgreen,
    borderColor.green,
    borderRadius(0.5.em),
    fontSize(20 px),
    fontStyle.normal,
    fontWeight.bold,
    padding(0.7.rem, 1.rem),
  )
}
