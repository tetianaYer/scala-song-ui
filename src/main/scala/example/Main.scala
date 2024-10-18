package example

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.api.features.unitArrows
import example.styles.GlobalStyles
import org.scalajs.dom
import org.scalajs.dom.document
import scalacss.ProdDefaults.*
import scalacss.StyleA
import scalacss.internal.mutable.GlobalRegistry

import scala.language.implicitConversions

@main
def helloWorld(): Unit =
  GlobalRegistry.addToDocumentOnRegistration()
  GlobalRegistry.register(GlobalStyles)

  extension (style: StyleA)
    def toClassName: Mod[HtmlElement] = className := style.className.value

  given Conversion[StyleA, Mod[HtmlElement]] =
    (styleA: StyleA) => className := styleA.className.value

  val tickStream = EventStream.periodic(10)

  ///// HERE ///


  def songListDatabaseRow(name: String, artist: String, genre: String, duration: String) = div(GlobalStyles.userPadding,
    table(GlobalStyles.userRow, GlobalStyles.userPadding, //heightAttr := 20,
      tbody(
        tr(
          td(p(s"$name"), GlobalStyles.songTableTextStyle, GlobalStyles.toTheLeft, widthAttr := 150),
          td(p(s"$artist"), GlobalStyles.songTableTextStyle, GlobalStyles.toTheLeft,  widthAttr := 100),
          td(p(s"$genre"), GlobalStyles.songTableTextStyle, GlobalStyles.toTheLeft,  widthAttr := 50),
          td(p(s"$duration"), GlobalStyles.songTableTextStyle, GlobalStyles.toTheLeft,  widthAttr := 50),
        )
      )
    )
  )

  val songListDatabase = div(GlobalStyles.songListDatabase,
    h2("Song List Database"),
    div(
      songListDatabaseRow("Follow you", "Imagine Dragons", "rock", "3:51"),
      songListDatabaseRow("Believer", "Imagine Dragons", "rock", "3:24"),
      songListDatabaseRow("Demons", "Imagine Dragons", "rock", "2:57"),
      songListDatabaseRow("Radioactive", "Imagine Dragons", "rock", "3:08"),
      songListDatabaseRow("Thunder", "Imagine Dragons", "rock", "3:07"),
    )
  )

  def deleteUser() = button(
    GlobalStyles.delete,
    span(
      cls := "button-text",
      "Delete user"
    ),
    onClick --> println("DELETE!!")
  )

  def userTable(name: String) = div(GlobalStyles.userPadding,
    table( GlobalStyles.userRow, GlobalStyles.userPadding,
      tbody(
        tr( GlobalStyles.userRow,
          td(p(s"$name"), GlobalStyles.userTableTextStyle, GlobalStyles.toTheLeft),
          td(GlobalStyles.toTheRight, GlobalStyles.columnWidth,
            deleteUser()
            )
        )
      )
    )
  )

  val userList = div(
    h3("Users:"),
    GlobalStyles.userlist,
    div(
      userTable("Taylor Swift"),
      userTable("Harry Potter"),
      userTable("Tetiana"),
      userTable("Table user")
    )
  )

  def inputField(labelText: String, placeholderText: String) = div(
    div(GlobalStyles.toTheRight, label(GlobalStyles.label, labelText)),
    div(
      input(
        GlobalStyles.inputField,
        placeholder := placeholderText
      )
    )
  )

  def ctaButton(text: String) = button(
    GlobalStyles.ctaButton,
    span(
      cls := "button-text",
      text
    ),
    onClick --> println("Button clicked!")
  )

  val addSongForm = div(
    GlobalStyles.addSongUserBoard,
    h3("Add song"),
    div( GlobalStyles.toTheLeft,
      inputField("Song title: ", "Enter song title"),
      inputField("Artist: ", "Enter artist name"),
      inputField("Duration: ", "Enter song duration")
    ),
    p(ctaButton("Add song"))
  )

  val addUserForm = div(
    GlobalStyles.addSongUserBoard,
    h3("Add user"),
    div(
      inputField("User name: ", "Enter user's name"),
      inputField("Age: ", "Enter user's age"),
      inputField("Favorite song: ", "Enter user's favorite song")
    ),
    p( ctaButton("Add user"))
  )

  val addCreateSong = div(
    GlobalStyles.pageContainer,
    div(GlobalStyles.topPanel,
      div(
        GlobalStyles.pageContainer,addSongForm),
      div(
        GlobalStyles.pageContainer,addUserForm)
    ),
    userList
  )


  def appElement(): HtmlElement =
    div(
      GlobalStyles.pageContainer,
      div(
        GlobalStyles.topPanel,
        idAttr := "app-container",songListDatabase,
        addCreateSong)
    )



  renderOnDomContentLoaded(dom.document.querySelector("#app"), appElement())
