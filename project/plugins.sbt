logLevel := Level.Warn

resolvers += Resolver.sonatypeRepo("releases")
resolvers += DefaultMavenRepository

addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.8.2")

addSbtPlugin("io.spray" % "sbt-revolver" % "0.9.1")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.6")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.3")

addSbtPlugin("org.duhemm" % "sbt-errors-summary" % "0.6.0")

addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.3.4")

addSbtPlugin("com.typesafe.sbt" % "sbt-gzip" % "1.0.2")
