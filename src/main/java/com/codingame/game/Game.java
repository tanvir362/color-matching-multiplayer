package com.codingame.game;

import java.util.*;

public class Game {
    private List<Player> players;
    private Map<Player, List<String>> cards;
    private Map<Player, Player> nextPlayer;

    public Game(List<Player> players){
        this.players = new ArrayList<Player>();

        for(Player player: players){
            this.players.add(player);
        }
    }

    private void setPlayerOrientation(){
        Collections.shuffle(players);

        for(int i=0; i<players.size(); i++){
            nextPlayer.put(players.get(i), players.get((i+1)%players.size()));
        }
    }

    private void distributeCards(){
        List<String> deck = new ArrayList<String>();
        for(String card: Constant.CARD_TYPES){
            for(int i=0; i<Constant.CARDS_PER_PLAYER; i++){
                deck.add(card);
            }
        }
        Collections.shuffle(deck);

        cards = new HashMap<Player, List<String>>();
        for(Player player: players){
            List<String> playerCards = new ArrayList<>();

            for(int i=0; i<Constant.CARDS_PER_PLAYER; i++){
                String card = deck.remove(i);
                playerCards.add(card);
            }

            cards.put(player, playerCards);
        }


    }

    public void playerPassingCard(Player sender, String card){
        Player receiver = nextPlayer.get(sender);

        List<String> senderCards = cards.get(sender);
        List<String> receiverCards = cards.get(receiver);

        if(!senderCards.contains(card)){
            throw new IllegalArgumentException(String.format("%s don't have the \"%s\" card!", card, sender.getNicknameToken()));
        }
        senderCards.remove(card);
        receiverCards.add(card);

    }

    public List<Player> getWinners(){
        List<Player> winners = new ArrayList<>();

        for(Player player: players){
            if(player.getScore() == Constant.CARDS_PER_PLAYER){
                winners.add(player);
            }
        }

        return winners;
    }

    public List<String> getPlayerCars(Player player){
        return cards.get(player);
    }

    public Map<Player, Player> getPlayersOrientation(){
        return nextPlayer;
    }

    public int calculatePlayerScore(Player player){

        //getting max frequency of a card and setting the score
        Map<String, Integer> cardFrequency = new HashMap<>();
        for(String eachCard: getPlayerCars(player)){
            cardFrequency.put(
                    eachCard,
                    cardFrequency.getOrDefault(eachCard, 0) + 1
            );
        }


        return Collections.max(cardFrequency.values());
    }


    public void start(){
        setPlayerOrientation();
        distributeCards();

    }


}
