package polyglot.sudoku

import org.python.util.PythonInterpreter;
import org.springframework.core.io.ClassPathResource

class PolyglotSudokuController {
    // these will be injected by Griffon
    def model
    def view

    // void mvcGroupInit(Map args) {
    //    // this method is called after model and view are injected
    // }

    // void mvcGroupDestroy() {
    //    // this method is called when the group is destroyed
    // }

    def defineProblem = { evt ->
    	def file = new ClassPathResource("top95.txt", app.class.classLoader).getFile()
        def problems = []
        file.eachLine{problems << it}
       
        model.problem = problems[new Random().nextInt(problems.size())]
    }

    def solveProblem = { evt ->
    	def res = new ClassPathResource("jython/solver.py", app.class.classLoader)
    	def interp = new PythonInterpreter()
		interp.execfile(res.inputStream )
		def obj = interp.eval("solve('${model.problem}')")

        model.problem = obj.keySet().sort().collect{obj[it]}.join('')
    }    
}
