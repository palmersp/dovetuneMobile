window.onload = function() {


  SC.initialize({
    client_id: 'bec3e771c59b67cd311ffbd871a4ce8c'
  });

  // stream track id 206661710
  SC.get('/tracks/206661710').then(function(player) {
    // player.play();
    console.log("test1");
    document.querySelector('.player__controls').setAttribute('src', player.uri + "/client_id=bec3e771c59b67cd311ffbd871a4ce8c");
    console.log("testing testing testing");
    console.log(player);
  });

}
