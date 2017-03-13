package dispatchers;

import akka.dispatch.MessageDispatcher;
import play.libs.Akka;

/**
 * Created by AndresFelipe on 15/02/2017.
 */
public class AkkaDispatcher
{
    public static MessageDispatcher jdbcDispatcher = Akka.system().dispatchers().lookup("contexts.jdbc-dispatcher");

}