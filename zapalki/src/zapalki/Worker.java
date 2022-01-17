package zapalki;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Worker {
    private Resources resource;
    private Resources cost;
    private Items items;
    private ScheduledFuture<?> work;

    Worker(String resource, Items items) {
        this.resource = Resources.fromString(resource,1);
        this.cost = new Resources(0,0,0,1);
        this.items = items;
    }

    public static void hire(String resource, Items items) {
        Worker worker = new Worker(resource,items);
        items.getWorkers().add(worker);
    }

    public void buy() {
        this.items.getResources().add(this.resource);
    }

    public void dismiss() {
        this.items.getWorkers().remove(this);
        this.work.cancel(false);
    }

    private void start() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        this.work = executorService.scheduleAtFixedRate(() -> {
            if( this.items.getResources().isEnough(this.cost) ) {
                this.buy();
            }
            else {
                this.dismiss();
            }
        },0,10, TimeUnit.SECONDS);
    }
}
