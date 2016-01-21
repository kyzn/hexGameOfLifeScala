object Life{
    def main(args:Array[String]){

        var rule = 6    //-12      apply rule 6 or 12
        var size = 100  //-size n  plane size
        var file = ""   //-f path  input config file
        var gens = 10   //-g n     how many generations
        var prin = 1    //-p n     print every nth gen
        var prob = 0.5  //-i n     probability of alive

        //Argument parsing
        for (i<-0 until args.size){
            if(args(i)=="-12"){ rule=12; }
            else if(args(i)=="-size"){ size=args(i+1).toInt   }
            else if(args(i)=="-f")   { file=args(i+1)         }
            else if(args(i)=="-g")   { gens=args(i+1).toInt   }
            else if(args(i)=="-p")   { prin=args(i+1).toInt   }
            else if(args(i)=="-i")   { prob=args(i+1).toDouble}
        }

        var lines = Array("","")//Will store file content 

        if(file!=""){           //Read the file once
            val source = io.Source.fromFile(file)
            lines = try source.mkString.split("\n") finally source.close()
            size = lines.size
        }

        val p = new Plane(size) //Create the plane
        p.nAssign()             //Assign its neighbors

        if(file!=""){           //Initialize plane
            for(i<-0 until size;j<-0 until size)
                p.p(i).r(j).state=lines(i)(j)
        }else{
            p.rand(prob)        //Randomly initialize
        }

        print(p+"\n\n")         //Print init conf

        for(i<-1 until gens+1){ //Main loop
            p.setNext(rule)     //calculate next
            p.update()          //update to next
            if(i%prin==0){      //print when requested
                println(i+"\n"+p+"\n\nPress enter")
                //try-catch to avoid empty readchar error
                try{scala.io.StdIn.readChar()}catch{case _:Throwable=>;}
            }
        }//end main loop

    }//end main
}//end life