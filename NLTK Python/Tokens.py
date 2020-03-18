import nltk
import re
from nltk.tokenize import word_tokenize


ajay = "A milkman became very wealthy through dishonest means. He had to cross a river daily to reach the city where his customers lived. He mixed the water of the river generously with the milk that he sold for a good profit. One day he went around collecting the dues in order to celebrate the wedding of his son. With the large amount thus collected he purchased plenty of rich clothes and glittering gold ornaments. But while crossing the river the boat capsized and all his costly purchases were swallowed by the river. The milk vendor was speechless with grief. At that time he heard a voice that came from the river, “Do not weep. What you have lost is only the illicit gains you earned through cheating your customers."
#ajay = "There was an old owl that lived in an oak. Everyday he saw incidents happening around him. Yesterday he saw a boy helping an old man to carry a heavy basket. Today he saw a girl shouting at her mother. The more he saw the less he spoke.As he spoke less, he heard more. He heard people talking and telling stories. He heard a woman saying that an elephant jumped over a fence. He also heard a man saying that he had never made a mistake.The old owl had seen and heard about what happened to people. Some became better and some became worse. But the old owl had become wiser each and every day."
#ajay = "One day, there was a mouse that was very afraid. A big cat was chasing him. The mouse was running as fast as he could to save his life. The mouse saw a big grandfather clock. It climbed up the clock. It reached the top and sat down to rest.Not long after that, the clock struck one, ‘Dong!’ The mouse had such a shock that he ran down the clock."
#ajay = "There lived a black sheep in a nearby village. Every spring, he shaved his black wool and sold it to the villagers. The villagers made sweaters and socks from his black wool.One day, the black sheep noticed that he had some more wool left. He thought, ‘It would be such a waste if nobody wants to buy the wool.’That afternoon, an old man came over to his wooden shed to see him. He wanted one bag full of the black sheep’s wool. Then an old woman came over. She also wanted a bag full of wool. A short while later, a little boy arrived. He also wanted one bag full of wool.Therefore, the black sheep prepared three bags full of wool for them. He was happy that all of his wool was sold off."
ajay = "Once upon a time in a fairy tale land a cat and a dog were friends. One night, the cat invited the dog for a party at his house.The cat played the fiddle. The dog happily clapped his hands. Suddenly, they saw a cow flying in the sky. It jumped over the moon. The dog laughed. Just then, they saw a dish and a spoon from the party running away together. And they laughed even louder. After that they became the best friends."
##ajay  = "A salt seller used to carry the salt bag on his donkey to the market every day.On the way they had to cross a stream. One day the donkey suddenly tumbled down the stream and the salt bag also fell into the water. The salt dissolved in the water and hence the bag became very light to carry. The donkey was happy.Then the donkey started to play the same trick every day.The salt seller came to understand the trick and decided to teach a lesson to it. The next day he loaded a cotton bag on the donkey.Again it played the same trick hoping that the cotton bag would be still become lighter.But the dampened cotton became very heavy to carry and the donkey suffered. It learnt a lesson. It didn’t play the trick anymore after that day, and the seller was happy."
#ajay = "Once upon a time there lived a lion in a forest. One day after a heavy meal. It was sleeping under a tree. After a while, there came a mouse and it started to play on the lion. Suddenly the lion got up with anger and looked for those who disturbed its nice sleep. Then it saw a small mouse standing trembling with fear. The lion jumped on it and started to kill it. The mouse requested the lion to forgive it. The lion felt pity and left it. The mouse ran away.On another day, the lion was caught in a net by a hunter. The mouse came there and cut the net. Thus it escaped. There after, the mouse and the lion became friends. They lived happily in the forest afterwards"
#ajay = "It was an incredibly hot day, and a lion was feeling very hungry.He came out of his den and searched here and there. He could find only a small hare. He caught the hare with some hesitation. “This hare can’t fill my stomach” thought the lion.As the lion was about to kill the hare, a deer ran that way. The lion became greedy. He thought;“Instead of eating this small hare, let me eat the big deer.”He let the hare go and went behind the deer. But the deer had vanished into the forest. The lion now felt sorry for letting the hare off."
tokens = nltk.word_tokenize(ajay)
# with open('tokenoutput.txt', 'w') as f:
# print(tokens, sep=' ', end='\n', file=f)


punct = re.compile(r'[-.?!,:;()""|0-9]')

post_punct = []


for words in tokens:
    word = punct.sub("", words)
    if len(word) > 0:
        post_punct.append(word)


with open('Honestly_token.txt', 'w') as f:
    print(post_punct, file=f)
