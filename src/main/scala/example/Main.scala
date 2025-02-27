package example

import com.raquo.airstream.ownership.Owner
import com.raquo.airstream.web.FetchOptions
import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.api.features.unitArrows
import com.raquo.laminar.receivers.ChildReceiver.text
import example.models.{Song, SongResponse}
import example.styles.GlobalStyles
import org.scalajs.dom
import org.scalajs.dom.document
import scalacss.ProdDefaults.*
import scalacss.StyleA
import scalacss.internal.mutable.GlobalRegistry
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.language.implicitConversions


@main
def helloWorld(): Unit =
  GlobalRegistry.addToDocumentOnRegistration()
  GlobalRegistry.register(GlobalStyles)
  implicit val owner: Owner = new Owner {}

  extension (style: StyleA)
    def toClassName: Mod[HtmlElement] = className := style.className.value

  given Conversion[StyleA, Mod[HtmlElement]] =
    (styleA: StyleA) => className := styleA.className.value


  val postSongResp = Var("")


  def ctaButtonAddSong(text: String, songTitle: Var[String], songDuration: String, artist: String) = button(
    GlobalStyles.ctaButton,
    span(
      cls := "button-text",
      text
    ),
    onClick.flatMap { _ =>
      dom.window.alert(songTitle.now())
      println(songTitle.now())
      val songStub = Song("3.67", "Killer", "Queen")
      val song = Song("3.67", songTitle.now(), "Queen")
      dom.window.alert(s"Song length: ${song.length}")
      val json = upickle.default.write[Song](song)
      dom.window.alert("1")
      val res = FetchStream.post("http://127.0.0.1:8081/v1/songs", _.body(json))
      dom.window.alert("2")
      res
    } --> Observer[String] { responseText =>
      dom.window.alert("3")
      dom.window.alert(s"Response: $responseText")
      //val response: String = upickle.default.read[SongResponse](responseText).text
      //dom.console.log(response)
      //postSongResp.set(response)
    }
  )

  val nameVar = Var(Song)
  def TextInput(): Input = input(typ := "text")

  case class FormState(title: String, artist: String, duration: String)

  val formStateVar = Var(FormState("", "", ""))
  val songVar = formStateVar.zoom(_.title)((state, newTitle) => state.copy(title = newTitle))

  val submitter = Observer[FormState] { state =>
    //ctaButtonAddSong("Add song", state.title, state.duration, state.artist)
    dom.window.alert(s"Song title: ${state.title}")
  }

  val addSongForm = div(
    GlobalStyles.addSongUserBoard,
    h3("Add song"),
    div(GlobalStyles.toTheLeft,
      form(
        onSubmit.mapTo(formStateVar.now()) --> submitter,
        div(
          div(GlobalStyles.toTheRight, label(GlobalStyles.label, "Song title: ")),
          div(
            input(
              GlobalStyles.inputField,
              placeholder := "Enter song title",
              value <-- songVar,
              onInput.mapToValue --> songVar
            )
          )
        ),
        p("Song title: ", text <-- songVar.signal),
      )
    ),
    p(ctaButtonAddSong("Add song", songVar, "state.duration", "state.artist"))
  )

  val addCreateSong = div(
    GlobalStyles.pageContainer,
    div(GlobalStyles.topPanel,
      div(
        GlobalStyles.pageContainer,addSongForm),
    )
  )

  def appElement(): HtmlElement =
    div(
      GlobalStyles.pageContainer,
      div(
        GlobalStyles.topPanel,
        idAttr := "app-container",
        addCreateSong)
    )

  renderOnDomContentLoaded(dom.document.querySelector("#app"), appElement())
