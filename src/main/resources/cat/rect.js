class Rect {
    x;
    y;
    wid;
    hei;
    color;
    dy;

    constructor(x, y, wid, hei, color) {
        this.x = x;
        this.y = y;
        this.wid = wid;
        this.hei = hei;
        this.color = color;
    }

    setDy(dy) {
        this.dy = dy
    }

    drawRect(ctx) {
        ctx.beginPath();
        ctx.fillStyle = this.color;
        // ctx.roundRect(this.x, this.y, this.hei,this.wid, 5);
        ctx.rect(this.x, this.y, this.hei, this.wid)
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
            for (let i = objects[w].x; i <= objects[w].x + objects[w].hei; i++) {
                for (let j = objects[w].y; j <= objects[w].y + objects[w].wid; j++) {
                    if ((this.x == i && this.y == j)
                        || ((this.x + this.wid) == i && (this.y + this.hei) == j)
                        || (this.x == i && (this.y + this.hei) == j)
                        || ((this.x + this.wid) == i && this.y == j)) {
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
                        || ((this.x + this.wid) == i && (this.y + this.hei) == j)
                        || (this.x == i && (this.y + this.hei) == j)
                        || ((this.x + this.wid) == i && this.y == j)) {
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