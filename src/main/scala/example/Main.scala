package example

import org.scalajs.dom
import org.scalajs.dom.document
import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.api.features.unitArrows
import AsciArt._
import example.styles.GlobalStyles
import scalacss.ProdDefaults._
import scalacss.StyleA
import scalacss.internal.mutable.GlobalRegistry

import scala.language.implicitConversions


@main
def helloWorld(): Unit =

  implicit def applyStyle(styleA: StyleA): Mod[HtmlElement] =
    cls := styleA.className.value

  val tickStream = EventStream.periodic(100)
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
  val headerText = div("This is Header", GlobalStyles.header)
  renderOnDomContentLoaded(dom.document.querySelector("#app"), appElement())

  def appElement(): HtmlElement =

    div(
      headerText,
      div(
        GlobalStyles.header,
//        p("Test", cls(GlobalStyles.header.className.value)),
        p("Test", GlobalStyles.header),
      hr(),
      h1("Hello with Vite & Laminar!"),
      harvDiv,
      hr(),
      p("test2", cls := "cheese"),
      genericButton,
      hr(),
      img(
        src := "https://www.scala-js.org/assets/img/scala-js-logo.svg",
        alt := "Scala logo",
        width := "130px",
        transform <-- tickStream.map(tick => s"translate(${Math.cos(tick/10.0) * 500}px, ${Math.sin(tick/5.0) * 150}px) rotate(${tick*5}deg)"),
//        filter <-- tickStream.map(tick => s"hue-rotate(${tick*10}deg)")
      ),
      div(asciArt)
    ))

  val homeDiv = document.createElement("div")
  homeDiv.classList.add("home-div")
  val heading = document.createElement("h1")
  heading.textContent = "Making UIs!!!"

  val button1 = document.createElement("button")
  button1.textContent = "🛑🥊"
  button1.addEventListener(
    "click",
    (_: dom.MouseEvent) =>
      println("Button clicked!"))

  val input = document.createElement("input")
  input.textContent = "Type something"

  val subheading = document.createElement("h2")
  subheading.textContent = "Scala.js compiles Scala code into JavaScript.🤓🤓😜🧐🥸"

  val itemTitle = document.createElement("h3")
  itemTitle.textContent = "Pink hairbrush 30cm🤨"

  homeDiv.appendChild(heading)
  homeDiv.appendChild(button1)
  homeDiv.appendChild(subheading)
  homeDiv.appendChild(itemTitle)
  homeDiv.appendChild(input)
  document.body.appendChild(homeDiv)
end helloWorld
////end helloWorld


object AsciArt {
//  val asciArt = "coming soon..."
  val asciArt: String =
    s"""          _____                    _____                    _____                    _____            _____                            _____                    _____ ${br()}
      |         /\\    \\                  /\\    \\                  /\\    \\                  /\\    \\          /\\    \\                          /\\    \\                  /\\    \\ ${br()}
      |        /::\\    \\                /::\\    \\                /::\\    \\                /::\\____\\        /::\\    \\                        /::\\    \\                /::\\    \\ ${br()}
      |       /::::\\    \\              /::::\\    \\              /::::\\    \\              /:::/    /       /::::\\    \\                       \\:::\\    \\              /::::\\    \\ ${br()}
      |      /::::::\\    \\            /::::::\\    \\            /::::::\\    \\            /:::/    /       /::::::\\    \\                       \\:::\\    \\            /::::::\\    \\ ${br()}
      |     /:::/\\:::\\    \\          /:::/\\:::\\    \\          /:::/\\:::\\    \\          /:::/    /       /:::/\\:::\\    \\                       \\:::\\    \\          /:::/\\:::\\    \\ ${br()}
      |    /:::/__\\:::\\    \\        /:::/  \\:::\\    \\        /:::/__\\:::\\    \\        /:::/    /       /:::/__\\:::\\    \\                       \\:::\\    \\        /:::/__\\:::\\    \\ ${br()}
      |    \\:::\\   \\:::\\    \\      /:::/    \\:::\\    \\      /::::\\   \\:::\\    \\      /:::/    /       /::::\\   \\:::\\    \\                      /::::\\    \\       \\:::\\   \\:::\\    \\ ${br()}
      |  ___\\:::\\   \\:::\\    \\    /:::/    / \\:::\\    \\    /::::::\\   \\:::\\    \\    /:::/    /       /::::::\\   \\:::\\    \\            _____   /::::::\\    \\    ___\\:::\\   \\:::\\    \\ ${br()}
      | /\\   \\:::\\   \\:::\\    \\  /:::/    /   \\:::\\    \\  /:::/\\:::\\   \\:::\\    \\  /:::/    /       /:::/\\:::\\   \\:::\\    \\          /\\    \\ /:::/\\:::\\    \\  /\\   \\:::\\   \\:::\\    \\ ${br()}
      |/::\\   \\:::\\   \\:::\\____\\/:::/____/     \\:::\\____\\/:::/  \\:::\\   \\:::\\____\\/:::/____/       /:::/  \\:::\\   \\:::\\____\\        /::\\    /:::/  \\:::\\____\\/::\\   \\:::\\   \\:::\\____\\ ${br()}
      |\\:::\\   \\:::\\   \\::/    /\\:::\\    \\      \\::/    /\\::/    \\:::\\  /:::/    /\\:::\\    \\       \\::/    \\:::\\  /:::/    /        \\:::\\  /:::/    \\::/    /\\:::\\   \\:::\\   \\::/    / ${br()}
      | \\:::\\   \\:::\\   \\/____/  \\:::\\    \\      \\/____/  \\/____/ \\:::\\/:::/    /  \\:::\\    \\       \\/____/ \\:::\\/:::/    /          \\:::\\/:::/    / \\/____/  \\:::\\   \\:::\\   \\/____/ ${br()}
      |  \\:::\\   \\:::\\    \\       \\:::\\    \\                       \\::::::/    /    \\:::\\    \\               \\::::::/    /            \\::::::/    /            \\:::\\   \\:::\\    \\ ${br()}
      |   \\:::\\   \\:::\\____\\       \\:::\\    \\                       \\::::/    /      \\:::\\    \\               \\::::/    /              \\::::/    /              \\:::\\   \\:::\\____\\ ${br()}
      |    \\:::\\  /:::/    /        \\:::\\    \\                      /:::/    /        \\:::\\    \\              /:::/    /                \\::/    /                \\:::\\  /:::/    / ${br()}
      |     \\:::\\/:::/    /          \\:::\\    \\                    /:::/    /          \\:::\\    \\            /:::/    /                  \\/____/                  \\:::\\/:::/    / ${br()}
      |      \\::::::/    /            \\:::\\    \\                  /:::/    /            \\:::\\    \\          /:::/    /                                             \\::::::/    / ${br()}
      |       \\::::/    /              \\:::\\____\\                /:::/    /              \\:::\\____\\        /:::/    /                                               \\::::/    / ${br()}
      |        \\::/    /                \\::/    /                \\::/    /                \\::/    /        \\::/    /                                                 \\::/    / ${br()}
      |         \\/____/                  \\/____/                  \\/____/                  \\/____/          \\/____/                                                   \\/____/ ${br()}
      |                                                                                                                                                                               """.stripMargin
}
