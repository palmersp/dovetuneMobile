/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dovetune;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static javax.ws.rs.core.Response.status;
import static javax.ws.rs.core.Response.status;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

/**
 *
 * @author Spencer
 */
@WebServlet(name = "PostTweet", urlPatterns = {"/PostTweet"})
public class PostTweet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        Twitter twitter = (Twitter)request.getSession().getAttribute("twitter");
        String tweetItem = request.getParameter("tweet");
        String songName = request.getParameter("song");
        
        songName = songName.replace(" ", "_");
        String tweet = tweetItem +" #" + songName + " #DoveTune #SoundCloud";
        
        String user = "";   
        try {
            Status status = twitter.updateStatus(tweet);
            user = "<img class='profileImg' src='" + status.getUser().getProfileImageURL() + "' title='Profile Image' alt='Profile Image'><strong>" +status.getUser().getName() + " @" + status.getUser().getScreenName() + "</strong> - " + status.getText();
            
            user = user.replace("#" + songName, "<strong class='hashtags'>#" + songName + "</strong>");
            user = user.replace("#DoveTune", "<strong class='hashtags'>#DoveTune</strong>");
            user = user.replace("#SoundCloud", "<strong class='hashtags'>#SoundCloud</strong>");
            String songL = songName;
            songL = songL.toLowerCase();
            user = user.replace("#" + songL, "<strong class='hashtags'>#" + songL + "</strong>");
                
        } catch (TwitterException ex) {
            Logger.getLogger(PostTweet.class.getName()).log(Level.SEVERE, null, ex);
        
            
        }
 
//        request.setAttribute("tweet", tweet);
        
        response.setContentType("text/HTML");
        response.getWriter().write(user);
        
//        request.getRequestDispatcher("details.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
