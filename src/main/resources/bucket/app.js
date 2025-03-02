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

    //
    // drawRect(ctx) {
    //     const obj = new Path2D();
    //     obj.fillStyle = this.color;
    //     obj.rect(this.x, this.y, this.hei, this.wid)
    //     obj.fillText(this.cont,0,0);
    //     ctx.fill(obj);
    //     return obj;
    // }
    //
    // drawArc(ctx) {
    //     const obj = new Path2D();
    //     obj.arc(this.x + this.hei / 2, this.y + this.hei / 2, this.hei / 2, 0, 2 * Math.PI, true)
    //     obj.fillStyle = this.color;
    //     ctx.fill(obj);
    //     return obj;
    // }
}

class Box extends Rect {

    size;
    cont;

    constructor(x, y, wid, hei, color, size, cont) {
        super(x, y, wid, hei, color);
        this.size = size;
        this.cont = cont;
    }

    incSize(l) {
        let max = this.size - this.cont
        if ((this.cont + l) > this.size) {
            this.cont = this.size;
            return max
        } else {
            this.cont += l;
            return l;
        }
    }

    decSize(l) {
        this.cont = this.cont - l;
        return this.cont - l
    }

    drawRect(ctx) {
        const tank = new Path2D();
        tank.fillStyle = this.color;
        tank.rect(this.x, this.y, this.hei, this.wid)
        ctx.strokeStyle = "navy";     // устанавливаем цвет текста
        ctx.strokeText(this.cont, this.x, this.y);
        ctx.fill(tank);
        return tank;
    }

    drawCont(ctx) {
        const content = new Path2D();
        content.fillStyle = '#37589b';
        content.rect(this.x + 5, this.y, this.hei - 10, this.cont * 10)
        ctx.fill(content);
        return content
    }
}