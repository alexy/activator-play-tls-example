package controllers

import play.api._
import play.api.mvc._

class HomeController extends Controller {

  def index = Action { implicit request =>
    Ok(views.html.index(subdomain)).withHeaders("Cache-Control" -> "no-store")
  }

  private def subdomain(implicit r: Request[_]): String = {
    // pull out the host part of the request URI
    val s = r.host.split(":")(0).replace(".disappointed.us", "")

    // This is technically user input, so need to make sure this isn't
    // anything fun like unicode characters or javascript.
    // It will still go through DNS, so that's something.
    s
  }
}
