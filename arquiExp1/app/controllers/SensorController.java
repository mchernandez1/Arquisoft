package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import models.Sensor;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;
/**
 * Created by AndresFelipe on 14/02/2017.
 */
public class SensorController extends Controller{

    //@BodyParser.Of(BodyParser.Json.class)
    //public Result create(){
       // JsonNode j = Controller.request().body().asJson();
       // Sensor sensor = Sensor.bind(j);
       // sensor.save();
      //  return ok(Json.toJson(sensor));
    //}
}

