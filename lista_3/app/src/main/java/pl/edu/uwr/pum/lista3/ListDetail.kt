package pl.edu.uwr.pum.lista3

class ListDetail (val points: Int, val info: String, val lOrP: Int){
        var id: Int = 0

        constructor(id: Int, points: Int, info: String, lOrP: Int ): this(points,info, lOrP) {
            this.id = id
        }
    }