package io.steve;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/file")
public class GreetingResource {

    private static final String DEFAULT_PATH = "/var/tmp";

    @GET
    @Path("/{filename}")
    public boolean getStatus(@PathParam("filename") String filename) {
        File f = new File(DEFAULT_PATH + filename);
        return f.exists();
    }

    @POST
    @Path("/{filename}")
    public void processFile(@PathParam("filename") String filename) throws InterruptedException {
        // start processing

        File f = new File(DEFAULT_PATH + filename);

        f.renameTo(new File(DEFAULT_PATH + filename + ".processing"));

        System.out.println("Do work on " + f.getName());
        Thread.sleep(15000);
        System.out.println("work done on " + f.getName());

        f.renameTo(new File(DEFAULT_PATH + filename + ".done"));
    }

}