package controllers;

import Mocks.SensorMock;
import akka.dispatch.MessageDispatcher;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import dispatchers.AkkaDispatcher;
import models.Paciente;
import models.Sensor;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.libs.Json.toJson;

/**
 * Created by AndresFelipe on 14/02/2017.
 */
public class SensorController extends Controller{

    public static List<Sensor> lista;

    @Inject
    HttpExecutionContext ec;

    //SensorMock mock = new SensorMock();

    //---------------------------------
    //     GET Sensores
    //---------------------------------
    public CompletionStage<Result> getSensores(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return Sensor.FINDER.all();
                            //return Sensor.FINDER.where().eq("paciente_id", idPaciente).findList();
                            //return mock.getAll();
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        sensor -> {
                            return ok(toJson(sensor));
                        }
                );
    }

    //----------------------------------
    //    GET Sensor
    //----------------------------------
    public CompletionStage<Result> getSensor(Long idSensor){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return CompletableFuture.supplyAsync(
                () -> {
                    return Sensor.FINDER.byId(idSensor);
                    //return mock.get(idSensor);
                }
                ,jdbcDispatcher)
                .thenApply(
                        sensorEntities -> {return ok(toJson(sensorEntities));}
        );
    }

    //-----------------------------------
    //    CREATE Sensor
    //-----------------------------------
    public CompletionStage<Result> createSensor(){
        JsonNode nSensor = request().body().asJson();
        Sensor sensor = Json.fromJson(nSensor, Sensor.class);
        return CompletableFuture.supplyAsync(
                ()->{
                    //Paciente pPaciente = Paciente.FINDER.byId(idPaciente);
                    //sensor.setPaciente(pPaciente);
                    sensor.save();
                    //mock.save(sensor);
                    return sensor;
                }
        ).thenApply(
                sensorEntity -> {return ok(Json.toJson(sensorEntity));}
        );
    }

    //-------------------------------------
    //     DELETE Sensor
    //-------------------------------------
    public CompletionStage<Result> deleteSensor(long idSensor){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return CompletableFuture.supplyAsync(
                ()->{
                    Sensor.FINDER.deleteById(idSensor);
                    //mock.delete(idSensor);
                    //Sensor sensor = mock.get(idSensor);
                    return idSensor;
                }
                ,jdbcDispatcher)
                .thenApply(
                        sensorEntities -> {return ok(toJson(sensorEntities));
                        }
                );
    }

    //-------------------------------------
    //     UPDATE Sensor
    //-------------------------------------
    public CompletionStage<Result> updateSensor(Long idSensor){
        return CompletableFuture.supplyAsync(
                ()->{
                    JsonNode nSensor = request().body().asJson();
                    Sensor sensor = Json.fromJson(nSensor, Sensor.class);
                    Sensor sensorPorActualizar = Sensor.FINDER.byId(idSensor);
                    sensorPorActualizar.setTipo(sensor.getTipo());
                    sensorPorActualizar.setPaciente(sensor.getPaciente());
                    sensorPorActualizar.setFechaAsigancion(sensor.getFechaAsignacion());
                    sensorPorActualizar.setRegistros(sensor.getRegistros());
                    sensorPorActualizar.update();
                    //mock.update(sensor);
                    //return sensor;
                    return sensorPorActualizar;
                }
                ,ec.current())
                .thenApply(
                        sensorEntity -> {return ok(toJson(sensorEntity));}
        );
    }

    //@BodyParser.Of(BodyParser.Json.class)
    //public Result create(){
    //    JsonNode j = Controller.request().body().asJson();
    //    Sensor sensor = Sensor.bind(j);
    //    sensor.save();
    //    return ok(Json.toJson(sensor));
    //}
}

