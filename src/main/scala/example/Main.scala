package example

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.api.features.unitArrows
import com.raquo.laminar.receivers.ChildReceiver.text
import example.styles.GlobalStyles
import org.scalajs.dom
import org.scalajs.dom.document
import scalacss.StyleA
import scala.language.implicitConversions


@main
def helloWorld(): Unit =

  implicit def applyStyle(styleA: StyleA): Mod[HtmlElement] =
    cls := styleA.className.value

  val tickStream = EventStream.periodic(10)
  val harvDiv = div("harvey is a div")

  val genericButton = button(
    cls := "button",
    //              styleAttr := "--clr:#03FF3C",
    span(
      cls := "button-text",
      "►"
    ),
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
  val headerText = div("This is Header PAM PAM", GlobalStyles.header)
  val nameVar = Var(initial = "world")
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

  renderOnDomContentLoaded(dom.document.querySelector("#app"), appElement())

  def appElement(): HtmlElement =
    div(
      headerText,
      div(
        GlobalStyles.header,
        h1("Making UIs!!!", cls := "home-div"),
        h2("Scala.js compiles Scala code into JavaScript.🤓🤓😜🧐🥸"),
        hr(),
        h1("Hello with Vite & Laminar!"),
        harvDiv,
        hr(),
        p("test2", cls := "cheese"),
        genericButton,
        hr(),
        renderMultiLineString(),
        img(
          src := "https://www.scala-js.org/assets/img/scala-js-logo.svg",
          alt := "Scala logo",
          widthAttr := 100,
          heightAttr := 100,
          styleAttr <-- tickStream.map(tick => s"filter: hue-rotate(${tick}deg); transform: translate(${Math.cos(tick / 33.0) * 180}px, ${Math.sin(tick / 99.0) * 100}px) rotate(${tick}deg)")
        ),
        div(
          label("Your name: "),
          input(
            placeholder := "Enter your name here",
            onInput.mapToValue --> nameVar
          ),
          p(
            "Hello, ",
            text <-- nameVar.signal.map(_.toUpperCase)
          )
        ),
        button1,
        h3("Pink hairbrush 30cm🤨")
      ))


end helloWorld
