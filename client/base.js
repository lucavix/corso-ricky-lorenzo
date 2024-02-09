class Game {
    mainCycle;
    board;
    maxx;
    maxy;
    element;
    constructor(maxx,maxy,elementId,mainCycle) {
        this.element = document.getElementById(elementId);
        this.mainCycle = mainCycle;
        this.maxx = maxx;
        this.maxy = maxy;
        this.board = new Array(maxy);
        for (var y=0;y<this.maxy;y++) {
            this.board[y]=new Array(maxx);
        }
        this.clear();
        this.nextFrame();
        console.log("Started");

    }

    nextFrame() {
        window.setTimeout(this.mainCycle,100);
    }

    clear() {
        for (var y=0;y<this.maxy;y++) {
            for (var x=0;x<this.maxx;x++) {
                this.board[x][y] = "#FFF";
            }
        }
        
    }

    draw(elementId) {        
        this.element.innerHTML='';

        for (var y=0;y<this.maxy;y++) {
            for (var x=0;x<this.maxx;x++) {
                var div = (document.createElement('div'));
                div.classList.add("pixel");
                if (x==0) {
                    div.classList.add("first");
                }
                div.style.backgroundColor = this.board[x][y];
                this.element.appendChild(div);                
            }
        }
    }

    setPixel(x,y,color) {
        this.board[x][y] = color;
    }
}