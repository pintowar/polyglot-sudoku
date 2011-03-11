package polyglot.sudoku

class PuzzleContentPane extends JPanel {
    private String problem;

    public PuzzleContentPane(){
        super()
        setLayout(new BorderLayout())
    }
    
    public void setProblem(String problem) {
        this.problem = problem
        removeAll()
        add(init(problem))
        revalidate()
        repaint()
    }

    def init(text){
        def swing = new groovy.swing.SwingBuilder()
        swing.vbox{
            for (i = 0; i < 9; i++){
                swing.hbox{
                    for (j = 0; j < 9; j++){ 
                        def aux = text[(9*i + j)]
                        aux = ('.' == aux)?' ':aux
                        def panelProps = [border:loweredBevelBorder(), preferredSize:[45,45] ]
			            
			            if (!((j >= 0 && j <= 2 && i >= 0 && i <= 2) || (j >= 6 && j <= 8 && i >= 0 && i <= 2) || 
			                (j >= 3 && j <= 5 && i >= 3 && i <= 5) || (j >= 0 && j <= 2 && i >= 6 && i <= 8) || 
			                (j >= 6 && j <= 8 && i >= 6 && i <= 8))) {
			                panelProps += [background:Color.GRAY]
			            }
			            
                        swing.panel(panelProps){
                            swing.borderLayout()
                            swing.label(text:aux, horizontalAlignment: SwingConstants.CENTER)
                        }
                    }
                }
            }
        } 
    }
}
