package controllers;

import akka.dispatch.MessageDispatcher;
import com.google.inject.Inject;
import dispatchers.AkkaDispatcher;
import models.Paciente;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.libs.Json.toJson;

/**
 * Created by d.marino10 on 15/02/2017.
 */
public class PacienteController extends Controller {

    @Inject
    HttpExecutionContext ec;

    //public CompletionStage<Result> getPaciente(){

    //}
}
