package controllers;

import dispatchers.AkkaDispatcher;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import static play.libs.Json.toJson;

import Mocks.MedicoMock;
import com.avaje.ebean.Model;
import com.google.inject.Inject;
import dispatchers.AkkaDispatcher;
import models.Medico;
import akka.dispatch.MessageDispatcher;
import play.libs.concurrent.HttpExecution;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;
import java.util.concurrent.CompletionStage;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;
/**
 * Created by Maria on 14/02/2017.
 */
public class MedicoController extends Controller
{
    @Inject
    HttpExecutionContext ec;

    MedicoMock mock= new MedicoMock();

    //------------
    //GET Medicos
    //------------

    public CompletionStage<Result> getMedicos()
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.supplyAsync(
                () -> {
                    return mock.getAll();
               }
                ,jdbcDispatcher)
                .thenApply(
                        mediquillo ->
                        {
                            return ok(toJson(mediquillo));
                        }
        );
    }

    //------------
    //GET Medico
    //------------

    public CompletionStage<Result> getMedico(Long id)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
               supplyAsync(
                        () -> {
                           return mock.get(id);
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        mediquillo -> {
                            return ok(toJson(mediquillo));
                        }
                );
    }

     //------------
     //POST Medico
     //------------
    public CompletionStage<Result>  createMedico()
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        JsonNode j = request().body().asJson();
        Medico medico = Json.fromJson(j,Medico.class);
        return CompletableFuture.supplyAsync(
                ()->{
                    mock.save(medico);
                    return medico;
                }
        ).thenApply(
                mediquillo -> {
                   return ok(Json.toJson(mediquillo));
                        }
                );

    }

    //------------
    //DELETE Medico
    //------------

    public CompletionStage<Result> deleteMedico(Long id)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.supplyAsync(
                ()->{
                    mock.delete(id);
                    Medico medico = mock.get(id);
                    return medico;
                }
        ).thenApply(
                mediquillo -> {
                    return ok(Json.toJson(mediquillo));
                }
        );
    }


    //------------
    //UPDATE Medico
    //------------

    public CompletionStage<Result> updateMedico(Long id){

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            JsonNode m = request().body().asJson();
                            Medico medico = Json.fromJson(m, Medico.class);
                            mock.update(medico);
                            return medico;
                        }
                        ,ec.current())
                .thenApply(
                        mediquillo -> {
                            return ok(toJson(mediquillo));
                        }
                );
    }
}
