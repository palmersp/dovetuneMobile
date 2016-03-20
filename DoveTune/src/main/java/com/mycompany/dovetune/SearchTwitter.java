/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dovetune;

import java.io.IOException;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.System.out;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static twitter4j.JSONObject.NULL;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 *
 * @author Spencer
 */
@WebServlet(name = "SearchTwitter", urlPatterns = {"/SearchTwitter"})
public class SearchTwitter extends HttpServlet {

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
            throws ServletException, IOException, TwitterException {
            
            
//            HttpSession session = request.getSession(false);
            
//            if(session != null){
        
             Twitter twitter = (Twitter)request.getSession().getAttribute("twitter");
             
             if(twitter != null){
             String soundcloudUrl = request.getParameter("soundcloudUrl");
             String songName = request.getParameter("songName");
             out.println(twitter.toString());
             

             int i = songName.indexOf("- ");
             String song = songName.substring(i + 2);
        try {
              Query query = new Query("(#" + song + ") OR ( " + songName + ") OR (#DoveTune)");

            QueryResult result = twitter.search(query);

                List<Status> tweets = result.getTweets();
//                String list = "<ulid='tweetList'>";
                String list = "";
                for (Status tweet : tweets) {
                   list += "<li><img class='profileImg' src='" + tweet.getUser().getProfileImageURL() + "' title='Profile Image' alt='Profile Image'><strong>" +tweet.getUser().getName() + " @" + tweet.getUser().getScreenName() + "</strong> - " + tweet.getText() + "</li>";
                }

                list = list.replace("#" + song, "<strong class='hashtags'>#" + song + "</strong>");
                list = list.replace("#DoveTune", "<strong class='hashtags'>#DoveTune</strong>");
                list = list.replace("#SoundCloud", "<strong class='hashtags'>#SoundCloud</strong>");
                String songL = song;
                songL = songL.toLowerCase();
                list = list.replace("#" + songL, "<strong class='hashtags'>#" + songL + "</strong>");
                if(list.equals("")){
                    list = "<li>There are no tweets to display. Tweet your own!</li>";
                }

                request.setAttribute("soundcloudUrl", soundcloudUrl);
                request.setAttribute("songName", songName);
                request.setAttribute("song", songName);
                request.setAttribute("list", list);

        } catch (TwitterException te) {
            te.printStackTrace();
            out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
            }else{
                String list = "<ul><li>In order to view tweets, please sign in to <a href=\"SignIn\" title=\"Sign in to Twitter\"><strong class=\"notSignedIn\">Twitter</strong></a></li></ul>";
                request.setAttribute("list", list);
            }
        
        request.getRequestDispatcher("details.jsp").forward(request, response);

      
            
    
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
        try {
            processRequest(request, response);
        } catch (TwitterException ex) {
            Logger.getLogger(SearchTwitter.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (TwitterException ex) {
            Logger.getLogger(SearchTwitter.class.getName()).log(Level.SEVERE, null, ex);
        }
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
