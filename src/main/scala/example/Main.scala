package example

import org.scalajs.dom
import org.scalajs.dom.document
import com.raquo.laminar.api.L.{*, given}

@main
def helloWorld(): Unit =
  renderOnDomContentLoaded(dom.document.querySelector("#app"), appElement())

  def appElement(): HtmlElement =
    div(
      h1("Hello with Vite & Laminar!")
    )

  val scalaLogo: dom.html.Image = document.createElement("img").asInstanceOf[dom.html.Image]
  scalaLogo.src = "https://www.scala-js.org/assets/img/scala-js-logo.svg"
  scalaLogo.alt = "Scala logo"
  scalaLogo.width = 100
  scalaLogo.height = 100

  val homeDiv = document.createElement("div")
  homeDiv.classList.add("home-div")
  val heading = document.createElement("h1")
  heading.textContent = "Making UIs!!!"

  val button1 = document.createElement("button")
  button1.textContent = "Click me!"
  button1.addEventListener(
    "click",
    (_: dom.MouseEvent) =>
      println("Button clicked!"))

  val input = document.createElement("input")
  input.textContent = "Type something"

  val subheading = document.createElement("h2")
  subheading.textContent = "Scala.js compiles Scala code into JavaScript."

  val itemTitle = document.createElement("h3")
  itemTitle.textContent = "Pink hairbrush 30cm"

  val asciArt = document.createElement("p")
  asciArt.textContent =
    """          _____                    _____                    _____                    _____            _____                            _____                    _____ \n
      |         /\    \                  /\    \                  /\    \                  /\    \          /\    \                          /\    \                  /\    \\n
      |        /::\    \                /::\    \                /::\    \                /::\____\        /::\    \                        /::\    \                /::\    \\n
      |       /::::\    \              /::::\    \              /::::\    \              /:::/    /       /::::\    \                       \:::\    \              /::::\    \\n
      |      /::::::\    \            /::::::\    \            /::::::\    \            /:::/    /       /::::::\    \                       \:::\    \            /::::::\    \\n
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

  homeDiv.appendChild(heading)
  homeDiv.appendChild(button1)
  homeDiv.appendChild(subheading)
  homeDiv.appendChild(scalaLogo)
  homeDiv.appendChild(itemTitle)
  homeDiv.appendChild(asciArt)
  homeDiv.appendChild(input)
  document.body.appendChild(homeDiv)
end helloWorld
////end helloWorld
