package controllers;

import Mocks.UrgenciaMock;
import com.google.inject.Inject;
import com.sun.xml.internal.ws.client.dispatch.MessageDispatch;
import dispatchers.AkkaDispatcher;
import models.Urgencia;
import com.fasterxml.jackson.databind.JsonNode;

import akka.dispatch.MessageDispatcher;
import play.mvc.Result;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


import static play.libs.Json.toJson;

/**
 * Created by Juan on 2/14/2017.
 */
public class UrgenciaController extends Controller{


    @Inject
    HttpExecutionContext ec;

    UrgenciaMock um = new UrgenciaMock();



    public CompletionStage<Result> createUrgencia(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode j = request().body().asJson();
        Urgencia urgencia = Json.fromJson(j, Urgencia.class);
        return CompletableFuture.supplyAsync(
                ()->{
                    um.add(urgencia);
                    return urgencia;
                }
        ).thenApply(
                urgencias->{
                    return ok(Json.toJson(urgencias));
                }
        );


    }

    public CompletionStage<Result> getUrgencias(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return CompletableFuture.
                supplyAsync(
                        ()->{
                            return um.getUrgencias();
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        urgencias->{
                            return ok(toJson(urgencias));
                        }
                );
    }

    public CompletionStage<Result> getUrgencia(Long id){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return CompletableFuture.
                supplyAsync(
                        ()->{
                            return um.getUrgencia(id);
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        urgencias->{
                            return ok(toJson(urgencias));
                        }

                );
    }

    public CompletionStage<Result> deleteUrgencia(long id)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.supplyAsync(
                ()->{
                    um.delete(id);
                    Urgencia urgencia = um.getUrgencia(id);
                    return urgencia;
                }
        ).thenApply(
                urgencias -> {
                    return ok(Json.toJson(urgencias));
                }
        );
    }

    public CompletionStage<Result> updateUrgencia(Long id){

        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            JsonNode node = request().body().asJson();
                            Urgencia urgencia = Json.fromJson(node, Urgencia.class);
                            um.update(urgencia);
                            return urgencia;
                        }
                        ,ec.current())
                .thenApply(
                        urgencia -> {
                            return ok(toJson(urgencia));
                        }
                );
    }


}
