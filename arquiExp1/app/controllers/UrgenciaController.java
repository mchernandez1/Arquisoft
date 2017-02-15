package controllers;

import com.sun.xml.internal.ws.client.dispatch.MessageDispatch;
import dispatchers.AkkaDispatcher;
import models.Urgencia;

import javax.xml.transform.Result;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * Created by Juan on 2/14/2017.
 */
public class UrgenciaController extends Controller{

    @BodyParser.Of(BodyParser.Json.class)
    public Result create(){
        JsonNode j = Controller.request().body().asJson();
        Urgencia urgencia = Urgencia.bind(j);
        urgencia.save();
        return ok(Json.toJson(urgencia));
    }

    public CompletionStage<Result> getUrgencias(){
        MessageDispatcher jdbcDispatcher = akkaDispatcher.jdbcDispatcher;
        return CompletableFuture.
                supplyAsync(
                        ()->{
                            return Urgencia.FINDER.all();
                        }
                        jdbcDispatcher)
                .thenApply(
                        urgencias->{
                            return ok(toJson(urgencias));
                        }
                );
    }

    public CompletionStage<Result> createUrgencia(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nUrgencia = request().body().asJson();
        Urgencia urgencia = Json.fromJson(nUrgencia, Urgencia.class);
        return CompletableFuture.supplyAsync(
                ()->{
                    urgencia.save();
                    return urgencia;
                }

        ).thenApply(
                Urgencia->{
                    return ok(Json.toJson(Urgencia));
                }
        );
    }


}
