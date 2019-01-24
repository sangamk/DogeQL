package doge.typechecker

import doge.data.symbol.SymbolTable
import doge.node.Node
import doge.typechecker.pass.CircularDependencyPass
import doge.typechecker.pass.DuplicatePass
import doge.typechecker.pass.ScopePass
import doge.typechecker.pass.TypePass

class TypeChecker(val symbolTable: SymbolTable) {

    fun check(tree: Node): TypeCheckResult {
        val result = TypeCheckResult()

        DuplicatePass(result).visit(tree)

        CircularDependencyPass(result, symbolTable).visit(tree)
        if (result.hasErrors()) {
            return result
        }

        ScopePass(result, symbolTable).visit(tree)
        if (result.hasErrors()) {
            return result
        }

        TypePass(result, symbolTable).visit(tree)

        return result
    }

}