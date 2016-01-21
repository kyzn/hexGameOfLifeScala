//Class for planes
class Plane(val s:Int){
    //s means size
    //p means plane

    //initialize s many rows in plane
    val p = Array.fill[Row](s)(new Row(s))
    
    //Randomize all rows in plane
    def rand(pr:Double) = p.foreach{_.rand(pr)}

    //Calculate next states for each row in a plane
    def setNext(rule:Int) = p.foreach{_.setNext(rule)}

    //Actually update states for each row in a plane
    def update() = p.foreach{_.update()}

    //Neighbor assignment, loop for each row (r)
    //Even and odd rows will call different functions
    def nAssign() = for(r<-0 until s) if(r%2==0) evenAssign(r) else oddAssign(r)

    //Neighbor assignment functions for even and odd rows
    //p(r).r(c) means planes(row).rows(cell)
    //Catch index out of bounds exceptions
    def evenAssign(r:Int){
        for(c<-0 until s){
            try{ p(r).r(c).neigh(0)(0) = p(r-1).r(c-1) } catch{ case _:Throwable => p(r).r(c).neigh(0)(0)=new Cell('?')}
            try{ p(r).r(c).neigh(0)(1) = p(r).r(c-1)   } catch{ case _:Throwable => p(r).r(c).neigh(0)(1)=new Cell('?')}
            try{ p(r).r(c).neigh(0)(2) = p(r+1).r(c-1) } catch{ case _:Throwable => p(r).r(c).neigh(0)(2)=new Cell('?')}
            try{ p(r).r(c).neigh(0)(3) = p(r-1).r(c)   } catch{ case _:Throwable => p(r).r(c).neigh(0)(3)=new Cell('?')}
            try{ p(r).r(c).neigh(0)(4) = p(r+1).r(c)   } catch{ case _:Throwable => p(r).r(c).neigh(0)(4)=new Cell('?')}
            try{ p(r).r(c).neigh(0)(5) = p(r).r(c+1)   } catch{ case _:Throwable => p(r).r(c).neigh(0)(5)=new Cell('?')}

            try{ p(r).r(c).neigh(1)(0) = p(r-1).r(c-2) } catch{ case _:Throwable => p(r).r(c).neigh(1)(0)=new Cell('?')}
            try{ p(r).r(c).neigh(1)(1) = p(r+1).r(c-2) } catch{ case _:Throwable => p(r).r(c).neigh(1)(1)=new Cell('?')}
            try{ p(r).r(c).neigh(1)(2) = p(r-2).r(c)   } catch{ case _:Throwable => p(r).r(c).neigh(1)(2)=new Cell('?')}
            try{ p(r).r(c).neigh(1)(3) = p(r+2).r(c)   } catch{ case _:Throwable => p(r).r(c).neigh(1)(3)=new Cell('?')}
            try{ p(r).r(c).neigh(1)(4) = p(r-1).r(c+1) } catch{ case _:Throwable => p(r).r(c).neigh(1)(4)=new Cell('?')}
            try{ p(r).r(c).neigh(1)(5) = p(r+1).r(c+1) } catch{ case _:Throwable => p(r).r(c).neigh(1)(5)=new Cell('?')}
        }
    }

    def oddAssign(r:Int){
        for(c<-0 until s){
            try{ p(r).r(c).neigh(0)(0) = p(r).r(c-1)   } catch{ case _:Throwable => p(r).r(c).neigh(0)(0)=new Cell('?')}
            try{ p(r).r(c).neigh(0)(1) = p(r-1).r(c)   } catch{ case _:Throwable => p(r).r(c).neigh(0)(1)=new Cell('?')}
            try{ p(r).r(c).neigh(0)(2) = p(r+1).r(c)   } catch{ case _:Throwable => p(r).r(c).neigh(0)(2)=new Cell('?')}
            try{ p(r).r(c).neigh(0)(3) = p(r-1).r(c+1) } catch{ case _:Throwable => p(r).r(c).neigh(0)(3)=new Cell('?')}
            try{ p(r).r(c).neigh(0)(4) = p(r).r(c+1)   } catch{ case _:Throwable => p(r).r(c).neigh(0)(4)=new Cell('?')}
            try{ p(r).r(c).neigh(0)(5) = p(r+1).r(c+1) } catch{ case _:Throwable => p(r).r(c).neigh(0)(5)=new Cell('?')}

            try{ p(r).r(c).neigh(1)(0) = p(r-1).r(c-1) } catch{ case _:Throwable => p(r).r(c).neigh(1)(0)=new Cell('?')}
            try{ p(r).r(c).neigh(1)(1) = p(r+1).r(c-1) } catch{ case _:Throwable => p(r).r(c).neigh(1)(1)=new Cell('?')}
            try{ p(r).r(c).neigh(1)(2) = p(r-2).r(c)   } catch{ case _:Throwable => p(r).r(c).neigh(1)(2)=new Cell('?')}
            try{ p(r).r(c).neigh(1)(3) = p(r+2).r(c)   } catch{ case _:Throwable => p(r).r(c).neigh(1)(3)=new Cell('?')}
            try{ p(r).r(c).neigh(1)(4) = p(r-1).r(c+2) } catch{ case _:Throwable => p(r).r(c).neigh(1)(4)=new Cell('?')}
            try{ p(r).r(c).neigh(1)(5) = p(r+1).r(c+2) } catch{ case _:Throwable => p(r).r(c).neigh(1)(5)=new Cell('?')}
        }
    }

    //Overriding toString for printing purposes
    override def toString() = (for (i <- 0 until s) yield p(i).eoStr(i)).mkString("\n")

}