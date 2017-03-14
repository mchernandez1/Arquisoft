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

    //PacienteMock mock= new PacienteMock();

    public CompletionStage<Result> getPacientes(){

        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return Paciente.FINDER.all();
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        pacientes -> {
                            return ok(toJson(pacientes));
                        }
                );
    }

    public CompletionStage<Result> getPaciente(long id){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return Paciente.FINDER.byId(id);
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        pacientes -> {
                            return ok(toJson(pacientes));
                        }
                );
    }

    public CompletionStage<Result> getPacienteByName(String name){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return Paciente.FINDER.where().eq("nombre",name).findList();
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        pacientes -> {
                            return ok(toJson(pacientes));
                        }
                );
    }

    public CompletionStage<Result> createPaciente(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        JsonNode p = request().body().asJson();
        Paciente paciente = Json.fromJson(p, Paciente.class);
        return CompletableFuture.supplyAsync(
                () -> {
                    paciente.save();
                    return paciente;
                }
        ).thenApply(
                pacientes -> {
                    return ok(Json.toJson(pacientes));
                }
        );
    }

    public CompletionStage<Result> deletePaciente(long id)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.supplyAsync(
                ()->{
                    Paciente.FINDER.deleteById(id);
                     return id;
                }
        ).thenApply(
                pacientes -> {
                    return ok(Json.toJson(pacientes));
                }
        );
    }

    public CompletionStage<Result> updatePaciente(long id){

        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            JsonNode p = request().body().asJson();
                            Paciente paciente = Json.fromJson(p, Paciente.class);
                            Paciente pPorActualizar = Paciente.FINDER.byId(id);

                            pPorActualizar.setCelular(paciente.getCelular());
                            pPorActualizar.setCiudad(paciente.getCiudad());
                            pPorActualizar.setExamenes(paciente.getExamenes());
                            pPorActualizar.setMedicionesHistoricas(paciente.getMedicionesHistoricas());
                            pPorActualizar.setMedico(paciente.getMedico());
                            pPorActualizar.setNombre(paciente.getNombre());
                            pPorActualizar.setPais(paciente.getPais());
                            pPorActualizar.setTelefono(paciente.getTelefono());
                            pPorActualizar.setTratamientos(paciente.getTratamientos());

                            pPorActualizar.update();

                            return pPorActualizar;

                        }
                        ,ec.current())
                .thenApply(
                        paciente -> {
                            return ok(toJson(paciente));
                        }
                );
    }
}
