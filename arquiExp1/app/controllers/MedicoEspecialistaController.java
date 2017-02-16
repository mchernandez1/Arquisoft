package controllers;

import dispatchers.AkkaDispatcher;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import static play.libs.Json.toJson;

import Mocks.MedicoEspecialistaMock;
import com.avaje.ebean.Model;
import com.google.inject.Inject;
import dispatchers.AkkaDispatcher;
import models.Medico;
import models.MedicoEspecialista;
import akka.dispatch.MessageDispatcher;
import play.libs.concurrent.HttpExecution;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;
import java.util.concurrent.CompletionStage;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by Maria on 15/02/2017.
 */
public class MedicoEspecialistaController extends Controller
{
    @Inject
    HttpExecutionContext ec;

    MedicoEspecialistaMock mock= new MedicoEspecialistaMock();

    //------------
    //GET Medicos
    //------------

    public CompletionStage<Result> getMedicosEspecialista()
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

    public CompletionStage<Result> getMedicoEspecialista(Long id)
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
    public CompletionStage<Result>  createMedicoEspecialista()
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        JsonNode j = request().body().asJson();
        MedicoEspecialista medico = Json.fromJson(j,MedicoEspecialista.class);
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

    public CompletionStage<Result> deleteMedicoEspecialista(Long id)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.supplyAsync(
                ()->{
                    mock.delete(id);
                    MedicoEspecialista medico = mock.get(id);
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

    public CompletionStage<Result> updateMedicoEspecialista(Long id){

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            JsonNode m = request().body().asJson();
                            MedicoEspecialista medico = Json.fromJson(m, MedicoEspecialista.class);
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
