package resume

import ammonite.ops._
import Styles._
import Styles.sheet._
import scalatags.Text.all._
object Resume{
  def dataUri(filepath: Path) = {
    "data:image/png;base64," +
    javax.xml.bind.DatatypeConverter.printBase64Binary(
      read.bytes! filepath
    )
  }
  def main(args: Array[String]) = {

    def autolink(url: String) = a(url, href:=url)
    def row = div(display.flex, flexDirection := "row")
    def col = div(display.flex, flexDirection := "column")
    def titledBlock(title: String, loc: String, bullets: Frag*) = div(
      row(
        h3(roleText, title),
        div(rightGreyText, loc)
      ),
      ul(
        listBlock,
        bullets.map(li(listItem, _))
      )
    )
    def quickBullet(lhs: String, rhs: String) = tr(
      td(div(para, roleText, paddingRight := 5, lhs)),
      td(para, rhs)
    )
    def logo(s: String) = {
      img(height := 15, src := dataUri(cwd/'images/s))
    }
    def section(title: String, body: Frag) = tr(

      td(h2(paddingTop := 10, paddingBottom := 10, sectionHeading, title, marginRight := 20)),
      td(paddingTop := 10, paddingBottom := 10, body)
    )
    def talk(name: String, loc: String, video: String) = div(
      row(h3(roleText, name), div(rightGreyText, loc)),
      ul(listBlock, li(listItem, autolink(video)))
    )


    val blob = html(
      fontFamily := "Calibri, Candara, Segoe, 'Segoe UI', Optima, Arial, sans-serif",
      head(
        scalatags.Text.tags2.style(raw(cssReset)),
        scalatags.Text.tags2.style(raw(sheet.styleSheetText))
      ),
      body(
        width := 720,
        boxSizing.`border-box`,
        padding := 30,
        row(
          width := "100%",
            div(
              width := "50%",
              h1(nameText, "Li Haoyi")
            ),
            col(
              width := "50%",
              div(textAlign.right, greyText, "haoyi.sg@gmail.com"),
              div(
                textAlign.right,
                greyText,
                a("http://www.github.com/lihaoyi", href:="http://www.github.com/lihaoyi")
              )
            )
          )
        ),
        hr,
        table(
          width := "100%",
          section(
            "Work",
            col(
              row(h2(sectionHeading, "Dropbox"), logo("Dropbox.png"), div(rightGreyText, "San Francisco, CA")),
              titledBlock(
                "Software Engineer, Developer Tools", "Mar 2015 - Present",
                """
                Prototyped, implemented and (gradually) rolled out an improved dev
                workflow to all of engineering, reducing edit-refresh latency from 40s
                to 13s for Python and 15s to 5s for Coffeescript
                """,
                """
                Led a team of 2-3 to destroy flaky tests in our CI system; via research,
                monitoring, and automatic quarantine, we reduced the number of builds
                failing due to flaky tests from ~10% to <1%
                """,
                """Helped manage our CI system comprising >3000 CI machines; performing
                on-call duties, manual scaling up/down, cluster-wide distributed
                trouble-shooting and recovery
                """
              ),
              titledBlock(
                "Software Engineer, Web Platform", "Mar 2014 - Mar 2015",
                """
                Set up selenium test infrastructure (> 300 CI machines), created a
                streamlined developer workflow, and got it adopted across engineering.
                Reduced the number of bugs reaching production by 5-10 a week
                """,
                """
                Modularized our >100,000 lines of Coffeescript, moving from a 'concat
                em' build system to Require.js modules
                """,
                """
                Implemented static analysis tooling to enforce conventions in Python and
                Coffeescript, allowing fast refactoring without fear
                """,
                """
                Designed and implemented an auto-packager, which uses machine
                learning on a historical sample of page-views to optimize bundling
                Javascript modules together for download. reduced
                the number of files loaded on the home page from ~250 to ~50
                """
              ),
              titledBlock(
                "Software Engineer, Dropbox for Business", "Aug 2013 - Mar 2014",
                """
                Worked on two-account migration for www.dropbox.com, two-account
                pairing flow
                """,
                """
                Performance improvements for most common pages, including speeding up the
                home page by 6x
                """
              )
            )
        ),
        section(
          "Open Source",
          col(
            row(h2(sectionHeading, "Scala.js"), logo("ScalaJS.png")),
            titledBlock(
              "Core Contributor", "Sep 2013 - Present",
              p(para,
                "Scala.js (", autolink("http://www.scala-js.org/"), ") is a compiler that ",
                "converts Scala code into equivalent, executable Javascript. It allows you ",
                "to write websites in an expressive, type-safe language and share code between ",
                "client and server. Scala.js has thousands of people ",
                "using it for real work, and a healthy ecosystem of open-source libraries."
              ),
              p(para,
                "I am not the author of the original Scala.js project (that's Sebastien Doraene)",
                ", but I can confidently say it would not exist as it does today without my help."
              )
            ),
            titledBlock(
              "Early Adopter", "",
              div(
                "Pioneered usage of Scala.js, demonstrating its effectiveness in writing ",
                "real-world projects through ", autolink("https://lihaoyi.github.io/scala-js-games"), " and ",
                autolink("https://lihaoyi.github.io/roll")
              ),
              """
              Pushed for several usability improvements that helped make Scala.js actually
              usable (edit-refresh speed, debuggability, etc.)
              """,
              "Contributed a dozen commits and many more bug-reports in the process."
            ),
            titledBlock(
              "Community", "",
              """
              Built the community from nothing, to thousands of active users.
              I presented at dozens of conferences and meetups to show off Scala.js and
              attract interest in the project.
              """,
              div(
                "Highlights include ", i(talkName, "Live Coding Scala.js"), ", Scala.js' ",
                "debut talk at the SF Scala Meetup, ",
                "which has been watched over 5,000 times. My Scala.js-related videos ",
                "have had >20,000 views on all platforms. Full listing below."
              ),
              div(
                "Wrote the free, interactive e-book ", i(talkName, "Hands-on Scala.js"), " (",
                autolink("http://www.hands-on-scala-js.com"), ")"
              ),
              """
              Scala by the Bay 2015 had five talks about Scala.js, none of them
              by me. The community has a life of its own!
              """
            ),
            titledBlock(
              "Ecosystem", "",
              """
              Initially built the Scala.js library ecosystem from nothing. A compiler or
              language without libraries is useless, and I built out enough of them that
              people coming to Scala.js could use it to do real work.
              """,
              div(
                "In total my libraries are getting >40,000 downloads a month from the package ",
                "repository, and every Scala.js project in existence uses some of them. See ",
                autolink("https://github.com/lihaoyi"), " to find out more."
              )
            )
          )
        ),
        section("Buzzwords",
          div(listBlock,
            table(
              quickBullet(
                "Programming Languages",
                "Scala, Python, Coffeescript, Javascript, CSS, Java, C#, F#, PHP, Ruby"
              ),
              quickBullet(
                "Frameworks",
                "Flask, React.js, Underscore.js, jQuery, Play, Spray"
              ),
              quickBullet(
                "Areas of Interest",
                "Compilers, FRP, Parser Combinators, Sandboxing, Web Dev, Game Dev"
              )
            )
          )
        ),
        tr(td(colspan := 2, div(scalatags.Text.styles2.pageBreakAfter.always))),
        section(
          "Ancient History",
          col(
            div(
              row(
                h2(sectionHeading, "Massachusetts Institute of Technology"),
                // Override height to compensate for non-square image
                logo("MIT.png")(height := 12, paddingTop := 4),
                div(rightGreyText, "Cambridge, MA")
              ),
              titledBlock(
                "Undergraduate Computer Science, GPA 4.8/5.0", "Sep 2010 - Jun 2013"
              )
            ),
            div(
              row(h2(sectionHeading, "Dropbox"), logo("Dropbox.png"), div(rightGreyText, "San Francisco, CA")),
              titledBlock(
                "Software Engineer Intern, API Team", "May 2012 - Aug 2012",
                """
                Built, documented and launched infrastructure to allow API apps to upload
                large files to Dropbox
                """,
                """
                Wrote unit tests, improved documentation and set up continuous
                integration for the Dropbox client SDKs
                """
              )
            ),
            div(
              row(h2(sectionHeading, "Facebook"), logo("Facebook.jpg"), div(rightGreyText, "Palo Alto, CA")),
              titledBlock(
                "Software Engineer Intern, Messaging Team", "May 2011 - Aug 2011",
                "Built an extensible attachment framework for Facebook Messages."
              )
            ),
            div(
              row(
                h2(sectionHeading, "Singapore Armed Forces"),
                logo("SAF.png"),
                div(rightGreyText, "Singapore")
              ),
              titledBlock(
                "Lieutenant, Platoon Commander, 35th Battalion Singapore Combat Engineers",
                "Jan 2008 - Nov 2009",
                """
                Managed the platoon's discipline and wellbeing, and their
                proficiency at operating military bridges.
                """,
                "Took part in combined-arms field exercises"
              )
            )
          )
        ),
        section(
          "Reference",
          col(
            div(
              row(h2(sectionHeading, "Projects"), logo("Github.png")),
              div(listBlock,
                p(para,
                  "Other cool projects i've worked on that are worth checking out!"
                )
              ),
              titledBlock(
                "Ammonite", "Jan 2015 - Present",
                """
              Ammonite explores the possibilities of writing a system-shell that uses a
              modern, type-safe, high-level language
                """,
                autolink("https://github.com/lihaoyi/Ammonite")
              ),
              titledBlock(
                "MacroPy", "Apr 2013 - Aug 2013",
                """
              What if syntactic macros were available in a language like Python
              instead of weird obscure ones like MIT-Scheme?
                """,
                autolink("https://github.com/lihaoyi/macropy")
              ),
              titledBlock(
                "Scala.Rx", "Dec 2012 - Aug 2014",
                """
              A FRP library that provides automatic change-propagation in any Scala
              application.
              their sources
                """,
                autolink("https://github.com/lihaoyi/scala.rx")
              ),
              titledBlock(
                "Metascala", "Feb 2013 - Dec 2013",
                """
                A JVM implemented in 3000 lines of code, in Scala on the JVM! Can interpret
                any Java bytecode, including itself!
                """,
                autolink("https://github.com/lihaoyi/Metascala")
              )
            ),
            div(
              marginTop := 20,
              row(h2(sectionHeading, "Talks"), logo("GoogleSlides.png")),
              talk(
                "Metascala: a tiny DIY JVM",
                "Scala Exchange 2 Dec 2013",
                "https://skillsmatter.com/skillscasts/4916-metascala-a-tiny-diy-jvm"
              ),
              talk(
                "Live-Coding Scala.js",
                "SF Scala Meetup 28 Feb 2014",
                "https://vimeo.com/87845442"
              ),
              talk(
                "Fun Functional-Reactive Programming with Scala.Rx",
                "Scaladays 17 June 2014",
                "https://vimeo.com/98477272"
              ),
              talk(
                "Cross-platform development with Scala.js",
                "Scala by the Bay 9 August 2014",
                "https://www.youtube.com/watch?v=Ksoi6AG9nbA"
              ),
              talk(
                "Hands-On Scala.js",
                "Pacific-North-West Scala 14 Nov 2014",
                "https://vimeo.com/111978847"
              ),
              talk(
                "Bootstrapping the Scala.js Ecosystem",
                "Scala Exchange 7 Dec 2014",
                "https://vimeo.com/113967983"
              ),
              talk(
                "Scala.js - Safety & Sanity in the wild west of the web",
                "PhillyETE 8 March 2015",
                "https://vimeo.com/124702603"
              ),
              talk(
                "Why (You might like) Scala.js",
                "Scaladays 17 March 2015",
                "https://vimeo.com/122611959"
              ),
              talk(
                "Beyond Bash",
                "Scala by the Bay 12 August 2015",
                "Video not yet published"
              )

            )
          )
        )
      )
    )
    write.over(cwd/'target/"resume.html", blob.render)
  }
}