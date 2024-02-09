var x=10;
var y=10;
up=false;

function mainCycle() {

    if (up) y--;

    var game  = window.game;
    game.clear();
    
    game.setPixel(x,y,"red");
   
    game.draw();
    game.nextFrame();
}

function keyPressed(keyCode) {
    console.log("Key Pressed: " + keyCode);

    up = (keyCode == "ArrowUp")
    

}


function keyReleased(keyCode) {
    console.log("Key Released: " + keyCode);

    up = (keyCode == "ArrowUp")?false:up;

}
