package example

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.api.features.unitArrows
import com.raquo.laminar.receivers.ChildReceiver.text
import example.styles.GlobalStyles
import org.scalajs.dom
import org.scalajs.dom.{document, window}
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


  /// END //////




  val harvDev = div("harvey is a dev.. but a nice dev")

  val genericButton = button(
    className := "button",

    //              styleAttr := "--clr:#03FF3C",
    span(
      cls := "button-text",
      "►"
    ),
    idAttr := "i-am " + "-the id",
    onClick -->
      println("Who said you could push me fool")
  )

  val button1 = button(
    cls := "button",
    span(
      cls := "button-text",
      "🛑🥊"
    ),
    onClick --> println("Button clicked!")
  )

  val ArranIsButtingOn = button(
    className := GlobalStyles.button53.className.value,
    idAttr := "Arran-button",
    span(
      cls := "Butt-on",
      "Arran-Why"
    ),
    onClick --> println("AAAAA YOU BROKE IT AAAAA!!"),
    onClick --> window.alert("ello")
  )
  val headerText = div("This is Header PAM PAM", GlobalStyles.header.toClassName)
  val nameVar = Var(initial = "world")
  val scalaJs2 =
    div("generating ascii art...", GlobalStyles.terminal)
  def renderMultiLineString(): HtmlElement = {
    val multiLineString =
      """          _____                    _____                    _____                    _____            _____                            _____                    _____
        |         /\    \                  /\    \                  /\    \                  /\    \          /\    \                          /\    \                  /\    \
        |        /::\    \                /::\    \                /::\    \                /::\____\        /::\    \                        /::\    \                /::\    \
        |       /::::\    \              /::::\    \              /::::\    \              /:::/    /       /::::\    \                       \:::\    \              /::::\    \
        |      /::::::\    \            /::::::\    \            /::::::\    \            /:::/    /       /::::::\    \                       \:::\    \            /::::::\    \
        |     /:::/\:::\    \          /:::/\:::\    \          /:::/\:::\    \          /:::/    /       /:::/\:::\    \                       \:::\    \          /:::/\:::\    \
        |    /:::/__\:::\    \        /:::/  \:::\    \        /:::/__\:::\    \        /:::/    /       /:::/__\:::\    \                       \:::\    \        /:::/__\:::\    \
        |    \:::\   \:::\    \      /:::/    \:::\    \      /::::\   \:::\    \      /:::/    /       /::::\   \:::\    \                      /::::\    \       \:::\   \:::\    \
        |  ___\:::\   \:::\    \    /:::/    / \:::\    \    /::::::\   \:::\    \    /:::/    /       /::::::\   \:::\    \            _____   /::::::\    \    ___\:::\   \:::\    \
        | /\   \:::\   \:::\    \  /:::/    /   \:::\    \  /:::/\:::\   \:::\    \  /:::/    /       /:::/\:::\   \:::\    \          /\    \ /:::/\:::\    \  /\   \:::\   \:::\    \
        |/::\   \:::\   \:::\____\/:::/____/     \:::\____\/:::/  \:::\   \:::\____\/:::/____/       /:::/  \:::\   \:::\____\        /::\    /:::/  \:::\____\/::\   \:::\   \:::\____\
        |\:::\   \:::\   \::/    /\:::\    \      \::/    /\::/    \:::\  /:::/    /\:::\    \       \::/    \:::\  /:::/    /        \:::\  /:::/    \::/    /\:::\   \:::\   \::/    /
        | \:::\   \:::\   \/____/  \:::\    \      \/____/  \/____/ \:::\/:::/    /  \:::\    \       \/____/ \:::\/:::/    /          \:::\/:::/    / \/____/  \:::\   \:::\   \/____/
        |  \:::\   \:::\    \       \:::\    \                       \::::::/    /    \:::\    \               \::::::/    /            \::::::/    /            \:::\   \:::\    \
        |   \:::\   \:::\____\       \:::\    \                       \::::/    /      \:::\    \               \::::/    /              \::::/    /              \:::\   \:::\____\
        |    \:::\  /:::/    /        \:::\    \                      /:::/    /        \:::\    \              /:::/    /                \::/    /                \:::\  /:::/    /
        |     \:::\/:::/    /          \:::\    \                    /:::/    /          \:::\    \            /:::/    /                  \/____/                  \:::\/:::/    /
        |      \::::::/    /            \:::\    \                  /:::/    /            \:::\    \          /:::/    /                                             \::::::/    /
        |       \::::/    /              \:::\____\                /:::/    /              \:::\____\        /:::/    /                                               \::::/    /
        |        \::/    /                \::/    /                \::/    /                \::/    /        \::/    /                                                 \::/    /
        |         \/____/                  \/____/                  \/____/                  \/____/          \/____/                                                   \/____/
        |                                                                                                                                                                               """.stripMargin
    pre(
      code(
        multiLineString
      )
    )
  }



  def appElement2(): HtmlElement =
    div(
      headerText,
      div(
        h1("Making UIs!!!", cls := "home-div"),
        h2("Scala.js compiles Scala code into JavaScript.🤓🤓😜🧐🥸"),
        hr(),
        h1("Hello with Vite & Laminar!"),
        harvDev,
        hr(),
        p("test2", cls := "cheese"),
        genericButton,
        ArranIsButtingOn,
        hr(),
        scalaJs2,
        renderMultiLineString(),
        img(
          src := "https://www.scala-js.org/assets/img/scala-js-logo.svg",
          alt := "Scala logo",
          widthAttr := 100,
          heightAttr := 100,
          styleAttr <-- tickStream.map(tick =>
            s"filter: hue-rotate(${tick}deg); transform: translate(${Math.cos(
              tick / 33.0) * 180}px, ${Math.sin(tick / 99.0) * 100}px) rotate(${tick}deg)")
        ),
        img(
          src := "https://www.scala-js.org/assets/img/scala-js-logo.svg",
          GlobalStyles.harvAnim
        ),
        div(
          label("Your name: "),
          input(
            GlobalStyles.sun,
            placeholder := "Enter your name here",
            onInput.mapToValue --> nameVar
          ),
          p(
            "Hello, ",
            text <-- nameVar.signal.map(_.toUpperCase)
          )
        ),
        button1,
        div(
          p("isabella", cls := GlobalStyles.book.className.value)
        ),
        h2("Computer", cls := GlobalStyles.sky.className.value)
      )
    )
//     className := GlobalStyles.{insert style name here}.className.value,

end helloWorld
