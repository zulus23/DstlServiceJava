import java.net.InetSocketAddress

import play.sbt.PlayRunHook
import sbt._

object Webpack {
  def apply(base: File): PlayRunHook = {

    object WebpackProcess extends PlayRunHook {

      var watchProcess: Option[Process] = None

      override def beforeStarted(): Unit = {
        if(isWindows){
         // Process("cmd /c webpack", base).run
            Process("cmd /c npm run build ", base).run
        }

      }

      override def afterStarted(addr: InetSocketAddress): Unit = {
        if(isWindows) {
          //watchProcess = Some(Process("cmd /c webpack --watch", base).run)
          watchProcess = Some(Process("cmd /c npm run watch", base).run)

        }
      }

      override def afterStopped(): Unit = {
        watchProcess.map(p => p.destroy())
        watchProcess = None
      }

      private def isWindows: Boolean = {
        System.getProperty("os.name").startsWith("Windows")
      }
    }

    WebpackProcess
  }
}