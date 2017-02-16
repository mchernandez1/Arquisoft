package controllers;

import Mocks.HospitalMock;
import akka.dispatch.MessageDispatcher;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import dispatchers.AkkaDispatcher;
import models.Hospital;


import play.libs.Json;

import akka.dispatch.MessageDispatcher;
import play.mvc.Result;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.libs.Json.toJson;

/**
 * Created by Juan on 2/15/2017.
 */
public class HospitalController extends Controller{

    @Inject
    HttpExecutionContext ec;

    HospitalMock hm = new HospitalMock();


    public CompletionStage<Result> createHospital(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode j = request().body().asJson();
        Hospital hospital = Json.fromJson(j, Hospital.class);
        return CompletableFuture.supplyAsync(
                ()->{
                    hm.add(hospital);
                    return hospital;
                }
        ).thenApply(
                hospitales->{
                    return ok(Json.toJson(hospital));
                }
        );


    }


    public CompletionStage<Result> getHospitales(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return CompletableFuture.
                supplyAsync(
                        ()->{
                            return hm.getHospitales();
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        hospitales->{
                            return ok(toJson(hospitales));
                        }
                );
    }


    public CompletionStage<Result> getHospital(Long id){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return CompletableFuture.
                supplyAsync(
                        ()->{
                            return hm.getHospital(id);
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        hospitales->{
                            return ok(toJson(hospitales));
                        }

                );
    }

    public CompletionStage<Result> deleteHospital(long id)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.supplyAsync(
                ()->{
                    hm.delete(id);
                    Hospital hospital = hm.getHospital(id);
                    return hospital;
                }
        ).thenApply(
                hospitales -> {
                    return ok(Json.toJson(hospitales));
                }
        );
    }

    public CompletionStage<Result> updateHospital(Long id){

        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            JsonNode node = request().body().asJson();
                            Hospital hospital = Json.fromJson(node, Hospital.class);
                            hm.update(hospital);
                            return hospital;
                        }
                        ,ec.current())
                .thenApply(
                        hospitales -> {
                            return ok(toJson(hospitales));
                        }
                );
    }


}
