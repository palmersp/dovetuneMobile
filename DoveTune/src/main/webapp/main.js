window.onload = function() {

  (function retrieveRecentSongs() {
    var trackList = [];
    trackList.push(localStorage.doveTune__trackOne);
    trackList.push(localStorage.doveTune__trackTwo);
    trackList.push(localStorage.doveTune__trackThree);
    trackList.push(localStorage.doveTune__trackFour);

    var artworkList = [];
    artworkList.push(localStorage.doveTune__artworkOne);
    artworkList.push(localStorage.doveTune__artworkTwo);
    artworkList.push(localStorage.doveTune__artworkThree);
    artworkList.push(localStorage.doveTune__artworkFour);

    if (trackList[0] || trackList[1] || trackList[2] || trackList[3]) {
      console.log("something");

      var orderedList = document.createElement('ol');
      for (var i = 0; i < trackList.length; i++) {
        var img = document.createElement('img');
        img.src = artworkList[i];
        orderedList.appendChild(img);
        var listItem = document.createElement('li');
        var text = document.createTextNode(trackList[i]);
        listItem.appendChild(text);
        orderedList.appendChild(listItem);
      }
      var recentSongsList = document.querySelector('.recentSongs__list');
      recentSongsList.appendChild(orderedList);

    } else {
      console.log("better");
      var tempTrackList = ["Stressed Out", 'Sufjan Stevens, "Should Have Known Better"', "Burning House", "Interstellar - Soundtrack - Dust - Hans Zimmer (2014) [Original Soundtrack]"];
      var tempArtworkList = ["https://i1.sndcdn.com/artworks-000117639387-35n4cg-large.jpg", "https://i1.sndcdn.com/artworks-000109656042-37dw2r-large.jpg", "https://i1.sndcdn.com/artworks-000125942257-aa7mg8-large.jpg", "https://i1.sndcdn.com/artworks-000096985678-e1my5n-large.jpg"];

      var orderedList = document.createElement('ol');
      for (var i = 0; i < tempTrackList.length; i++) {
        var img = document.createElement('img');
        img.src = tempArtworkList[i];
        orderedList.appendChild(img);
        var listItem = document.createElement('li');
        var text = document.createTextNode(tempTrackList[i]);
        listItem.appendChild(text);
        orderedList.appendChild(listItem);
      }
      var recentSongsList = document.querySelector('.recentSongs__list');
      recentSongsList.appendChild(orderedList);

    }
  })();


  SC.initialize({
    client_id: 'bec3e771c59b67cd311ffbd871a4ce8c'
  });

  // stream track id 206661710
  SC.get('/tracks/206661710').then(function(player) {
    // player.play();
    console.log("test1");
    // document.querySelector('.player__controls').setAttribute('src', player.uri + "/client_id=bec3e771c59b67cd311ffbd871a4ce8c");
    console.log("testing testing testing");
    console.log(player);
  });

}
