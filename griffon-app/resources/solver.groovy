public class Solver {
    private cross = { a, b -> [a,b].combinations().collect{it.join('')}}

    private digits = ('1'..'9') as ArrayList
    private rows = ('A'..'I') as ArrayList
    private cols = digits

    private squares = cross(rows, cols).sort()

    //lista com as possibilidades de verificação (linha, coluna e quadrado)
    private unitlist = cols.collect{cross(rows, it)} + rows.collect{cross(it, cols)} + [['ABC','DEF','GHI'],['123','456','789']].combinations().collect{cross(it).sort()}

    //mapeamento de verificações a serem feitas por elemento. ex.: [A1:[[A1, B1, C1, D1, E1, F1, G1, H1, I1], [A1, A2, A3, A4, A5, A6, A7, A8, A9], [A1, A2, A3, B1, B2, B3, C1, C2, C3]]]
    private units = squares.inject([:]){map, it -> map[(it)] = unitlist.groupBy{elem -> it in elem}[true]; map}
    //mapeamento de todas as casas a serem procuradas pelo elemento chave. ex: [A1:[A2, A3, A4, A5, A6, A7, A8, A9, B1, B2, B3, C1, C2, C3, D1, E1, F1, G1, H1, I1]]
    private peers = units.inject([:]){map, it -> map[(it.key)] = (it.value.flatten().unique() - it.key); map}

	/*
	 * Convert grid into a dict of {square: char} with '0' or '.' for empties.
	 */
	def gridValues(grid){
		def chars = grid.findAll{it in digits || it in ['0','.']}
		assert chars.size() == 81
		def map = [:]
		squares.eachWithIndex{it, i -> map[it] = chars[i]}
		return map
	}

	/*
	 * Convert grid to a dict of possible values, [square: digits], or
	 * return False if a contradiction is detected.
	 */
	def parseGrid(grid){
		//To start, every square can be any digit; then assign values from the grid.
		def values = squares.inject([:]){map, it -> map[(it)] = digits.join(''); map}
		gridValues(grid).each{k,v ->
			if(v in digits && !assign(values, k, v)){
				return false
			}
		}
		return values
	}

	/*
	 * Eliminate all the other values (except d) from values[s] and propagate.
	 * Return values, except return False if a contradiction is detected.
	 */
	def assign(values, s, d){
		def otherValues = values[s].replace(d, '')
		if(otherValues.collect{eliminate(values, s, it)}.every()){
			return values
		}else{
			return false
		}
	}

	/*
	 * Eliminate d from values[s]; propagate when values or places <= 2.
	 * Return values, except return False if a contradiction is detected.
	 */
	def eliminate(values, s, d){
		if(!(values[s].contains(d))){
			return values //Already eliminated
		}
		values[s] = values[s].replace(d, '')
		//values[s].remove(d)
		//(1) If a square s is reduced to one value d2, then eliminate d2 from the peers.
		if(values[s].size() == 0){
			return false
		}else if(values[s].size() == 1){
			def d2 = values[s]
			if(!peers[s].collect{eliminate(values, it, d2)}.every()){
				return false
			}
		}
		//(2) If a unit u is reduced to only one place for a value d, then put it there.
		units[s].each{ u ->
			def dplaces = u.flatten().findAll{values[s].contains(d)}
			if(dplaces.size() == 0){
				return false
			}else if(dplaces.size() == 1){
				if(!assign(values, dplaces[0], d)){
					return false
				}
			}
		}
		return values
	}

	def solve(grid){
		search(parseGrid(grid))
	}
	/*
	 * Using depth-first search and propagation, try all possible values.
	 */
	def search(values){
		if(values == false){
			return false //failed earlier
		}
		if(squares.every{values[it].size() == 1}){
			return values //solved
		}
		def s = squares.findAll{values[it].size() > 1}.min{values[it].size()}
	
		return some(values[s].collect{search(assign(values.clone(), s, it))})
	}

	def some(seq){
		def any = seq.findAll{it instanceof Map}
		return (any.size() > 0)?any[0]:false	
	}
}

def getSolver(){
    new Solver()    
}
