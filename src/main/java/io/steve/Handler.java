package io.steve;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/file")
public class Handler {

    private static final String DEFAULT_PATH = "/var/tmp/";

    @GET
    @Path("/{filename}")
    public boolean getStatus(@PathParam("filename") String filename) {
        File f = new File(DEFAULT_PATH + filename);
        System.out.println(f.getAbsolutePath());
        return f.exists();
    }

    @POST
    @Path("/{filename}")
    public void processFile(@PathParam("filename") String filename) throws InterruptedException {
        // start processing

        System.out.println("received " + filename);

        File f = new File(DEFAULT_PATH + filename);

        if(f.exists() && !f.getName().endsWith(".processing") && !f.getName().endsWith(".done")) {

            String newPath = DEFAULT_PATH + filename + ".processing";

            if(f.renameTo(new File(newPath))) {

                f = new File(newPath);

                System.out.println(f.getAbsolutePath());

                System.out.println("working on " + f.getAbsolutePath());
                Thread.sleep(15000);
                System.out.println("work done on " + f.getAbsolutePath());

                f.renameTo(new File(DEFAULT_PATH + filename + ".done"));
            }

        }
        else {
            System.out.println("skipping " + f.getAbsolutePath());
        }

    }

}