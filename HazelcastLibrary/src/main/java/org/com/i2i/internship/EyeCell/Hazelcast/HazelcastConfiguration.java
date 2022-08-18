package org.com.i2i.internship.EyeCell.Hazelcast;
        import com.hazelcast.client.HazelcastClient;
        import com.hazelcast.client.config.ClientConfig;
        import com.hazelcast.core.HazelcastInstance;
        import com.hazelcast.map.IMap;
        import java.io.Serializable;
        import java.util.*;

public class HazelcastConfiguration implements Serializable{
    ClientConfig clientConfig = new ClientConfig();
    HazelcastInstance client;
    IMap<String, Object> clientMap;

    public void initConnection(String firstAdress, String secondAdress,String mapName)
    {
        this.clientConfig.getNetworkConfig().addAddress(new String[]{firstAdress, secondAdress});
        this.client = HazelcastClient.newHazelcastClient(this.clientConfig);
        this.clientMap = this.client.getMap(mapName);
    }

    public void putMsisdn(String msisdn, long Uid)
    {
        valueObject valueObject = new valueObject(Uid, msisdn);
        this.clientMap.put(msisdn, valueObject);
    }

    public ArrayList<String> getMsisdnList(int wantedSize)
    {
        ArrayList<String> arrayList = new ArrayList<String>();
        Set<String> keySet = clientMap.keySet();
        String[] keys = keySet.toArray(new String[0]);
        Random random = new Random();
        for (int i = 0; i < keys.length; i++) {
            int randomIndexToSwap = random.nextInt(keys.length);
            String temp = keys[randomIndexToSwap];
            keys[randomIndexToSwap] = keys[i];
            keys[i] = temp;
        }
        for(int i = 0; i < wantedSize && i < keys.length; i++)
        {
            arrayList.add(keys[i]);
        }
        System.out.println(arrayList);
        return arrayList;
    }

    public void clearMapValues()
    {
        clientMap.evictAll();
    }

    public void close()
    {
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        this.client.shutdown();
    }
}

