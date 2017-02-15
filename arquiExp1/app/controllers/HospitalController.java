package controllers;

import dispatchers.AkkaDispatcher;
import models.Hospital;

import java.util.concurrent.CompletableFuture;

/**
 * Created by Juan on 2/15/2017.
 */
public class HospitalController extends Controller{

    @BodyParser.Of(BodyParser.Json.class)
    public CompletionSate<Result> createHospital(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nHospital = request().body().asJson();
        Hospital hospital = Json.fromJson(nHospital, Hospital.class);
        return CompletableFuture.supplyAsync(
                ()->{
                    hospital.save();
                    return hospital;
                }
        ).thenApply(
                Hospital->{
                    return ok(Json.toJson(Hospital));
                }
        );
    }


    public CompletionStage<Result> getHospitales(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return CompletableFuture.
                supplyAsync(
                        ()->{
                            return Hospital.FINDER.all();
                        }
                        , jdbcDispatcher
                ).thenApply(
                        hospitales->{
                            return ok(toJson(hospitales));
                        }
        );
    }

}
