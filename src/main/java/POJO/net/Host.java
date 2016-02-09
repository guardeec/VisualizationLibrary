package POJO.net;

/**
 * Created by Guardeec on 04.02.16.
 */
public class Host {

    private Integer id;
    private String name;
    private Router router;

    public Host() {
    }

    public Host(Integer id, String name) {
        if (name.length()>30){
            throw new IllegalArgumentException("name.length must be < 30");
        }
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length()>30){
            throw new IllegalArgumentException("name.length must be < 30");
        }
        this.name = name;
    }

    public Router getRouter() {
        return router;
    }

    public void setRouter(Router router) {
        this.router = router;
    }
}
