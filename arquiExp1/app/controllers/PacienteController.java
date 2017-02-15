package controllers;

import Mocks.PacienteMock;
import akka.dispatch.MessageDispatcher;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import dispatchers.AkkaDispatcher;
import models.Paciente;
import play.libs.Json;
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

    PacienteMock mock= new PacienteMock();

    public CompletionStage<Result> getPacientes(){

        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return mock.getAll();
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        pozoEntities -> {
                            return ok(toJson(pozoEntities));
                        }
                );
    }

    public CompletionStage<Result> getPaciente(long id){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return mock.get(id);
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        productEntities -> {
                            return ok(toJson(productEntities));
                        }
                );
    }

    public CompletionStage<Result> createPaciente(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        JsonNode p = request().body().asJson();
        Paciente paciente = Json.fromJson(p, Paciente.class);
        return CompletableFuture.supplyAsync(
                () -> {
                    mock.save(paciente);
                    return paciente;
                }
        ).thenApply(
                campoEntity -> {
                    return ok(Json.toJson(campoEntity));
                }
        );
    }

    public CompletionStage<Result> deletePaciente(long id)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.supplyAsync(
                ()->{
                    mock.delete(id);
                    Paciente paciente = mock.get(id);
                    return paciente;
                }
        ).thenApply(
                productEntity -> {
                    return ok(Json.toJson(productEntity));
                }
        );
    }

    public CompletionStage<Result> updatePaciente(Long id){

        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            JsonNode p = request().body().asJson();
                            Paciente paciente = Json.fromJson(p, Paciente.class);
                            mock.update(paciente);
                            return paciente;
                        }
                        ,ec.current())
                .thenApply(
                        campoEntity -> {
                            return ok(toJson(campoEntity));
                        }
                );
    }
}
