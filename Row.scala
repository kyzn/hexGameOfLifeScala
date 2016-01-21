//Class for rows (of Planes)
class Row(val s:Int){
    //s means size
    //r means the row itself

    //initialize s many cells in row to state '?'
    val r = Array.fill[Cell](s)(new Cell())

    //Randomize all cells in row
    def rand(pr:Double) = r.foreach{_.rand(pr)}

    //Calculate next states for each cell in a row
    def setNext(rule:Int) = r.foreach{_.setNext(rule)}

    //Actually update states for each cell in a row
    def update() = r.foreach{_.update()}

    //eoStr (even odd string): i is the row index
    //returns toString for even rows, adds space if odd
    def eoStr(i:Int) = if(i%2==0) toString else " "+toString

    //Overriding toString for printing purposes
    override def toString() = (for(t<-r) yield t.toString).mkString(" ")

}