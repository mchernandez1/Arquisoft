package controllers;

//import dispatchers.AkkaDispatcher;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import static play.libs.Json.toJson;

import com.avaje.ebean.Model;
import com.google.inject.Inject;
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

    //------------
    //GET Medicos
    //------------

   // public CompletionStage<Result> getMedicos()
    //{
       // MessageDispatcherjdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

      //  return CompletableFuture.supplyAsync(
        //        () -> {
          //          return Medico.FINDER.all();
            //    }
              //  ,jdbcDispatcher)
               // .thenApply(
                 //       procuctEntities ->
                   //     {
                     //       return ok(toJson(procuctEntities));
                      //  }
        //)
   // }

    //------------
    //GET Medico
    //------------

  //  public CompletionStage<Result> getMedico(int id)
  //  {
//        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

     //   return CompletableFuture.
        //        supplyAsync(
                    //    () -> {
                  //          return Medico.FINDER.byId(id);
                //        }
              //          ,jdbcDispatcher)
            //    .thenApply(
          //              productEntities -> {
        //                    return ok(toJson(productEntities));
      //                  }
    //            );
  //  }


     //------------
     //POST Medico
     //------------
   // public Result createMedico()
   // {
     //   JsonNode j = request().body().asJson();
       // Medico medico = Json.fromJson(j,Medico.class);
       // return CompletableFuture.supplyAsync(
         //       ()->{
           //         medico.save();
            //        return medico;
             //   }
       // ).thenApply(
         //       Medico -> {
           //         return ok(Json.toJson(Medico));
             //           }
             //   );

    //}

    //------------
    //DELETE Medico
    //------------

    public CompletionStage<Result> deleteMedico(int id)
    {
        return CompletableFuture.supplyAsync(
                ()->{
                    Medico medico = Medico.FINDER.byId(id);
                    Medico.FINDER.deleteById(id);
                    medico = Medico.FINDER.byId(id);
                    return medico == null;
                }
        ).thenApply(
                productEntity -> {
                    return ok(Json.toJson(productEntity));
                }
        );
    }


    //------------
    //UPDATE Medico
    //------------

    public CompletionStage<Result> updateMedico(int id){

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            JsonNode medico = request().body().asJson();
                            Medico mediquillo = Json.fromJson(medico, Medico.class);
                            Medico medicoActualizar = Medico.FINDER.byId(id);
                            medicoActualizar.setNombreMedico(mediquillo.getNombreMedico());
                            medicoActualizar.setEspecialidadMedico(mediquillo.getEspecialidadMedico());
                            medicoActualizar.setDescripcionMedico(mediquillo.getDescripcionMedico());
                            medicoActualizar.update();
                            return medicoActualizar;
                        }
                        ,ec.current())
                .thenApply(
                        Medico -> {
                            return ok(toJson(Medico));
                        }
                );
    }
}
