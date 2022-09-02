import sys
import math
import random

# Wordle for programmers, you need to find the magical word from a set of words faster than your opponent to win
# Revealing the word get you score 50
# Every correct letter and position reveal will get you 5 points
# Every letter reveal not position will get you 2 points

letter_state = {}
word_chars = []
tested_words = []
state_weight = {0: 0, 1: 0, 2: 2, 3: 5}
alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
word_set = []


def check_all_unlock():
    count = 0
    for letter in letter_state:
        if letter_state[letter][0] == 3:
            count += 1

    return count == 5

def make_the_word():
    word_ls = ['', '', '', '', '']
    for letter in letter_state:
        if letter_state[letter][0] == 3:
            pos = letter_state[letter][1]
            word_ls[pos-1] = letter

    return "".join(word_ls)

word_count = int(input())  # Total number of words in word set
for word in input().split():
    word_set.append(word)


all_unlocked = False
turn = 0
tested_word = ''
# game loop
while True:
    states = []
    for i in input().split():
        # state: State of the character for each position of last guess, 0 for the 1st turn
        state = int(i)
        states.append(state)

    if tested_word:
        for i, (ch, st) in enumerate(zip(tested_word, states)):
            prev_state = letter_state.get(ch, (0, 0))[0]

            if prev_state <= st:
                letter_state[ch] = (st, i+1)

    if not all_unlocked:
        all_unlocked = check_all_unlock()
        tested_word = random.choice([w for w in word_set if w not in tested_words])
        print(tested_word)
        tested_words.append(tested_word)

    else:
        print(make_the_word())

    turn += 1

