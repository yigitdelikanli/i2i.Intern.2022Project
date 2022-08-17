package com.i2i.internship.eyecell;

import com.i2i.internship.eyeCell.kafka.method.ProducerMethod;
import com.i2i.internship.eyeCell.kafka.modul.UsageMessage;
import com.i2i.internship.eyecell.config.OCSConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutionException;

public class App {
    private static Logger log = LogManager.getLogger(App.class);

    public static void main(String[] args) {

/*
        Pattern p = Pattern.compile("[^505][0-9]{5}$");
        Matcher m = p.matcher("5051234545");
        System.out.println("numara " + m.find());                           // returns true
        System.out.println(m.start());
        System.out.println();

        // returns 4
        m.end();
        // returns 9

        System.out.println(m = p.matcher("502"));
        System.out.println("second : "+m.find());

*/


/*
        VoltDbOperation operation = new VoltDbOperation();

        operation.sendPrice("5313313131",456);
*/

















        //   fileControl(args);


/*
        // Creating environment
        ActorSystem system = ActorSystem.create("AkkaRemoteServer", ConfigFactory.load());

        // Create an actor
        system.actorOf(Props.create(Listener.class), "Listener");

*/
   //     kafkaSender("1234569877","voice",555);

     //   parseXml();


  //      log.info("Work");
    }
/*
    public static void parseXml(){
        // Parse XML

        try {
            ParseXML parseXML =new ParseXML();
            Map<String , Service> serviceRule= parseXML.getServiceRule();

            for(Map.Entry<String,Service> m : serviceRule.entrySet()){
                log.info(m.getKey()+" Regex : " +m.getValue().getRegex());
                log.info(m.getKey()+" Round : " +m.getValue().getRound());
                log.info(m.getKey()+" Price : " +m.getValue().getPrice());
                /*
                System.out.println(m.getKey()+" Regex : " +m.getValue().getRegex());
                System.out.println(m.getKey()+" Round : " +m.getValue().getRound());
                System.out.println(m.getKey()+" Price : " +m.getValue().getPrice() + "\n");

            }
        } catch (ParserConfigurationException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (SAXException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
*/
    public static void kafkaSender(String MSISDN, String service ,int amount){

        UsageMessage message = new UsageMessage();

        message.setMsisdn(MSISDN);
        message.setUsedAmount(amount);
        message.setUsedService(service);

        log.info("Sending data to Kafka");

        try {
            ProducerMethod.runProducer(message);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void fileControl(String[] args){

        if(0<args[0].length()){
            log.info("File path read");
            OCSConfig.setChargingFilePath(args[0]);
        }else {
            log.error("File path read error");
        }
    }
}
