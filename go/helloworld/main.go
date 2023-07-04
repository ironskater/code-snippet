package main

import "fmt"

func main() {

	cards := []string{newCard(), newCard()}

	cards = append(cards, "Six of Spades")

	for card := range cards {
		fmt.Println(card)
	}
}

func newCard() string {
	return "Five of Diamonds"
}
