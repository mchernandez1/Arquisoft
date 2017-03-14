package controllers;

import Mocks.MedidorMock;
import akka.dispatch.MessageDispatcher;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import dispatchers.AkkaDispatcher;
import models.Medicion;
import models.Medico;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.libs.Json.toJson;

/**
 * Created by d.marino10 on 15/02/2017.
 */
public class MedicionController extends Controller {
    @Inject
    HttpExecutionContext ec;

    //MedidorMock mock= new MedidorMock();

    public CompletionStage<Result> getMediciones(){

        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return Medicion.FINDER.all();
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        Mediciones -> {
                            return ok(toJson(Mediciones));
                        }
                );
    }

    public CompletionStage<Result> getMedicion(long referencia){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return Medicion.FINDER.byId(referencia);
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        Mediciones -> {
                            return ok(toJson(Mediciones));
                        }
                );
    }

    public CompletionStage<Result> createMedicion(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        JsonNode m = request().body().asJson();
        Medicion medicion = Json.fromJson(m, Medicion.class);
        return CompletableFuture.supplyAsync(
                () -> {
                    medicion.save();
                    return medicion;
                }
        ).thenApply(
                Medicion -> {
                    return ok(Json.toJson(Medicion));
                }
        );
    }

    public CompletionStage<Result> deleteMedicion(long referencia)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.supplyAsync(
                ()->{
                    Medicion.FINDER.deleteById(referencia);
                    return referencia;
                }
        ).thenApply(
                 Medicion -> {
                    return ok(Json.toJson(Medicion));
                }
        );
    }

    public CompletionStage<Result> updateMedicion(long referencia){

        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            JsonNode m = request().body().asJson();
                            Medicion medicion = Json.fromJson(m, Medicion.class);
                            Medicion pPorActualizar =  Medicion.FINDER.byId(referencia);

                            pPorActualizar.setEstado(medicion.getEstado());
                            pPorActualizar.setEstres(medicion.getEstres());
                            pPorActualizar.setFrecuencia(medicion.getFrecuencia());
                            pPorActualizar.setPresion(medicion.getPresion());

                            pPorActualizar.update();

                            return pPorActualizar;
                        }
                        ,ec.current())
                .thenApply(
                        Medicion -> {
                            return ok(toJson(Medicion));
                        }
                );
    }



    public CompletionStage<Result> getByFechas(String fInicio, String fFinal){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            DateFormat df = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm");
                            Date fechaInicio=null;
                            Date fechaFin=null;

                            try {
                                fechaInicio = df.parse(fInicio);
                                fechaFin = df.parse(fFinal);
                            }
                            catch (Exception e){

                            }
                            return Medicion.FINDER.where().between("fecha", fechaInicio,fechaFin).findList();
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        Medicion -> {
                            return ok(toJson(Medicion));
                        }
                );
    }
}
