package POJO.net;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Guardeec on 04.02.16.
 */
public class Router {

    private int id;
    private String name;
    private List<Host> hosts;

    public Router() {
        this.hosts = new LinkedList<Host>();
    }

    public Router(int id, String name, List<Host> hosts) {
        this.id = id;
        this.name = name;
        this.hosts = hosts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addHost(Host host){
        host.setRouter(this);
        hosts.add(host);
    }

    public List<Host> getHosts(){
        return hosts;
    }
}
