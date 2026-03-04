package resume

import Styles._
import scalatags.Text.all._

object Resume {

  def main(args: Array[String]): Unit = {

    def autolink(url: String) = a(url.stripPrefix("https://").stripPrefix("http://"), href := url)

    def row = div(display.flex, flexDirection := "row")

    def col = div(display.flex, flexDirection := "column")

    def titledBlock(title: String, loc: String, bullets: Frag*) = div(
      row(
        h3(roleText, title),
        div(rightGreyText, loc)
      ),
      bulletList(bullets: _*)
    )

    def subListBlock(title: String, bullets: Frag*) = div(
      h4(roleText, title),
      ul(bullets.map(li(subListItem, _)))
    )

    def bulletList(bullets: Frag*) = ul(
      listBlock,
      bullets.map(li(listItem, _))
    )

    def section(title: String, body: Frag) = tr(

      td(h2(paddingTop := 10, paddingBottom := 10, sectionHeading, title)),
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
              "MTS 2 Software Engineer, Cloud Data Platform", "Jun 2019 - Feb 2026",
              subListBlock("Led the construction of the next-generation lakehouse based on Apache Iceberg",
                """
                   Scaled Apache Iceberg in production by developing internal customizations, managing Spark-Iceberg integration,
                   and contributing bug fixes and features back to the community.
                """,
                """
                   Developed a customized migration solution to achieve seamless and smooth migration of 300+ Spark/Hive tables to Iceberg tables
                   without copying data or affecting business, meeting GDPR and other compliance requirements
                """,
                """
                   Developed a Data Lake Optimizer by watching Iceberg commit events, triggering compaction jobs on demand to merge small files,
                   reducing metadata overhead and read amplification, improving query stability and performance, and saving cluster resources.
                """,
                """
                   Designed a CDC data ingestion solution based on Iceberg to solve the problem of declining query performance under streaming updates,
                   meeting near real-time data analysis needs.
                """
              ),
              subListBlock("Developed and maintained a high-availability Spark platform, supporting PB-level data processing and analysis requirements",
                """
                   Managed the full lifecycle of internal Spark distributions, including custom feature backporting, proactive performance tuning,
                   and the development of standardized production troubleshooting frameworks.
                """,
                """
                   Continuously evolved the job monitoring and diagnosis platform: analyzing Spark EventLogs to extract task execution and diagnostic information,
                   and persisting into ElasticSearch to support query and diagnostic services, improving self-service troubleshooting efficiency.
                """,
                """
                   Led the large-scale enabling of Spark AQE on the production platform, handling data skew scenarios without manual intervention;
                   meanwhile reported critical issues and contributed bug fixes back to Spark community.
                """,
                """
                   Built a centralized Spark configuration service: supporting canary release and rollback of multiple versions and configuration changes across multiple clusters,
                   satisfying diverse business requirements under a unified platform.
                """,
                """
                   Developed an automated Spark major version upgrade pipeline based on the centralized configuration service and diagnostic platform,
                   achieving a smooth upgrade of 10,000+ jobs from 2.4 to 3.1 and then to 3.5; fixed issues found during the upgrade process and contributed back to the community.
                """),
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
                 Developed Gearpump, a next-generation real-time big data engine based on Akka;
                 responsible for developing Kafka connectors, Storm compatibility layers, and transaction APIs and integrating with Apache Beam;
                 contributed the project to the Apache Foundation.
              """
            ),
            titledBlock(
              "Software Engineer, Intel Hadoop Distribution", "July 2013 - Jan 2015",
              """
                 Developed storm-benchmark, a specialized benchmarking suite to profile Apache Storm performance at scale,
                 uncovering architectural bottlenecks.
              """,
              """
                 Contributed to mapreduce-nativetask, which boosted MapReduce performance up to 30%,
                 and was merged into Hadoop trunk.
              """
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
                "Apache Spark (Long time active contributor)",
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
        ),
        section(
          "Skills",
          bulletList(
            row(h3(roleText, "Programming Languages:"),
              Seq(
                "Scala",
                "Java",
                "Python",
                "SQL",
                "Shell",
                "C/C++",
                "JavaScript",
                "Haskell"
              ).mkString(",")
            ),
            row(h3(roleText, "Distributed Frameworks:"),
              Seq(
                "Iceberg",
                "Spark",
                "Hadoop",
                "Akka",
                "Beam",
                "Storm",
                "Kafka",
                "Cassandra"
              ).mkString(",")
            ),
            row(h3(roleText, "Tools:"),
              Seq(
                "Docker/Kubernetes",
                "Ubuntu",
                "CentOS",
                "Git",
                "Intellij",
                "Jupyter/JupyterLab",
                "Vim",
                "Emacs"
              ).mkString(",")
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
        )
      )
    )
    os.write.over(os.pwd / Symbol("target") / "resume.html", blob.render)
  }
}
