package resume

import Styles._
import scalatags.Text.all._

object Resume{

  def main(args: Array[String]): Unit = {

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

    def section(title: String, body: Frag) = tr(

      td(h2(paddingTop := 10, paddingBottom := 10, sectionHeading, title, marginRight := 20)),
      td(paddingTop := 10, paddingBottom := 10, body)
    )

    val blob = html(
      fontFamily := "Calibri, Candara, Segoe, 'Segoe UI', Optima, Arial, sans-serif",
      head(
        scalatags.Text.tags2.style(raw(cssReset)),
        scalatags.Text.tags2.style(raw(styleSheetText))
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
                autolink("https://github.com/manuzhang")
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
              row(h2(sectionHeading, "eBay"), div(rightGreyText, "Shanghai")),
              titledBlock(
                "MTS 2, Software Engineer, Selling API", "Aug 2025 - Feb 2026",
                "Led the maintenance of eBay's public Trading API used by thousands of global third-party developers.",
                """
                Architected a high-throughput duplicate listing detection process, eliminating critical database "hot spots"
                and race conditions by implementing a Distributed Lock Manager.
                """
              ),
              titledBlock(
                "MTS 2, Software Engineer, Cloud Data Platform", "Jun 2019 - Aug 2025",
                """
                Led the transition from Hive to Iceberg by developing dedicated migration tooling
                and a "Lake Manager" to automate table maintenance and cost optimization.
                """,
                """
                Maintained a highly available Spark platform processing PB-level data daily
                across 10,000+ concurrent production jobs.
                """,
                """
                Built a centralized service for Spark distribution and configuration management,
                supporting heterogeneous business requirements.
                """,
                """
                Integrated automated performance profiling and version-control tools to
                streamline Spark upgrades and reduce manual troubleshooting.
                """
              ),
              row(h2(sectionHeading, "Vipshop"), div(rightGreyText, "Shanghai")),
              titledBlock(
                "Senior Software Engineer, Machine Learning Platform", "Aug 2017 - Jun 2019",
                """
                Led the architecture and development of an end-to-end Machine Learning Platform,
                providing an integrated workspace for data scientists and engineers to develop,
                train, and deploy models.
                """,
                """
                Built a high-performance drag-and-drop pipeline editor that abstracted complex execution logic,
                enabling non-technical users to build production-grade ML workflows.
                """,
                """
                Developed stream processing jobs to prepare real-time data for model training,
                greatly improving model freshness.
                """
              ),
              row(h2(sectionHeading, "Intel"), div(rightGreyText, "Shanghai")),
              titledBlock(
                "Software Engineer, Gearpump", "Jan 2015 - Aug 2017",
                """
                Core committer of Gearpump, a real-time Big Data engine on Akka,
                developing Kafka connectors, Storm compatibility layer and transaction APIs.
                """
              ),
              titledBlock(
                "Software Engineer, Intel Hadoop Distribution", "July 2014 - Jan 2015",
                """
                Developed storm-benchmark, a specialized benchmarking suite to profile Apache Storm performance at scale,
                uncovering architectural bottlenecks.
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
                "Intern, Intel Hadoop Distribution", "Jan 2013 - July 2013",
                """
                Conducted a benchmark on a message queue built on Apache HBase
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
                "Iceberg",
                "Spark",
                "Hadoop",
                "Akka",
                "Beam",
                "Storm",
                "Kafka",
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
                div(rightGreyText, "Shanghai")
              ),
              titledBlock(
                "Undergraduate Software Engineering", "Sep 2009 - July 2013"
              )
            )
          )
        ),
        section(
          "Open Source Contributions",
          col(
            div(
              row(h2(sectionHeading, "Projects")),
              titledBlock(
                "Apache Iceberg (Top 10 contributors by commits)",
                "",
                autolink("https://github.com/apache/iceberg")
              ),
              titledBlock(
                "Apache Spark (Long time contributor since 2019)",
                "",
                autolink("https://github.com/apache/spark")
              ),
              titledBlock(
                "Apache Beam (committer)",
                "",
                autolink("https://github.com/apache/beam")
              ),
              titledBlock(
                "Gearpump (Core developer)",
                "",
                autolink("https://github.com/gearpump/gearpump")
              ),
              titledBlock(
                "awesome-streaming (over 3k stars)",
                "",
                autolink("https://github.com/manuzhang/awesome-streaming")
              )
            )
          )
        )
      )
    )
    os.write.over(os.pwd/Symbol("target")/"resume.html", blob.render)
  }
}
