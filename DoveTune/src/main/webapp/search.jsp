<%--
    Document   : experimentalSearch
    Created on : Mar 13, 2015, 5:49:00 PM
    Author     : adam
--%>
<%@page import="twitter4j.Twitter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Search</title>
        <link href="tanga.css" type="text/css" rel="stylesheet" media="screen" />
        <script src="http://connect.soundcloud.com/sdk.js"></script>
        <style>
            .resultdiv {
                background-color: #edece0;
            }
        </style>
    </head>
    <body>
        <script>
            SC.initialize({
                client_id: 'YOUR_CLIENT_ID'
            });

            var search = getParameterByName('searchbox');
            console.log(search);
            if (search) {
                var message_entered =  search;
                // find all sounds of message_entered licensed under 'creative commons share alike'
                SC.get('/tracks', { q: message_entered }, function(tracks) {
                    console.log(tracks.length + " search results");

                    var parent = document.getElementById("searchdiv");
                    h2 = document.createElement('h2');
                    h2.innerHTML = "Search Results";
                    parent.appendChild(h2);
                    for (var i = 0; i < tracks.length; i++) {
                        permalink = tracks[i]["permalink_url"];
                        title = tracks[i]["title"];
                        avatar = tracks[i]['user']['avatar_url'];
                        download = tracks[i]['download_url'];
                        artwork_url = tracks[i]['artwork_url'];
                        artist = tracks[i]['user']['username'];

                        //debug
                        console.log(tracks[i]);

                        diva = document.createElement('div');

                        a = document.createElement('a');
                        a.href = "SearchTwitter?soundcloudUrl=" + permalink + "&songName=" + title;
                        a.innerHTML = title;
                        img = document.createElement('img');
                        img.src = artwork_url;
                        img.alt = "Image Unavailable";
                        a.appendChild(document.createElement('br'));
                        img.setAttribute("width", "30%");
                        a.appendChild(img);
                        diva.setAttribute("class","resultdiv");
                        diva.appendChild(document.createElement('hr'));
                        diva.appendChild(a);
                        parent.appendChild(diva);
                    }
                });
            };

            function getParameterByName(name) {
                name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
                var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
                    results = regex.exec(location.search);
                return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
            }

        </script>
        <div id="searchdiv">
            <%
               Twitter twitter = (Twitter)request.getSession().getAttribute("twitter");
                    if (twitter != null){
                out.write("<p><a id=\"signOut\" href=\"SignOut\">Sign Out</a></p>");
                }
            %>
            <h1 style="text-align: center;">Dovetune</h1>
            <%
                    if (twitter == null){
                out.write("<h1 style=\"text-align: center;\"><a href=\"SignIn\">Sign In to Twitter</a></h1>");
                }
            %>


            <form action="search.jsp" method="GET">
                <input type="text" name="searchbox" id="search_box">
                <input type="submit" value="Search">
            </form>
        </div>
    </body>
</html>
