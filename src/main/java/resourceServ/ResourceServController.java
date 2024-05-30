package resourceServ;

public class ResourceServController implements ResourceServControllerMBean {
    private final ResourceServ resourceServ;
    public ResourceServController(ResourceServ resourceServ) {
        this.resourceServ = resourceServ;
    }

    @Override
    public String getName() { return resourceServ.getName(); }

    @Override
    public int getAge() { return resourceServ.getAge(); }
}