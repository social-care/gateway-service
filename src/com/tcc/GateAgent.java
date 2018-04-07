package com.tcc;

/*****************************************************************

 This agent receives the blackboard object
 and its content will be sent to the proper agent

 *****************************************************************/

import jade.wrapper.gateway.GatewayAgent;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GateAgent extends GatewayAgent {

    private static final long serialVersionUID = 1L;
    BlackBoardBean board = null;
    boolean act = false;

    @Override
    protected void processCommand(Object obj) {

        if(obj instanceof BlackBoardBean) {
            board = (com.tcc.BlackBoardBean)obj;

            String result = "";
            String line = "";
            HttpClient httpclient = HttpClients.createDefault();
            HttpGet request = new HttpGet("http://localhost:3000/user");
            HttpResponse response = null;
            try {
                response = httpclient.execute(request);
                BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
                while ((line = rd.readLine()) != null) {
                    result += line;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }



            board.setMessage(result);

            this.releaseCommand(board);
        }

    }

    public void setup()
    {
        super.setup();
    }

}