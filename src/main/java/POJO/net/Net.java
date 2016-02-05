package POJO.net;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Guardeec on 04.02.16.
 */
public class Net {
    private List<Router> network;

    public Net() {
        network = new LinkedList<Router>();
    }

    public List<Router> getNetwork() {
        return network;
    }

    public void setNetwork(List<Router> network) {
        this.network = network;
    }

    public void addRouter(Router router){
        network.add(router);
    }

    public static Net generate(int maxNumberOfRouters, int maxNumberOfHostsPerRouter){
        Net net = new Net();
        Random random = new Random();

        int id=0;
        for (int i=0; i<getNumberOfRouters(maxNumberOfRouters); i++){
            Router router = new Router();
            router.setId(id);
            router.setName("router #"+id);
            for (int q=0; q<getNumberOfHostsPerRouter(maxNumberOfHostsPerRouter); q++){
                id++;
                Host host = new Host();
                host.setId(id);
                host.setName("Host #"+id);
                router.addHost(host);
            }
            net.addRouter(router);
            id++;
        }

        return net;
    }

    private static int getNumberOfRouters(int maxNumberOfRouters){
        int numberOfRouters = 0;
        Random random = new Random();
        while (numberOfRouters<maxNumberOfRouters/3){
            numberOfRouters = random.nextInt(maxNumberOfRouters);
        }
        return numberOfRouters;
    }

    private static int getNumberOfHostsPerRouter(int maxNumberOfHostsPerRouter){
        int numberOfHosts = 0;
        Random random = new Random();
        while (numberOfHosts<maxNumberOfHostsPerRouter/3){
            numberOfHosts = random.nextInt(maxNumberOfHostsPerRouter);
        }
        return numberOfHosts;
    }

}
