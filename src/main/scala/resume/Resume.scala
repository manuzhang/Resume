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

    def autolink(url: String) = a(url.stripPrefix("https://").stripPrefix("http://"), href:=url)
    def row = div(display.flex, flexDirection := "row")
    def col = div(display.flex, flexDirection := "column")
    def titledBlock(title: String, loc: String, bullets: Frag*) = div(
      row(
        h3(roleText, title),
        div(rightGreyText, loc)
      ),
      bulletList(bullets:_*)
    )
    def bulletList(bullets: Frag*) = ul(
      listBlock,
      bullets.map(li(listItem, _))
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
              h1(nameText, "Tianlun(Manu) Zhang")
            ),
            col(
              width := "50%",
              div(textAlign.right, greyText, "owenzhang1990@gmail.com"),
              div(
                textAlign.right,
                greyText,
                autolink("http://www.github.com/manuzhang")
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
              row(h2(sectionHeading, "Vipshop"), div(rightGreyText, "Shanghai")),
               titledBlock(
                "Senior Software Engineer, Machine Learning Platform", "Aug 2017 - present",
                """
                Lead the developement of machine learning platform, an online workspace for data engineers and 
                data scientists to develop machine learning workloads interactively and efficiently. 
                """,
                """
                Built a drag-and-drop editor where newbies can define a machine learning pipeline without 
                writing any codes and experts can make use of data exploration and visualization tools to get 
                insights of data and models.
                """,
                """
                Developed stream processing jobs to prepare real-time data for model training, 
                which improved freshness of trained models.
                """
              ),
              row(h2(sectionHeading, "Intel"), logo("Intel.png"), div(rightGreyText, "Shanghai")),
              titledBlock(
                "Software Engineer, Gearpump", "Jan 2015 - Aug 2017",
                """
                Core committer to Gearpump, a real-time Big Data engine on Akka.
                Developed Kafka connectors, Storm compatibility layer and transaction APIs.
                """
              ),
              titledBlock(
                "Software Engineer, Intel Hadoop Distribution", "July 2014 - Jan 2015",
                """
                Carried out benchmark on Apache Storm and developed storm-benchmark,
                a suite of benchmarks to test Apache Storm performance.
                """
              ),
              titledBlock(
                "Software Engineer, Intel Hadoop Distribution", "July 2013 - July 2014",
                """
                Contributed to mapreduce-nativetask, which boosted MapReduce performance up to 30%,
                and was merged into Hadoop trunk.
                """
              ),
              titledBlock(
                "Intern , Intel Hadoop Distribution", "Jan 2013 - July 2013",
                """
                Carried out benchmark on a message queue built on Apache HBase
                """
              )
            )
        ),
        section(
          "Skills",
          h3(roleText,
            bulletList(
              Seq(
                "Scala",
                "Java",
                "Python",
                "C/C++",
                "JavaScript",
                "Shell",
                "SQL",
                "Haskell"
              ).mkString(" - "),
              Seq(
                "Spark",
                "Storm",
                "Akka",
                "Beam",
                "Kafka",
                "Hadoop",
                "Cassandra",
                "JupyterLab"
              ).mkString(" - "),
              Seq(
                "Docker/Kubernetes",
                "Ubuntu",
                "CentOS"
              ).mkString(" - "),
              Seq(
                "Git",
                "Intellij",
                "Vim",
                "Emacs"
              ).mkString(" - ")
            )
          )
        ),
        section(
          "Education",
          col(
            div(
              row(
                h2(sectionHeading, "East China Normal University"),
                // Override height to compensate for non-square image
                logo("ECNU.png")(height := 12, paddingTop := 4),
                div(rightGreyText, "Shanghai")
              ),
              titledBlock(
                "Undergraduate Software Engineering", "Sep 2009 - July 2013"
              )
            )
          )
        ),
        section(
          "Reference",
          col(
            div(
              row(h2(sectionHeading, "Projects"), logo("Github.png")),
             /* div(listBlock,
                 p(para,
                  "Other cool projects i've worked on that are worth checking out!"
                )
              ), */
              titledBlock(
                "Gearpump",
                """
                Gearpump is a real-time Big Data engine on Akka.
                """,
                autolink("https://github.com/gearpump/gearpump")
              ),
              titledBlock(
                "Apache Beam",
                """
                Beam committer and contributed the Gearpump runner.
                """,
                autolink("https://github.com/apache/beam")
              ),
              titledBlock(
                "storm-benchmark",
                """
                a suite of benchmarks to test performance of Apache Storm.
                """,
                autolink("https://github.com/intel-hadoop/storm-benchmark")
              ),
              titledBlock(
                "awesome-streaming",
                """
                a curated list of awesome streaming frameworks, applications, etc.
                """,
                autolink("https://github.com/manuzhang/awesome-streaming")
              )
            )
          )
        )
      )
    )
    write.over(cwd/'target/"resume.html", blob.render)
  }
}
