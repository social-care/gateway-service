package com.tcc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jade.util.leap.Properties;

import jade.core.Profile;
import jade.wrapper.gateway.JadeGateway;

public class GateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public GateServlet() {
        Properties pp = new Properties();
        pp.setProperty(Profile.MAIN_HOST, "localhost");
        pp.setProperty(Profile.MAIN_PORT, "2000");
        JadeGateway.init("com.tcc.GateAgent", pp);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // create a BlackBoard for the session if it not exist
        BlackBoardBean board = new BlackBoardBean();
        board.setReceiver("PingJani");
        board.setMessage("Hey whats up");
        try {
            JadeGateway.execute(board);
        } catch(Exception e) {
            e.printStackTrace();
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("Message has been sent!<br/>");
        out.print("Reply:"+board.getMessage());
        out.flush();
        out.close();
    }
}