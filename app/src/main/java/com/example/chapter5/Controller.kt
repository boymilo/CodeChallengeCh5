package com.example.chapter5

class Controller {
    private var player1: String = ""
    private var player2: String = ""
    private val pilihan = mutableListOf("gunting", "batu", "kertas")
    private var result: Int = 0
    fun setPlayer1(player1: String){
        this.player1 = player1
    }

    fun setPlayer2(player2: String){
        this.player2 = player2
    }

    fun getPlayer1(): String {
        return player1
    }

    fun getPlayer2(): String {
        return player2
    }

    fun getRandom(): String{
        return pilihan.random()
    }

    fun logicGame(): Int{
        if(player1 == player2){
            result = 3
        }
        else{
            if(player1 == "gunting" && player2 =="kertas" || player1 == "kertas" && player2 =="batu" || player1 == "batu" && player2 =="gunting"){
                result = 1
            }
            else
                result = 2
        }
        return result
    }

    fun random(): Int {
        var acak: Int = pilihan.random().toInt()
        return acak
    }
}