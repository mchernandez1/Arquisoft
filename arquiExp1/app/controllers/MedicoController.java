package controllers;

//import dispatchers.AkkaDispatcher;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import static play.libs.Json.toJson;

import com.avaje.ebean.Model;
import models.Medico;
import akka.dispatch.MessageDispatcher;
import play.mvc.*;
import java.util.concurrent.CompletionStage;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;
/**
 * Created by Maria on 14/02/2017.
 */
public class MedicoController extends Controller
{
    @BodyParser.Of(BodyParser.Json.class)

    public Result create()
    {
        JsonNode j = Controller.request().body().asJson();
        Medico medico = Medico.bind(j);
        medico.save();
        return ok(Json.toJson(medico));
    }

    public Result read()
    {
        List<Medico> medicos = new Model.Finder(String.class, Medico.class).all();
        return ok(Json.toJson(medicos));
    }
//public CompletionStage<Result> getMedicos()
//{
    //MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
    //return CompletableFuture.
            //supplyAsync()
              //      ()->{
                //        return Medico.Finder.all();
                  //  }
                    //,jdbcDispatcher
            //.thenApply(
                    //Medico->{
                       // return ok(toJson(Medico));
                    //}

            //);
//}
}
