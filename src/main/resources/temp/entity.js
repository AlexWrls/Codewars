class Rect {
    x;
    y;
    wid;
    hei;
    color;

    constructor(x, y, wid, hei, color) {
        this.x = x;
        this.y = y;
        this.wid = wid;
        this.hei = hei;
        this.color = color;
    }

    drawRect(ctx) {
        ctx.beginPath();
        ctx.fillStyle = this.color;
        ctx.rect(this.x, this.y, this.hei, this.wid)
        ctx.fill();

    }

    drawArc(ctx) {
        ctx.beginPath();
        ctx.fillStyle = this.color;
        ctx.arc(this.x + this.hei / 2, this.y + this.hei / 2, this.hei / 2, 0, 2 * Math.PI, true)
        ctx.fill();
        ctx.stroke();
    }

    checkCollision(obj) {
        // obj = obj.length == undefined ? [obj] : obj;
        for (let w = 0; w < obj.length; w++) {
            for (let i = obj[w].x; i <= obj[w].x + obj[w].wid; i++) {
                for (let j = obj[w].y; j <= obj[w].y + obj[w].hei; j++) {
                    if ((this.x == i && this.y == j)
                        || (this.x + this.wid == i && this.y + this.hei == j)
                        || (this.x == i && this.y + this.hei == j)
                        || (this.x + this.wid == i && this.y == j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    getCollision(obj) {
        // obj = obj.length == undefined ? [obj] : obj;
        for (let w = 0; w < obj.length; w++) {
            for (let i = obj[w].x; i <= obj[w].x + obj[w].wid; i++) {
                for (let j = obj[w].y; j <= obj[w].y + obj[w].hei; j++) {
                    if ((this.x == i && this.y == j)
                        || (this.x + this.wid == i && this.y + this.hei == j)
                        || (this.x == i && this.y + this.hei == j)
                        || (this.x + this.wid == i && this.y == j)) {
                        return obj[w];
                    }
                }
            }
        }
        return null;
    }

}

class MoveRect extends Rect {
    dx;
    dy;

    constructor(x, y, dx, dy, wid, hei, color) {
        super(x, y, wid, hei, color);
        this.dx = dx;
        this.dy = dy;
    }

    stop() {
        this.x -= this.dx;
        this.y -= this.dy;
        this.dx = 0;
        this.dy = 0;
    }

    drawRect(ctx) {
        super.drawRect(ctx);
    }

    drawArc(ctx) {
        super.drawArc(ctx);
    }

    checkCollision(obj) {
        return super.checkCollision(obj);
    }
}

class LiveRect extends Rect {
    live;

    constructor(x, y, wid, hei, color, live) {
        super(x, y, wid, hei, color);
        this.live = live;
    }

    drawRect(ctx) {
        super.drawRect(ctx);
    }

    drawArc(ctx) {
        super.drawArc(ctx);
    }

    checkCollision(obj) {
        return super.checkCollision(obj);
    }
}

class LiveMoveRect extends MoveRect {
    live;

    constructor(x, y, dx, dy, wid, hei, color, live) {
        super(x, y, dx, dy, wid, hei, color);
        this.live = live;
    }

    stop() {
        super.stop();
    }

    drawRect(ctx) {
        super.drawRect(ctx);
    }

    drawArc(ctx) {
        super.drawArc(ctx);
    }

    checkCollision(obj) {
        return super.checkCollision(obj);
    }

    getCollision(obj) {
        return super.getCollision(obj);
    }
}