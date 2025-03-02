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
        ctx.roundRect(this.x, this.y, this.hei,this.wid, 5);
        // ctx.rect(this.x, this.y, this.hei, this.wid)
        ctx.fill();

    }

    drawArc(ctx) {
        ctx.beginPath();
        ctx.fillStyle = this.color;
        ctx.arc(this.x + this.hei / 2, this.y + this.hei / 2, this.hei / 2, 0, 2 * Math.PI, true)
        ctx.fill();
    }

    drawLiker(ctx) {
        let x = this.x
        let y = this.y
        ctx.beginPath();
        ctx.fillStyle = this.color;
        ctx.moveTo(x + 15, y + 8);
        ctx.bezierCurveTo(x + 15, y + 7.4, x + 14, y + 5, x + 10, y + 5);
        ctx.bezierCurveTo(x + 4, y + 5, x + 4, y + 12.5, x + 4, y + 12.5);
        ctx.bezierCurveTo(x + 4, y + 16, x + 8, y + 20.4, x + 15, y + 24);
        ctx.bezierCurveTo(x + 22, y + 20.4, x + 26, y + 16, x + 26, y + 12.5);
        ctx.bezierCurveTo(x + 26, y + 12.5, x + 26, y + 5, x + 20, y + 5);
        ctx.bezierCurveTo(x + 17, y + 5, x + 15, y + 7.4, x + 15, y + 8);
        ctx.fill();
    }

    checkCollision(objects) {
        for (let w = 0; w < objects.length; w++) {
            for (let i = objects[w].x; i <= objects[w].x + objects[w].wid; i++) {
                for (let j = objects[w].y; j <= objects[w].y + objects[w].hei; j++) {
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

    getObjCollision(objects) {
        for (let w = 0; w < objects.length; w++) {
            for (let i = objects[w].x; i <= objects[w].x + objects[w].wid; i++) {
                for (let j = objects[w].y; j <= objects[w].y + objects[w].hei; j++) {
                    if ((this.x == i && this.y == j)
                        || (this.x + this.wid == i && this.y + this.hei == j)
                        || (this.x == i && this.y + this.hei == j)
                        || (this.x + this.wid == i && this.y == j)) {
                        return objects[w];
                    }
                }
            }
        }
        return null;
    }

    getThisFacetCollision(objects, ctx) {
        for (let w = 0; w < objects.length; w++) {
            for (let i = objects[w].x; i <= objects[w].x + objects[w].wid; i++) {
                for (let j = objects[w].y; j <= objects[w].y + objects[w].hei; j++) {
                    // if (this.x == i && this.y == j) return 'in'
                    // if (this.x + this.wid == i && this.y + this.hei == j) return 'right'
                    // if (this.x == i && this.y + this.hei == j) return 'top'
                    // if (this.x + this.wid == i && this.y == j) return 'left'
                    this.drawPoint(ctx, i, j)
                }
            }
        }
        return null;
    }
}

class MoveRect extends Rect {
    dx;
    dy;
    move;
    timer;
    power;

    constructor(x, y, dx, dy, wid, hei, color,power) {
        super(x, y, wid, hei, color);
        this.dx = dx;
        this.dy = dy;
        this.power = power;
        this.timer = 0;
        this.move = null;
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

    checkCollision(objects) {
        return super.checkCollision(objects);
    }

    getObjCollision(objects) {
        return super.getObjCollision(objects);
    }

    getThisFacetCollision(objects) {
        return super.getThisFacetCollision(objects);
    }

    drawGhost(ctx) {
        ctx.fillStyle = this.color;
        ctx.beginPath();
        ctx.moveTo(this.x, this.y + 28);
        ctx.lineTo(this.x, this.y + 14);
        ctx.bezierCurveTo(this.x, this.y + 6, this.x + 6, this.y, this.x + 14, this.y);
        ctx.bezierCurveTo(this.x + 22, this.y, this.x + 28, this.y + 6, this.x + 28, this.y + 14);
        ctx.lineTo(this.x + 28, this.y + 28);
        ctx.lineTo(this.x + 23.333, this.y + 23.333);
        ctx.lineTo(this.x + 18.666, this.y + 28);
        ctx.lineTo(this.x + 14, this.y + 23.333);
        ctx.lineTo(this.x + 9.333, this.y + 28);
        ctx.lineTo(this.x + 4.666, this.y + 23.333);
        ctx.lineTo(this.x, this.y + 28);
        ctx.fill();

        ctx.fillStyle = "white";
        ctx.beginPath();
        ctx.moveTo(this.x + 8, this.y + 8);
        ctx.bezierCurveTo(this.x + 5, this.y + 8, this.x + 4, this.y + 11, this.x + 4, this.y + 13);
        ctx.bezierCurveTo(this.x + 4, this.y + 15, this.x + 5, this.y + 18, this.x + 8, this.y + 18);
        ctx.bezierCurveTo(this.x + 11, this.y + 18, this.x + 12, this.y + 15, this.x + 12, this.y + 13);
        ctx.bezierCurveTo(this.x + 12, this.y + 11, this.x + 11, this.y + 8, this.x + 8, this.y + 8);
        ctx.moveTo(this.x + 20, this.y + 8);
        ctx.bezierCurveTo(this.x + 17, this.y + 8, this.x + 16, this.y + 11, this.x + 16, this.y + 13);
        ctx.bezierCurveTo(this.x + 16, this.y + 15, this.x + 17, this.y + 18, this.x + 20, this.y + 18);
        ctx.bezierCurveTo(this.x + 23, this.y + 18, this.x + 24, this.y + 15, this.x + 24, this.y + 13);
        ctx.bezierCurveTo(this.x + 24, this.y + 11, this.x + 23, this.y + 8, this.x + 20, this.y + 8);
        ctx.fill();

        ctx.fillStyle = "black";
        ctx.beginPath();
        ctx.arc(this.x + 18, this.y + 14, 2, 0, Math.PI * 2, true);
        ctx.fill();

        ctx.beginPath();
        ctx.arc(this.x + 6, this.y + 14, 2, 0, Math.PI * 2, true);
        ctx.fill();
    }
}

class LiveMoveRect extends MoveRect {
    live;
    pos;

    constructor(x, y, dx, dy, wid, hei, color, power,live) {
        super(x, y, dx, dy, wid, hei, color, power);
        this.live = live;
        this.pos = 'r';
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

    checkCollision(objects) {
        return super.checkCollision(objects);
    }

    getObjCollision(objects) {
        return super.getObjCollision(objects);
    }

    getThisFacetCollision(objects) {
        return super.getThisFacetCollision(objects);
    }

    drawLiker(ctx) {
        super.drawLiker(ctx);
    }

    drawGhost(ctx) {
        super.drawGhost(ctx);
    }

    drawPlayer(ctx) {
        if (this.dx == 1)
            this.pos = 'r'
        else if (this.dx == -1)
            this.pos = 'l'
        else if (this.dy == 1)
            this.pos = 'u'
        else if (this.dy == -1)
            this.pos = 'd'
        this.timer = this.timer > 15 ? this.timer = 0 : this.timer += 1;
        ctx.beginPath();
        ctx.fillStyle = this.power ? '#9b0205' : this.color;
        if (this.timer > 10 && this.timer <= 20 && (this.dx != 0 || this.dy != 0)) {
            ctx.arc(this.x + this.hei / 2, this.y + this.hei / 2, this.hei / 2, 0, 2 * Math.PI, true)
        } else {
            switch (this.pos) {
                case 'r':
                    ctx.arc(this.x + this.hei / 2, this.y + this.hei / 2, this.hei / 2, getRadians(180), getRadians(20), true);
                    ctx.arc(this.x + this.hei / 2, this.y + this.hei / 2, this.hei / 2, getRadians(180), getRadians(340), false);
                    break;
                case 'l':
                    ctx.arc(this.x + this.hei / 2, this.y + this.hei / 2, this.hei / 2, 0, getRadians(200), true);
                    ctx.arc(this.x + this.hei / 2, this.y + this.hei / 2, this.hei / 2, 0, getRadians(160), false);
                    break;
                case 'u':
                    ctx.arc(this.x + this.hei / 2, this.y + this.hei / 2, this.hei / 2, -getRadians(90), getRadians(110), true);
                    ctx.arc(this.x + this.hei / 2, this.y + this.hei / 2, this.hei / 2, -getRadians(90), getRadians(70), false);
                    break;
                case 'd':
                    ctx.arc(this.x + this.hei / 2, this.y + this.hei / 2, this.hei / 2, getRadians(90), getRadians(290), true);
                    ctx.arc(this.x + this.hei / 2, this.y + this.hei / 2, this.hei / 2, getRadians(90), getRadians(250), false);
                    break;
            }
        }
        ctx.fill();
    }

}

function getRadians(degrees) {
    return (Math.PI / 180) * degrees;
}